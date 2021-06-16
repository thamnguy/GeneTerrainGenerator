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
				//System.out.println(line);
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
			
			theTerrainData.peakValue = CommonMath.max(theTerrainData.nodeValue);
			theTerrainData.valleyValue = Math.min(0, CommonMath.min(theTerrainData.nodeValue));
		//}

			System.out.println();
		//catch (Exception e)
		//{
		//	e.printStackTrace();
		//}
	}


	// ------------- read edge file ------------------------
	public void readEdgeFile(File edgeFile) throws Exception
	{
		String line = "";
		//try
		//{
			// read the edge file
			Scanner readEdgeFile = new Scanner (edgeFile);
			Vector <String> tempBeginID = new Vector <String>();
			Vector <String> tempEndID = new Vector<String>();
			Vector <String> tempEdgeWeight = new Vector<String>();
			Vector <String> tempDirection = new Vector<String>();
			Vector <String> tempAnnotation = new Vector<String>();

			int numCol = 0;

			// the first line tells how many column
			if (readEdgeFile.hasNextLine())
			{
				line = readEdgeFile.nextLine();
				String [] component = line.split("\t");
				numCol = component.length;

				if (numCol >= 2) // only 2 columns: <begin Node, end Node>
				{
					if ( theTerrainData.nodeID2Index.get(component[0]) != null && theTerrainData.nodeID2Index.get(component[1]) != null)
					{
						tempBeginID.add(component[0]);
						tempEndID.add(component[1]);

						if (numCol >= 3) // the third column is edge weight
						{
							tempEdgeWeight.add(component[2]);
							if (numCol >= 4) // the forth column is edge directionality
							{
								tempDirection.add(component[3]);
								if (numCol >= 5) // the fifth column is edge annotation
								{
									tempAnnotation.add(component[5]);
								}
							}
						}
					}
				}
			}

			//process the other lines
			while (readEdgeFile.hasNextLine())
			{
				line = readEdgeFile.nextLine();
				String [] component = line.split("\t");
				if (component.length == numCol)
				{
					if (numCol >= 2)
					{
						if ( theTerrainData.nodeID2Index.get(component[0]) != null && theTerrainData.nodeID2Index.get(component[1]) != null)
						{
							tempBeginID.add(component[0]);
							tempEndID.add(component[1]);

							if (numCol >= 3)
							{
								tempEdgeWeight.add(component[2]);
								if (numCol >= 4)
								{
									tempDirection.add(component[3]);
									if (numCol >= 5)
									{
										tempAnnotation.add(component[5]);
									}
								}
							}
						}
					}
				}
			}

			readEdgeFile.close();

			// after reading the edge file, import the data file intou the edge structure
			theTerrainData.edge = new Edge [tempBeginID.size()];
			switch (numCol) // the number of columns decide which edge constructor to use
			{
			case 2:
			{
				for (int i = 0; i < theTerrainData.edge.length; i++)
				{
					theTerrainData.edge[i] = new Edge (theTerrainData.nodeID2Index.get(tempBeginID.elementAt(i)), 
							theTerrainData.nodeID2Index.get(tempEndID.elementAt(i)));
				}
				break;
			}

			case 3:
			{
				for (int i = 0; i < theTerrainData.edge.length; i++)
				{
					theTerrainData.edge[i] = new Edge (theTerrainData.nodeID2Index.get(tempBeginID.elementAt(i)), 
							theTerrainData.nodeID2Index.get(tempEndID.elementAt(i)), 
							Float.parseFloat(tempEdgeWeight.elementAt(i)));
				}
				break;
			}

			case 4:
			{
				for (int i = 0; i < theTerrainData.edge.length; i++)
				{
					theTerrainData.edge[i] = new Edge (theTerrainData.nodeID2Index.get(tempBeginID.elementAt(i)), 
							theTerrainData.nodeID2Index.get(tempEndID.elementAt(i)), 
							Float.parseFloat(tempEdgeWeight.elementAt(i)), Boolean.parseBoolean(tempDirection.elementAt(i)));
				}
				break;
			}

			case 5:
			{
				for (int i = 0; i < theTerrainData.edge.length; i++)
				{
					theTerrainData.edge[i] = new Edge (theTerrainData.nodeID2Index.get(tempBeginID.elementAt(i)), 
							theTerrainData.nodeID2Index.get(tempEndID.elementAt(i)), 
							Float.parseFloat(tempEdgeWeight.elementAt(i)), Boolean.parseBoolean(tempDirection.elementAt(i)));
					theTerrainData.edge[i].setAnnotation(tempAnnotation.elementAt(i));
				}
				break;
			}
			}

		//}

		//catch (Exception e)
		//{
			//System.out.println(line);
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
			
			// preset the viewing window in the terrainData
			theTerrainData.topView = CommonMath.max(theTerrainData.nodeY) + 2;
			theTerrainData.bottomView = CommonMath.min(theTerrainData.nodeY)  - 2;
			theTerrainData.leftView = CommonMath.min(theTerrainData.nodeX) - 2;
			theTerrainData.rightView = CommonMath.max(theTerrainData.nodeX) + 2;
		//}
		
		//catch (Exception e)
		//{
		//	e.printStackTrace();
		//}
			System.out.println();
	}
	
	
	// ----------------------- read mask file ---------------------------
	public void readMaskFile(File maskFile) throws Exception
	{
		theTerrainData.mask = new boolean [3*theTerrainData.drawHeight][3*theTerrainData.drawWidth];
		for (int i = 0; i < 3*theTerrainData.drawHeight; i++)
		{
			for (int j = 0; j < 3*theTerrainData.drawWidth; j++)
			{
				theTerrainData.mask[i][j] = false;
			}
		}
		
		Scanner readMaskFile = new Scanner (maskFile);
		int rowNum = 0;
		
		while (readMaskFile.hasNextLine())
		{
			String line = readMaskFile.nextLine();
			
			for (int i = 0; i < line.length(); i++)
			{
				if (line.charAt(i) == '1')
				{
					theTerrainData.mask[theTerrainData.drawHeight+rowNum][theTerrainData.drawWidth+i] = true;
				}
			}
			rowNum++;
		}
	}

}
