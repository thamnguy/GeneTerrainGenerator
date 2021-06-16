import java.io.File;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class GDC_TCGA_GBM_MultiTerrain 
{
	final int NUM_GENE = 40;
	final int NUM_SAMPLE = 108;
	
	GeneTerrainMainUI testUI ;
	String[] className;
	String [] geneName;
	float [][] expression;
	int [] survival;

	String layoutFileName;
	String outputFolderName;
	
	NumberFormat usFormat;
	
	public GDC_TCGA_GBM_MultiTerrain(String argLayoutFileName, String argOutputFolderName, float argWidthScale)
	{
		usFormat = NumberFormat.getInstance(Locale.US);
		layoutFileName = argLayoutFileName;
		outputFolderName = argOutputFolderName;
		//testUI = new GeneTerrainMainUI();
		className = new String [NUM_SAMPLE];
		geneName = new String [NUM_GENE];
		survival = new int [NUM_SAMPLE];
		expression = new float [NUM_SAMPLE] [NUM_GENE];
		
		readData();
		
		writeExpressionFile();
		
		testUI = new GeneTerrainMainUI();
		for (int i = 0; i < NUM_SAMPLE; i++)
		{
			testUI.geneListFile = new File (
					"C:/RWorkspace/TCGA_GBM/GDC_Pager_randForest40/TerrainData/" + i + "_" + className[i] + "_" + survival[i] + ".txt");
			testUI.connectionFile = new File ("C:/RWorkspace/TCGA_GBM/GDC_Kegg_Pager/PPI.txt");
			testUI.layoutFile = new File(layoutFileName);
			
			//testUI.terrainData.scaleNodeWidth(argWidthScale);
			
			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("2.0");
			
			testUI.eventHandler.createTerrainEvent(argWidthScale);
			
			//testUI.peakHeightTxt.setText("1.0");
			//testUI.valleyHeightTxt.setText("-6.0");
			//testUI.flatHeightTxt.setText("-2");
			//testUI.upperHeighTxt.setText("0");
			//testUI.lowerHeightTxt.setText("-3");
			testUI.eventHandler.updateColorBtnEvent();
			
			saveTerrainEvent(outputFolderName + "/" + className[i] + "_"+ i + "_" + survival[i] + ".png");
		}
		
		testUI.createTerrainBtn.disable();
	}
	
	
	private void saveTerrainEvent (String fileName)
	{
		try
		{
			testUI.terrainPanel.saveImage(fileName);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void writeExpressionFile()
	{
		try
		{
			for (int i = 0; i < NUM_SAMPLE; i++)
			{
				Formatter writeFile = new Formatter (new File (
						"C:/RWorkspace/TCGA_GBM/GDC_Pager_randForest40/TerrainData/" + i + "_" + className[i] + "_" + survival[i] + ".txt"));
				
				for (int j = 0; j < NUM_GENE; j++)
				{
					writeFile.format("%s\t%s\r\n", geneName[j], usFormat.format(expression[i][j]) );
				}
				writeFile.close();
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void readData()
	{
		try
		{
			Scanner readFile = new Scanner (new File(
					"C:/RWorkspace/TCGA_GBM/GDC_Pager_randForest40/rnaNormalizeSeqDay2D_PagerRand40Gene_LongShort.csv"));
			
			String firstLine = readFile.nextLine();
			String [] firstComponent = firstLine.split(",");
			for (int i = 0; i < NUM_GENE; i++)
			{
				geneName[i] = firstComponent[i+6];
			}
			
			for (int i = 0; i < NUM_SAMPLE; i++)
			{
				String line = readFile.nextLine();
				String [] component = line.split(",");
				
				className[i] = component[5];
				survival[i] = Integer.parseInt(component[3]);
				for (int j = 0; j < NUM_GENE; j++)
				{
					expression [i][j] = Float.parseFloat(component[j+6]);
				}
			}
			
			readFile.close();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main (String [] arg)
	{
		long startTime = System.currentTimeMillis();
		
		String layoutFile = "C:/RWorkspace/TCGA_GBM/GDC_Pager_randForest40/TerrainData/Layout.txt"; // by default
		String outputFolder = "C:/RWorkspace/TCGA_GBM/GDC_Pager_randForest40/TerrainImage"; // by default
		float argWeightScale = 1;
		if (arg.length == 2)
		{
			layoutFile = arg[0];
			outputFolder = arg[1];
		}
		else if (arg.length == 3)
		{
			layoutFile = arg[0];
			outputFolder = arg[1];
			argWeightScale = Float.parseFloat(arg[2]);
		}
		
		new GDC_TCGA_GBM_MultiTerrain(layoutFile, outputFolder, argWeightScale);
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Generating 108 terrains in " + elapsedTime / 1000.0 + " seconds");
	}
}
