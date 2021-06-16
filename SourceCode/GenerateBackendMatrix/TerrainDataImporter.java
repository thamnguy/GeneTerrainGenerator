package GenerateBackendMatrix;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

// This class only handle importing user data. It is a component of GeneTerrainData class
// write it here for easy debug

public class TerrainDataImporter 
{

	final GeneTerrainData theTerrainData;
	public TerrainDataImporter(GeneTerrainData argTerrainData) 
	{
		theTerrainData = argTerrainData;
	}

	// -------------------- read the node file -----------------------------
	public void readNodeFile(File nodeFile) throws Exception
	{
		//try
		//{
		// read the node file
		Scanner readNodeFile = new Scanner (nodeFile);
		Vector <String> tempNodeID = new Vector <String>();
		Vector <String> tempNodeVal = new Vector <String>();
		while (readNodeFile.hasNextLine())
		{
			String line = readNodeFile.nextLine();
			String [] component = line.split("\t");
			tempNodeID.add(component[0]);
			tempNodeVal.add(component[1]);
		}
		readNodeFile.close();

		// initialize the back-end and front-end data array
		theTerrainData.nodeID = new String [tempNodeID.size()];
		theTerrainData.nodeID2Index = new HashMap <String, Integer>();
		theTerrainData.nodeX = new float [tempNodeID.size()];
		theTerrainData.nodeY = new float [tempNodeID.size()];
		theTerrainData.nodeValue = new float [tempNodeID.size()];
		theTerrainData.nodeSize = new float [tempNodeID.size()];
		theTerrainData.nodeInLayout = new boolean [tempNodeID.size()];
		theTerrainData.nodeXPixel = new int [tempNodeID.size()];
		theTerrainData.nodeYPixel = new int [tempNodeID.size()];
		theTerrainData.nodeBackWeight = new float [tempNodeID.size()];
		theTerrainData.nodeRadius = new int [tempNodeID.size()];
		//theTerrainData.nodeColor = new Color [tempNodeID.size()];

		for (int i = 0; i < theTerrainData.nodeID.length; i++)
		{
			theTerrainData.nodeID[i] = tempNodeID.elementAt(i);
			theTerrainData.nodeID2Index.put(theTerrainData.nodeID[i], i);
			theTerrainData.nodeX[i] = 0f;
			theTerrainData.nodeY[i] = 0f;
			theTerrainData.nodeValue[i] = Float.parseFloat(tempNodeVal.elementAt(i));
			theTerrainData.nodeSize[i] = 0f;
			theTerrainData.nodeInLayout[i] = false;
			theTerrainData.nodeXPixel[i] = 0;
			theTerrainData.nodeYPixel[i] = 0;
			theTerrainData.nodeBackWeight[i] = 0;
			theTerrainData.nodeRadius[i] = 0;
			//theTerrainData.nodeColor[i] = Color.BLUE;
		}

		//}

		System.out.println();
		//catch (Exception e)
		//{
		//	e.printStackTrace();
		//}
	}




	// --------------------- read the layout file ---------------------------
	public void readLayoutFile (File layoutFile) throws Exception
	{
		//try
		//{
		Scanner readLayoutFile = new Scanner (layoutFile);

		while (readLayoutFile.hasNextLine())
		{
			String line = readLayoutFile.nextLine();
			String [] component = line.split("\t");

			if (component.length == 4)
			{
				if (theTerrainData.nodeID2Index.get(component[0]) != null) // first column is node ID
				{
					int nodeIndex = theTerrainData.nodeID2Index.get(component[0]);
					theTerrainData.nodeInLayout[nodeIndex] = true;
					theTerrainData.nodeX[nodeIndex] = Float.parseFloat(component[1]); // second column is node x
					theTerrainData.nodeY[nodeIndex] = Float.parseFloat(component[2]); // third column is node y
					theTerrainData.nodeSize[nodeIndex] = Float.parseFloat(component[3]); // forth column is node size
					theTerrainData.nodeBackWeight[nodeIndex] = 1f*theTerrainData.nodeSize[nodeIndex];

				}
			}
		}

		readLayoutFile.close();

		theTerrainData.maxNodeSize = Float.MIN_VALUE;
		theTerrainData.minNodeSize = Float.MAX_VALUE;

		// rescale the x and y coordinate between -8 and 8
		float maxX = CommonMath.max(theTerrainData.nodeX, theTerrainData.nodeInLayout);
		float minX = CommonMath.min(theTerrainData.nodeX, theTerrainData.nodeInLayout);
		float maxY = CommonMath.max(theTerrainData.nodeY, theTerrainData.nodeInLayout);
		float minY = CommonMath.min(theTerrainData.nodeY, theTerrainData.nodeInLayout);

		for (int i = 0; i < theTerrainData.nodeID.length; i++)
		{
			if (theTerrainData.nodeSize[i] > theTerrainData.maxNodeSize)
			{
				theTerrainData.maxNodeSize = theTerrainData.nodeSize[i];
			}
			if (theTerrainData.nodeSize[i] < theTerrainData.minNodeSize)
			{
				theTerrainData.minNodeSize = theTerrainData.nodeSize[i];
			}

			if (theTerrainData.nodeInLayout[i])
			{
				theTerrainData.nodeX[i] = -8 + (theTerrainData.nodeX[i] - minX) / (maxX - minX) *16f;
				theTerrainData.nodeY[i] = -8 + (theTerrainData.nodeY[i] - minY) / (maxY - minY) *16f;
			}
			else
			{
				theTerrainData.nodeX[i] = 0;
				theTerrainData.nodeY[i] = 0;
			}
		}

		//}

		//catch (Exception e)
		//{
		//	e.printStackTrace();
		//}
		System.out.println();
	}

}
