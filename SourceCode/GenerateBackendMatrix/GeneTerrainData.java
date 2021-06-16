package GenerateBackendMatrix;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Formatter;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class GeneTerrainData 
{

	// the back-end data fields (related to node and edge)
	String [] nodeID; // all of the node IDs
	HashMap <String, Integer> nodeID2Index; // map from node ID (String) to node index (in nodeID array)
	float [] nodeX; // nodes' x coordinates
	float [] nodeY; // nodes' y coordinates
	float [] nodeValue; // nodes' heights (node values)
	float [] nodeSize; // node size (influence)
	boolean [] nodeInLayout; // check if the node is in the layout file
	float maxNodeSize; // remember the node size for future calculation
	float minNodeSize;

	// the front-end data
	int [] nodeXPixel; // node x pixel coordinate
	int [] nodeYPixel; // node y pixel coordinate
	//float [] nodeFrontWeight; // node backend weight -- not really needed
	//Color[][] layoutColor; // node Color (node Height <--> color)

	float [] nodeBackWeight; // this is the weight factor used in gaussian mixer, derived from nodeSize
	int [] nodeRadius; // radius of the node in 'view node' mode. Maximum radius is 30 pixel, minimum is 5 pixel
	final int MAX_RADIUS = 30;
	final int MIN_RADIUS = 5;
	float [][] pixelValue;
	
	final int RESOLUTION; // resolution of the backend terrain data;


	TerrainDataImporter importer;
	//TerrainDataBackFrontConverter converter;

	public GeneTerrainData(String argNodeFile, String argLayoutFile, int argResolution) 
	{
		importer = new TerrainDataImporter (this);
		setupBackEndData(new File(argNodeFile), new File(argLayoutFile));
		this.RESOLUTION = argResolution;
		
		pixelValue = new float [RESOLUTION][RESOLUTION];
		for (int i = 0; i < RESOLUTION; i++)
		{
			for (int j = 0; j < RESOLUTION; j++)
			{
				pixelValue[i][j] = 0;
			}
		}

	}


	// -------------- setup the backend data ---------------------
	private void setupBackEndData (File nodeFile,  File layoutFile) 
	{
		try
		{
			importer.readNodeFile(nodeFile);

			importer.readLayoutFile(layoutFile);
			
			for (int i = 0; i < nodeX.length; i++)
			{
				System.out.println(nodeX[i] + ", " + nodeY[i]);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	// update backend weight / influence factor for each gene
	public void updateBackendWeight(float minPercent, float maxPercent)
	{

		float minBackendWeight = 20*minPercent / 100;
		float maxBackendWeight = 20*maxPercent / 100;
		//System.out.println(minBackendWeight + " " + maxBackendWeight);
		//System.out.println(minNodeSize + " " + maxNodeSize);

		
		if (Math.abs(maxNodeSize - minNodeSize) > 0.001)
		{
			for (int i = 0; i < nodeID.length; i++)
			{
				nodeBackWeight[i] = (maxBackendWeight - minBackendWeight) * (nodeSize[i] - minNodeSize) / (maxNodeSize - minNodeSize) + minBackendWeight;
				nodeRadius[i] = Math.round((MAX_RADIUS - MIN_RADIUS) * (nodeSize[i] - minNodeSize) / (maxNodeSize - minNodeSize) + MIN_RADIUS);
			}
		}
		else // all node size are equal
		{
			for (int i = 0; i < nodeID.length; i++)
			{
				nodeBackWeight[i] = 1 ;
				nodeRadius[i] =  MIN_RADIUS;
			}

		}
	}



	// ------------ set the pixel value (bigger than pixel table for faster moving) when drawing the terrain ------------------------------
	// hint: after import, all nodes should be within the square(-8, 8). So I assume that it would be fine to draw from (-16, 16) in real coordinate
	public void setupAllPixelVal ()
	{
		// this contains the area outside the layout
		float gridWidth = 32f / RESOLUTION;
		float minDraw = -16f;
		float maxDraw = 16f;
		
		// this only covers area inside the layout, for testing
		//float gridWidth = 20f / RESOLUTION;
		//float minDraw = -10f;
		//float maxDraw = 10f;

		//run N threads in parallel
		int numThread = 4;
		ParallelTerrainData [] parallelTerrain = new ParallelTerrainData [numThread];
		for (int i = 0; i < numThread; i++)
		{
			parallelTerrain[i] = new ParallelTerrainData(i,numThread, this);
		}
		for (int i = 0; i < numThread; i++)
		{
			parallelTerrain[i].start();
		}
		try
		{
			for (int i = 0; i < numThread; i++)
			{
				parallelTerrain[i].join();
			}			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		for (int h = 0; h < RESOLUTION; h++)
		{			
			for (int w = 0; w < RESOLUTION; w++)
			{
				// calculating the backend coordinate of the pixel
				float xBackend = minDraw + w * gridWidth + gridWidth/2 ;
				float yBackend = maxDraw - h * gridWidth - gridWidth/2;

				// calculating the pixel value
				float pixelVal = 0;
				for (int i = 0; i < nodeID.length; i++)
				{
					if (nodeInLayout[i])
					{
						float sqrDis = CommonMath.getSquareDistance (xBackend, yBackend, nodeX[i], nodeY[i]);

						//pixelVal += nodeValue[i] * Math.exp(-sqrDis / nodeBackWeight[i]);
						pixelVal += nodeValue[i] *  nodeBackWeight[i] / (sqrDis + 1) ;
					}
				}

				//mainUI.terrainPanel.pixelValue[h][w] = pixelVal;
				pixelValue[h][w] = pixelVal;

			}
		}

	}


	// -------------- scale the node backend weight -----------------------------------
	public void scaleNodeWidth (float argWidthScale)
	{
		for (int i = 0; i < nodeID.length; i++)
		{
			nodeBackWeight[i] *= argWidthScale;
		}
	}



	// ---------------------------- save pixel value matrix to a text file ------------------------
	public void savePixelValue (String argFilePath)
	{
		try
		{
			Formatter writeFile = new Formatter (new File (argFilePath));

			for (int h = 0; h < RESOLUTION; h++)
				//for (int h = -drawHeight; h < 2*drawHeight; h++)
			{
				for (int w = 0; w < RESOLUTION - 1; w++)
					//for (int w = -drawWidth; w < 2*drawWidth; w++)
				{
					writeFile.format(pixelValue[h][w] + "\t");
				}
				writeFile.format(pixelValue[h][RESOLUTION - 1] + "\r\n");
			}

			writeFile.close();
			
			/*OutputStream outputStream = new FileOutputStream(argFilePath);
			
			DataOutputStream byteOut = new DataOutputStream(outputStream);
			for (int h = 0; h < RESOLUTION; h++)
			{
				for (int w = 0; w < RESOLUTION ; w++)
				{
					byteOut.writeFloat(pixelValue[h][w]); 
				}
			}
			
			byteOut.close();
			outputStream.close();*/
			
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
