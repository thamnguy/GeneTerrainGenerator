import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class Terrain2XML 
{
	GeneTerrainMainUI mainUI;

	public Terrain2XML (GeneTerrainMainUI argUI)
	{
		mainUI = argUI;
	}


	public void write2XML( String fileName )
	{
		try
		{
			Formatter xmlWriter = new Formatter( new File(fileName) );

			xmlWriter.format("%s\r\n", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");

			xmlWriter.format("%s\r\n", "<GeneTerrain>");

			writeMetaData(xmlWriter);
			writeInputFile(xmlWriter);
			writeBackendParam(xmlWriter);
			writeTransformParam(xmlWriter);
			writeNodeInfo(xmlWriter);
			writeEdgeInfo(xmlWriter);


			xmlWriter.format("%s\r\n", "</GeneTerrain>");

			xmlWriter.close();
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	//------------------ write edge info -----------------------
	private void writeEdgeInfo (Formatter writer)
	{
		writer.format("\t<Edges>\r\n");
		if (mainUI.terrainData.edge != null)
		{
			for (int i = 0; i < mainUI.terrainData.edge.length; i++)
			{
				Edge edge = mainUI.terrainData.edge[i];
				writer.format("\t\t<Edge>\r\n");
				writer.format("\t\t\t<BeginID>" + edge.beginIndex + "</BeginID>\r\n");
				writer.format("\t\t\t<EndID>" + edge.endIndex + "</EndID>\r\n");
				writer.format("\t\t\t<Strength>" + edge.strength + "</Strength>\r\n");
				writer.format("\t\t\t<IsDirected>" + edge.isDirected + "</IsDirected>\r\n");
				writer.format("\t\t\t<Annotation>" + edge.annotation + "</Annotation>\r\n");
				writer.format("\t\t</Edge>\r\n");
			}
		}
		
		writer.format("\t</Edges>\r\n");
	}
	
	
	// ---------------- write node info ----------------------
	private void writeNodeInfo (Formatter writer)
	{
		writer.format("\t<Nodes>\r\n");
		for (int i = 0; i < mainUI.terrainData.nodeID.length; i++)
		{
			writer.format("\t\t<Node>\r\n");
			writer.format("\t\t\t<NodeID>" + mainUI.terrainData.nodeID2Index.get(mainUI.terrainData.nodeID[i]) + "</NodeID>\r\n");
			writer.format("\t\t\t<NodeName>" + mainUI.terrainData.nodeID[i] + "</NodeName>\r\n");
			writer.format("\t\t\t<NodeBackendX>" + mainUI.terrainData.nodeX[i] + "</NodeBackendX>\r\n");
			writer.format("\t\t\t<NodeBackendY>" + mainUI.terrainData.nodeY[i] + "</NodeBackendY>\r\n");
			writer.format("\t\t\t<InputValue>" + mainUI.terrainData.nodeValue[i] + "</InputValue>\r\n");
			writer.format("\t\t\t<InputWeight>" + mainUI.terrainData.nodeSize[i] + "</InputWeight>\r\n");
			writer.format("\t\t\t<BackendWeight>" + mainUI.terrainData.nodeBackWeight[i] + "</BackendWeight>\r\n");
			writer.format("\t\t\t<NodeFrontendX>" + mainUI.terrainData.nodeXPixel[i] + "</NodeFrontendX>\r\n");
			writer.format("\t\t\t<NodeFrontendY>" + mainUI.terrainData.nodeYPixel[i] + "</NodeFrontendY>\r\n");
			writer.format("\t\t</Node>\r\n");
		}
		
		writer.format("\t</Nodes>\r\n");
	}


	// ------------ write front end display parameters ----------------
	private void writeTransformParam (Formatter writer)
	{
		// front end display
		writer.format("\t<frontEndDisplay>\r\n");
		writer.format("\t\t%s\r\n", "<displayWidth>" + mainUI.terrainData.drawWidth + "</displayWidth>");
		writer.format("\t\t%s\r\n", "<displayHeight>" + mainUI.terrainData.drawHeight + "</displayHeight>");
		writer.format("\t\t%s\r\n", "<bufferMatrixSize>" + 2700 + "</bufferMatrixSize>");		
		writer.format("\t\t%s\r\n", "<upLeftX>" + mainUI.terrainData.upLeftX + "</upLeftX>");
		writer.format("\t\t%s\r\n", "<upLeftY>" + mainUI.terrainData.upLeftY + "</upLeftY>");
		writer.format("\t</frontEndDisplay>\r\n");

		// color mapping
		writer.format("\t<colorMap>\r\n");

		// peak color
		writer.format("\t\t<peakColor>\r\n");
		writer.format("\t\t\t%s\r\n", "<red>" + mainUI.terrainData.peakColor.getRed() + "</red>");
		writer.format("\t\t\t%s\r\n", "<green>" + mainUI.terrainData.peakColor.getGreen() + "</green>");
		writer.format("\t\t\t%s\r\n", "<blue>" + mainUI.terrainData.peakColor.getBlue() + "</blue>");
		writer.format("\t\t\t%s\r\n", "<backEndVal>" + mainUI.terrainData.peakValue + "</backEndVal>");
		writer.format("\t\t</peakColor>\r\n");

		// upper color
		writer.format("\t\t<upperColor>\r\n");
		writer.format("\t\t\t%s\r\n", "<red>" + mainUI.terrainData.upperColor.getRed() + "</red>");
		writer.format("\t\t\t%s\r\n", "<green>" + mainUI.terrainData.upperColor.getGreen() + "</green>");
		writer.format("\t\t\t%s\r\n", "<blue>" + mainUI.terrainData.upperColor.getBlue() + "</blue>");
		writer.format("\t\t\t%s\r\n", "<backEndVal>" + mainUI.terrainData.upperValue + "</backEndVal>");
		writer.format("\t\t</upperColor>\r\n");

		// flat color
		writer.format("\t\t<flatColor>\r\n");
		writer.format("\t\t\t%s\r\n", "<red>" + mainUI.terrainData.flatColor.getRed() + "</red>");
		writer.format("\t\t\t%s\r\n", "<green>" + mainUI.terrainData.flatColor.getGreen() + "</green>");
		writer.format("\t\t\t%s\r\n", "<blue>" + mainUI.terrainData.flatColor.getBlue() + "</blue>");
		writer.format("\t\t\t%s\r\n", "<backEndVal>" + mainUI.terrainData.flatValue + "</backEndVal>");
		writer.format("\t\t</flatColor>\r\n");

		// lower color
		writer.format("\t\t<lowerColor>\r\n");
		writer.format("\t\t\t%s\r\n", "<red>" + mainUI.terrainData.lowerColor.getRed() + "</red>");
		writer.format("\t\t\t%s\r\n", "<green>" + mainUI.terrainData.lowerColor.getGreen() + "</green>");
		writer.format("\t\t\t%s\r\n", "<blue>" + mainUI.terrainData.lowerColor.getBlue() + "</blue>");
		writer.format("\t\t\t%s\r\n", "<backEndVal>" + mainUI.terrainData.lowerValue + "</backEndVal>");
		writer.format("\t\t</lowerColor>\r\n");

		// valley color
		writer.format("\t\t<valleyColor>\r\n");
		writer.format("\t\t\t%s\r\n", "<red>" + mainUI.terrainData.valleyColor.getRed() + "</red>");
		writer.format("\t\t\t%s\r\n", "<green>" + mainUI.terrainData.valleyColor.getGreen() + "</green>");
		writer.format("\t\t\t%s\r\n", "<blue>" + mainUI.terrainData.valleyColor.getBlue() + "</blue>");
		writer.format("\t\t\t%s\r\n", "<backEndVal>" + mainUI.terrainData.valleyValue + "</backEndVal>");
		writer.format("\t\t</valleyColor>\r\n");

		writer.format("\t</colorMap>\r\n");
	}


	// ------------------- write backend parameters --------------------
	private void writeBackendParam (Formatter writer)
	{
		writer.format("\t<backendParams>\r\n");

		// default view windows in the backend
		writer.format("\t\t<defaultBackendWindow>\r\n");
		writer.format("\t\t\t%s\r\n", "<left>" + (CommonMath.min(mainUI.terrainData.nodeX) - 2) + "</left>");
		writer.format("\t\t\t%s\r\n", "<right>" + (CommonMath.max(mainUI.terrainData.nodeX) + 2) + "</right>");
		writer.format("\t\t\t%s\r\n", "<top>" + (CommonMath.min(mainUI.terrainData.nodeY) - 2) + "</top>");
		writer.format("\t\t\t%s\r\n", "<bottom>" + (CommonMath.max(mainUI.terrainData.nodeY) + 2) + "</bottom>");
		writer.format("\t\t</defaultBackendWindow>\r\n");

		// current view windows in the backend
		writer.format("\t\t<currentBackendWindow>\r\n");
		writer.format("\t\t\t%s\r\n", "<left>" + mainUI.terrainData.leftView + "</left>");
		writer.format("\t\t\t%s\r\n", "<right>" +  mainUI.terrainData.rightView + "</right>");
		writer.format("\t\t\t%s\r\n", "<top>" +  mainUI.terrainData.topView + "</top>");
		writer.format("\t\t\t%s\r\n", "<bottom>" +  mainUI.terrainData.bottomView + "</bottom>");
		writer.format("\t\t</currentBackendWindow>\r\n");

		// max and min node influence in the terrain
		writer.format("\t\t<nodeInfluence>\r\n");
		writer.format("\t\t\t%s\r\n", "<maxNodePercentage>" + mainUI.terrainData.maxNodeSize + "</maxNodePercentage>");
		writer.format("\t\t\t%s\r\n", "<minNodePercentage>" + mainUI.terrainData.minNodeSize + "</minNodePercentage>");
		writer.format("\t\t</nodeInfluence>\r\n");

		writer.format("\t</backendParams>\r\n");
	}


	// -------------------- write input file name -----------------------
	private void writeInputFile(Formatter writer)
	{
		writer.format("\t<inputFiles>\r\n");

		writer.format("\t\t%s\r\n", "<inputExpressionFile>" + mainUI.geneListFile.getName() + "</inputExpressionFile>");
		writer.format("\t\t%s\r\n", "<layoutFile>" + mainUI.layoutFile.getName() + "</layoutFile>");
		if (mainUI.connectionFile == null)
		{
			writer.format("\t\t%s\r\n", "<networkFile></networkFile>");
		}
		else
		{
			writer.format("\t\t%s\r\n", "<networkFile>" + mainUI.connectionFile.getName() + "</networkFile>");
		}

		writer.format("\t</inputFiles>\r\n");
	}


	//----------------- write the input file, terrain name, etc. ----------------------
	private void writeMetaData (Formatter writer)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  

		writer.format("\t<metadata>\r\n");

		writer.format("\t\t%s\r\n", "<terrainName>" + mainUI.currentTerrainName + "</terrainName>");
		writer.format("\t\t%s\r\n", "<createAt>" + dtf.format(now) + "</createAt>");

		writer.format("\t</metadata>\r\n"); 
	}



}
