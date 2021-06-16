// this class handle the mapping between the back-end (user input data) and the front-end (terrain) data

import java.awt.Color;
import java.io.File;
import java.io.Serializable;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class GeneTerrainData implements Serializable
{
	private static final long serialVersionUID = 6529685098267757690L;
	
	GeneTerrainMainUI mainUI;

	// the back-end data fields (related to node and edge)
	String [] nodeID; // all of the node IDs
	HashMap <String, Integer> nodeID2Index; // map from node ID (String) to node index (in nodeID array)
	float [] nodeX; // nodes' x coordinates
	float [] nodeY; // nodes' y coordinates
	float [] nodeValue; // nodes' heights (node values)
	float [] nodeSize; // node size (influence)
	boolean [] nodeInLayout; // check if the node is in the layout file
	Edge [] edge; // list of all edge
	float maxNodeSize; // remember the node size for future calculation
	float minNodeSize;
	
	boolean [][] mask;  // this is the mask. Pixel outside the mask will have 'default' average value.

	// the front-end data
	int [] nodeXPixel; // node x pixel coordinate
	int [] nodeYPixel; // node y pixel coordinate
	//float [] nodeFrontWeight; // node backend weight -- not really needed
	//Color[][] layoutColor; // node Color (node Height <--> color)
	int drawHeight; // the height of the terrain drawing panel in the mainUI
	int drawWidth; // the weight of the terrain drawing panel in the mainUI
	float [] nodeBackWeight; // this is the weight factor used in gaussian mixer, derived from nodeSize
	int [] nodeRadius; // radius of the node in 'view node' mode. Maximum radius is 30 pixel, minimum is 5 pixel
	final int MAX_RADIUS = 30;
	final int MIN_RADIUS = 5;
	float [][] pixelValue;

	// transformation from back end to front end data
	float topView;    // the following 4 fields define the drawing windows at the back end
	float bottomView;
	float leftView;
	float rightView;
	Color peakColor;	// peak, flat and valley colors
	Color valleyColor;
	Color flatColor;
	float peakValue;  // peak, flat and valley values
	float valleyValue;
	float flatValue;

	float upperValue; // a threshold between peak and flat. Use this to smoother the color
	Color upperColor;
	float lowerValue; // a threshold between flat and valley. Use this to smoother the color
	Color lowerColor;

	int upLeftX; //the 'upper left' point where we start using pixelValue table
	int upLeftY;

	TerrainDataImporter importer;
	//TerrainDataBackFrontConverter converter;

	public GeneTerrainData(GeneTerrainMainUI argMainUI) 
	{
		mainUI = argMainUI;
		drawHeight = mainUI.terrainPanel.getHeight();
		drawWidth = mainUI.terrainPanel.getWidth();

		upLeftX = drawWidth;
		upLeftY = drawHeight;
		
		//System.out.println("Size: " + drawWidth + " by " + drawHeight);

		pixelValue = new float [3*drawHeight][3*drawWidth];
		for (int i = 0; i < 3*drawHeight; i++)
		{
			for (int j = 0; j < 3*drawWidth; j++)
			{
				pixelValue[i][j] = 0;
			}
		}

		importer = new TerrainDataImporter (this);

		peakColor = Color.RED;
		valleyColor = Color.blue;
		flatColor = Color.GREEN;
		upperColor = Color.YELLOW;
		lowerColor = Color.CYAN;

		// initialize (default) transformation from back end to front end data

	}


	// -------------- setup the backend data ---------------------
	public void setupBackEndData (File nodeFile, File edgeFile, File layoutFile, File argMaskFile) throws Exception
	{
		// get the node list and node height first
		System.out.println("TerrainDataImporter is: " + importer.toString());
		importer.readNodeFile(nodeFile);
		
		if (edgeFile != null)
		{
			importer.readEdgeFile(edgeFile);
		}
		importer.readLayoutFile(layoutFile);
		
		if (argMaskFile != null)
		{
			importer.readMaskFile(argMaskFile);
		}
		else
		{
			mask = new boolean [3*drawHeight][3*drawWidth];
			for (int i = 0; i < 3*drawHeight; i++)
			{
				for (int j = 0; j < 3*drawWidth; j++)
				{
					mask[i][j] = true;
				}
			}
		}

		//for (int i = 0; i < nodeX.length; i++)
		//{
		//	System.out.println(nodeX[i] + ", " + nodeY[i]);
		//}
	}


	// ------------- get the pixel coordinate of the node when drawing the terrains ------------------
	public void setNodePixelCoordinate()
	{
		for (int i = 0; i < nodeID.length; i++)
		{
			if (nodeInLayout[i])
			{
				nodeXPixel[i] = Math.round( 1f*drawWidth*(nodeX[i] - leftView) / (rightView - leftView) );
				nodeYPixel[i] = Math.round( 1f*drawHeight*(topView - nodeY[i]) / (topView - bottomView) );
			}
		}
	}



	// ------------------- update the backend weight ------------------------------------------------
	public void updateBackendWeight()
	{

		float minBackendWeight = 20*Float.parseFloat(mainUI.smallInfTxt.getText()) / 100;
		float maxBackendWeight = 20*Float.parseFloat(mainUI.largeInfTxt.getText()) / 100;
		//System.out.println(minBackendWeight + " " + maxBackendWeight);
		//System.out.println(minNodeSize + " " + maxNodeSize);

		if (minBackendWeight <= 0 || maxBackendWeight <= 0)
		{
			JOptionPane.showMessageDialog(mainUI, "the max/min percentage of influence must be positive", "Error", JOptionPane.ERROR_MESSAGE);
		}


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
	public void setupAllPixelVal ()
	{
		//float minPixelVal = Float.MAX_VALUE;
		//float maxPixelVal = Float.MIN_VALUE;

		//float flatVal = 0; // the flat value will be the average value of 4 corners

		//for (int h = 0; h < drawHeight; h++)

		for (int h = -drawHeight; h < 2*drawHeight; h++)
		{
			//for (int w = 0; w < drawWidth; w++)
			for (int w = -drawWidth; w < 2*drawWidth; w++)
			{
				if (mask[h+drawHeight][w+drawWidth])
				{
					// calculating the backend coordinate of the pixel
					float xBackend = leftView + w * (rightView - leftView) / drawWidth;
					float yBackend = topView - h * (topView - bottomView) / drawHeight;

					/*
					// calculating the pixel value - original
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
					*/
					
					// calculating the pixel value - weighted average
					float pixelVal = 0;
					float sumWeight = 0;
					for (int i = 0; i < nodeID.length; i++)
					{
						if (nodeInLayout[i])
						{
							float sqrDis = CommonMath.getSquareDistance (xBackend, yBackend, nodeX[i], nodeY[i]);

							//pixelVal += nodeValue[i] * Math.exp(-sqrDis / nodeBackWeight[i]);
							pixelVal += nodeValue[i] *  nodeBackWeight[i] / (sqrDis + 1) ;
							sumWeight += nodeBackWeight[i] / (sqrDis + 1) ;
						}
					}

					//mainUI.terrainPanel.pixelValue[h][w] = pixelVal;
					pixelValue[h+drawHeight][w+drawWidth] = pixelVal / sumWeight;
				}
				
			}
		}

		setAutomaticColorThreshold();
		
		for (int h = -drawHeight; h < 2*drawHeight; h++)
		{
			//for (int w = 0; w < drawWidth; w++)
			for (int w = -drawWidth; w < 2*drawWidth; w++)
			{
				if (!mask[h+drawHeight][w+drawWidth])
				{
					pixelValue[h+drawHeight][w+drawWidth] = this.flatValue;
				}
			}
		}
		
		System.out.println("leftView: " + leftView + ", topView: " + topView + ", drawHeight: " + drawHeight + ", upleftY: " + upLeftY + ", upleftX: " + upLeftX );
		System.out.println("topLeft value: " + pixelValue[upLeftY][upLeftX]);
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

			for (int h = 0; h < drawHeight; h++)
				//for (int h = -drawHeight; h < 2*drawHeight; h++)
			{
				for (int w = 0; w < drawWidth - 1; w++)
					//for (int w = -drawWidth; w < 2*drawWidth; w++)
				{
					writeFile.format(pixelValue[h+upLeftY][w+upLeftX] + "\t");
				}
				writeFile.format(pixelValue[h+upLeftY][drawWidth - 1+upLeftX] + "\r\n");
			}

			writeFile.close();
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}



	//------------------ set up the pixel color table from pixel value --------------------------
	public void setupDrawingPixelColor()
	{
		for (int h = 0; h < drawHeight; h++)
			//for (int h = -drawHeight; h < 2*drawHeight; h++)
		{
			for (int w = 0; w < drawWidth; w++)
				//for (int w = -drawWidth; w < 2*drawWidth; w++)
			{
				//float pixelVal = mainUI.terrainPanel.pixelValue[h][w];
				float pixelVal = pixelValue[h+upLeftY][w+upLeftX];

				// calculate the pixel color

				if (pixelVal >= peakValue)
				{
					mainUI.terrainPanel.pixelColor[h][w] = peakColor;
					//mainUI.terrainPanel.pixelColor[h+drawHeight][w+drawWidth] = peakColor;
				}

				else if (pixelVal > upperValue && pixelVal < peakValue)
				{
					float newR = 1f*peakColor.getRed() - (peakColor.getRed()-upperColor.getRed()) * (peakValue-pixelVal) / (peakValue-upperValue);
					if (newR > 255) {newR = 255;}
					if (newR < 0) {newR = 0;}

					float newG = 1f*peakColor.getGreen() - (peakColor.getGreen()-upperColor.getGreen()) * (peakValue-pixelVal) / (peakValue-upperValue);
					if (newG > 255) {newG = 255;}
					if (newG < 0) {newG = 0;}

					float newB = 1f*peakColor.getBlue() - (peakColor.getBlue()-upperColor.getBlue()) * (peakValue-pixelVal) / (peakValue-upperValue);
					if (newB > 255) {newB = 255;}
					if (newB < 0) {newB = 0;}

					//System.out.println("backend x: " + xBackend + ", backend y: " + yBackend + ", pixelVal: " + pixelVal +
					//		", distance: " + CommonMath.getSquareDistance (xBackend, yBackend, nodeX[0], nodeY[0]) +
					//		" Color: (" + newR + "," + newG + "," + newB + ")");

					mainUI.terrainPanel.pixelColor[h][w] = new Color (Math.round(newR), Math.round(newG), Math.round(newB));
					//mainUI.terrainPanel.pixelColor[h+drawHeight][w+drawWidth] = new Color (Math.round(newR), Math.round(newG), Math.round(newB));
				}

				else if (pixelVal >= flatValue && pixelVal < upperValue)
				{
					float newR = 1f*upperColor.getRed() - (upperColor.getRed()-flatColor.getRed()) * (upperValue-pixelVal) / (upperValue-flatValue);
					if (newR > 255) {newR = 255;}
					if (newR < 0) {newR = 0;}

					float newG = 1f*upperColor.getGreen() - (upperColor.getGreen()-flatColor.getGreen()) * (upperValue-pixelVal) / (upperValue-flatValue);
					if (newG > 255) {newG = 255;}
					if (newG < 0) {newG = 0;}

					float newB = 1f*upperColor.getBlue() - (upperColor.getBlue()-flatColor.getBlue()) * (upperValue-pixelVal) / (upperValue-flatValue);
					if (newB > 255) {newB = 255;}
					if (newB < 0) {newB = 0;}

					mainUI.terrainPanel.pixelColor[h][w] = new Color (Math.round(newR), Math.round(newG), Math.round(newB));
					//mainUI.terrainPanel.pixelColor[h+drawHeight][w+drawWidth] = new Color (Math.round(newR), Math.round(newG), Math.round(newB));
				}

				else if (pixelVal >= lowerValue && pixelVal < flatValue)
				{
					float newR = 1f*flatColor.getRed() - (flatColor.getRed()-lowerColor.getRed()) * (flatValue-pixelVal) / (flatValue-lowerValue);
					if (newR > 255) {newR = 255;}
					if (newR < 0) {newR = 0;}

					float newG = 1f*flatColor.getGreen() - (flatColor.getGreen()-lowerColor.getGreen()) * (flatValue-pixelVal) / (flatValue-lowerValue);
					if (newG > 255) {newG = 255;}
					if (newG < 0) {newG = 0;}

					float newB = 1f*flatColor.getBlue() - (flatColor.getBlue()-lowerColor.getBlue()) * (flatValue-pixelVal) / (flatValue-lowerValue);
					if (newB > 255) {newB = 255;}
					if (newB < 0) {newB = 0;}

					//System.out.println("backend x: " + xBackend + ", backend y: " + yBackend + ", pixelVal: " + pixelVal +
					//		", distance: " + CommonMath.getSquareDistance (xBackend, yBackend, nodeX[0], nodeY[0]) +
					//		" Color: (" + newR + "," + newG + "," + newB + ")");

					mainUI.terrainPanel.pixelColor[h][w] = new Color (Math.round(newR), Math.round(newG), Math.round(newB));
					//mainUI.terrainPanel.pixelColor[h+drawHeight][w+drawWidth] = new Color (Math.round(newR), Math.round(newG), Math.round(newB));
				}

				else if (pixelVal >= valleyValue && pixelVal < lowerValue)
				{
					float newR = 1f*lowerColor.getRed() - (lowerColor.getRed()-valleyColor.getRed()) * (lowerValue-pixelVal) / (lowerValue-valleyValue);
					if (newR > 255) {newR = 255;}
					if (newR < 0) {newR = 0;}

					float newG = 1f*lowerColor.getGreen() - (lowerColor.getGreen()-valleyColor.getGreen()) * (lowerValue-pixelVal) / (lowerValue-valleyValue);
					if (newG > 255) {newG = 255;}
					if (newG < 0) {newG = 0;}

					float newB = 1f*lowerColor.getBlue() - (lowerColor.getBlue()-valleyColor.getBlue()) * (lowerValue-pixelVal) / (lowerValue-valleyValue);
					if (newB > 255) {newB = 255;}
					if (newB < 0) {newB = 0;}

					//System.out.println("backend x: " + xBackend + ", backend y: " + yBackend + ", pixelVal: " + pixelVal +
					//		", distance: " + CommonMath.getSquareDistance (xBackend, yBackend, nodeX[0], nodeY[0]) +
					//		" Color: (" + newR + "," + newG + "," + newB + ")");

					mainUI.terrainPanel.pixelColor[h][w] = new Color (Math.round(newR), Math.round(newG), Math.round(newB));
					//mainUI.terrainPanel.pixelColor[h+drawHeight][w+drawWidth] = new Color (Math.round(newR), Math.round(newG), Math.round(newB));
				}

				else if (pixelVal < valleyValue)
				{
					mainUI.terrainPanel.pixelColor[h][w] = valleyColor;
					//mainUI.terrainPanel.pixelColor[h+drawHeight][w+drawWidth] = valleyColor;
				}

			}
		}
	}



	// ------------------ set color threshold from the existing drawing terrain data ---------------------------
	public void setAutomaticColorThreshold ()
	{
		float maxPixelVal = -Float.MAX_VALUE;
		float minPixelVal = Float.MAX_VALUE;

		for (int h = 0; h < drawHeight; h++)
		{
			for (int w = 0; w < drawWidth; w++)
			{
				if (mask[upLeftY+h][upLeftX+w])
				{
					if (pixelValue[upLeftY+h][upLeftX+w] > maxPixelVal)
					{
						maxPixelVal = pixelValue[upLeftY+h][upLeftX+w];
					}
					if (pixelValue[upLeftY+h][upLeftX+w] < minPixelVal)
					{
						minPixelVal = pixelValue[upLeftY+h][upLeftX+w];
					}
				}
			}
		}

		peakValue = maxPixelVal;
		valleyValue = minPixelVal;
		flatValue = (peakValue + valleyValue) / 2;
		upperValue = (peakValue + flatValue) / 2;
		lowerValue = (valleyValue + flatValue) / 2;

		mainUI.peakHeightTxt.setText(String.format("%.02f", peakValue));
		mainUI.valleyHeightTxt.setText(String.format("%.02f",valleyValue));
		mainUI.flatHeightTxt.setText(String.format("%.02f",flatValue));
		mainUI.upperHeighTxt.setText(String.format("%.02f",upperValue));
		mainUI.lowerHeightTxt.setText(String.format("%.02f",lowerValue));
	}


	// -------------- setup the color bar for the terrain --------------------------
	public void setColorBar ()
	{
		int colorBarLength = mainUI.colorBarPanel.getHeight();
		int peakPt = 0;
		int valleyPt = colorBarLength-1;
		int flatPt = colorBarLength / 2;
		int upperPt = colorBarLength / 4;
		int lowerPt = (3*colorBarLength) / 4;

		for (int h = 0; h < mainUI.colorBarPanel.getHeight(); h++)
		{
			float newR = 0;
			float newG = 0;
			float newB = 0;

			if (h <= upperPt )
			{
				newR = 1f*peakColor.getRed() + (h - peakPt) * (peakColor.getRed()-upperColor.getRed()) / (peakPt-upperPt);
				if (newR > 255) {newR = 255;}
				if (newR < 0) {newR = 0;}
				newG = 1f*peakColor.getGreen() + (h - peakPt) * (peakColor.getGreen()-upperColor.getGreen()) / (peakPt-upperPt);
				if (newG > 255) {newG = 255;}
				if (newG < 0) {newG = 0;}
				newB = 1f*peakColor.getBlue() + (h - peakPt) * (peakColor.getBlue()-upperColor.getBlue()) / (peakPt-upperPt);
				if (newB > 255) {newB = 255;}
				if (newB < 0) {newB = 0;}
			}

			else if (h <= flatPt && h > upperPt )
			{
				newR = 1f*upperColor.getRed() + (h - upperPt) * (upperColor.getRed()-flatColor.getRed()) / (upperPt-flatPt);
				if (newR > 255) {newR = 255;}
				if (newR < 0) {newR = 0;}
				newG = 1f*upperColor.getGreen() + (h - upperPt) * (upperColor.getGreen()-flatColor.getGreen()) / (upperPt-flatPt);
				if (newG > 255) {newG = 255;}
				if (newG < 0) {newG = 0;}
				newB = 1f*upperColor.getBlue() + (h - upperPt) * (upperColor.getBlue()-flatColor.getBlue()) / (upperPt-flatPt);
				if (newB > 255) {newB = 255;}
				if (newB < 0) {newB = 0;}
			}

			else if (h <= lowerPt && h > flatPt )
			{
				newR = 1f*flatColor.getRed() + (h - flatPt) * (flatColor.getRed()-lowerColor.getRed()) / (flatPt-lowerPt);
				if (newR > 255) {newR = 255;}
				if (newR < 0) {newR = 0;}
				newG = 1f*flatColor.getGreen() + (h - flatPt) * (flatColor.getGreen()-lowerColor.getGreen()) / (flatPt-lowerPt);
				if (newG > 255) {newG = 255;}
				if (newG < 0) {newG = 0;}
				newB = 1f*flatColor.getBlue() + (h - flatPt) * (flatColor.getBlue()-lowerColor.getBlue()) / (flatPt-lowerPt);
				if (newB > 255) {newB = 255;}
				if (newB < 0) {newB = 0;}
			}

			else
			{
				newR = 1f*lowerColor.getRed() + (h - lowerPt) * (lowerColor.getRed()-valleyColor.getRed()) / (lowerPt-valleyPt);
				if (newR > 255) {newR = 255;}
				if (newR < 0) {newR = 0;}
				newG = 1f*lowerColor.getGreen() + (h - lowerPt) * (lowerColor.getGreen()-valleyColor.getGreen()) / (lowerPt-valleyPt);
				if (newG > 255) {newG = 255;}
				if (newG < 0) {newG = 0;}
				newB = 1f*lowerColor.getBlue() + (h - lowerPt) * (lowerColor.getBlue()-valleyColor.getBlue()) / (lowerPt-valleyPt);
				if (newB > 255) {newB = 255;}
				if (newB < 0) {newB = 0;}
			}

			for (int w = 0; w < mainUI.colorBarPanel.getWidth(); w++)
			{
				mainUI.colorBarPanel.pixelColor[h][w] = new Color (Math.round(newR), Math.round(newG), Math.round(newB));
			}

		}

	}


	// ------------- find the gene index from the pixel coordinate in mouse click event ------------------------------
	public int findClosestGene (int mouseX, int mouseY)
	{
		int closestIndex = -1;

		if (mouseX >= 0 && mouseY >= 0)
		{
			float smallestDis = Float.MAX_VALUE;

			for (int i = 0; i < nodeID.length; i++)
			{
				float dist = (1f*mouseX - nodeXPixel[i]) * (1f*mouseX - nodeXPixel[i]) + 
						(1f*mouseY - nodeYPixel[i]) * (1f*mouseY - nodeYPixel[i]);
				if (dist < smallestDis)
				{
					smallestDis = dist;
					closestIndex = i;
				}
			}

			System.out.println("Pixel val: " + pixelValue[upLeftY+mouseY][upLeftX+mouseX]);

			if (smallestDis > 36) // the closest gene is still too far from the click point
			{
				closestIndex = -1;
			}
		}

		return closestIndex;
	}
	
	
	// --------------------- save genes in peak regions. Peaks are defined by some thresholds -------------------
	public void saveGeneInPeak (float thres, String outputFilePath)
	{
		try
		{
			Formatter writeOutput = new Formatter (new File(outputFilePath));
			for (int node = 0; node < nodeID.length; node++)
			{
				if (nodeInLayout[node])
				{
					float xBackend = nodeX[node];
					float yBackend = nodeY[node];
					
					float cumulativeVal = 0;
					for (int i = 0; i < nodeID.length; i++)
					{
						if (nodeInLayout[i])
						{
							float sqrDis = CommonMath.getSquareDistance (xBackend, yBackend, nodeX[i], nodeY[i]);

							//pixelVal += nodeValue[i] * Math.exp(-sqrDis / nodeBackWeight[i]);
							cumulativeVal += nodeValue[i] * nodeBackWeight[i] / (sqrDis + 1) ;
						}
					}
					
					if (cumulativeVal > thres)
					{
						writeOutput.format(nodeID[node] + "\r\n");
					}
				}
				
			}
			
			writeOutput.close();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
