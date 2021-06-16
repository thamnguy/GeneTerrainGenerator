import java.io.File;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class GBM_MultiTerrain 
{

	GeneTerrainMainUI testUI ;
	String[] className;
	String [] geneName;
	float [][] expression;
	int [] survival;

	String layoutFileName;
	String outputFolderName;
	
	NumberFormat usFormat;
	
	public GBM_MultiTerrain(String argLayoutFileName, String argOutputFolderName, float argWidthScale)
	{
		usFormat = NumberFormat.getInstance(Locale.US);
		layoutFileName = argLayoutFileName;
		outputFolderName = argOutputFolderName;
		//testUI = new GeneTerrainMainUI();
		className = new String [447];
		geneName = new String [890];
		survival = new int [447];
		expression = new float [447] [890];
		
		readData();
		
		writeExpressionFile();
		
		testUI = new GeneTerrainMainUI();
		for (int i = 0; i < 447; i++)
		{
			testUI.geneListFile = new File (
					"TerrainData_DEMA/" + i + "_" + className[i] + "_" + survival[i] + ".txt");
			testUI.connectionFile = new File ("TerrainData_DEMA/GBM_5Star_INTERACTION.txt");
			testUI.layoutFile = new File(layoutFileName);
			
			//testUI.terrainData.scaleNodeWidth(argWidthScale);
			
			testUI.eventHandler.createTerrainEvent(argWidthScale);
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
			for (int i = 0; i < 447; i++)
			{
				Formatter writeFile = new Formatter (new File (
						"TerrainData_DEMA/" + i + "_" + className[i] + "_" + survival[i] + ".txt"));
				
				for (int j = 0; j < 890; j++)
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
					"TerrainData_DEMA/GBM_arraygene_zNormalize_weka.csv"));
			
			String firstLine = readFile.nextLine();
			String [] firstComponent = firstLine.split(",");
			for (int i = 0; i < 890; i++)
			{
				geneName[i] = firstComponent[i+2];
			}
			
			for (int i = 0; i < 447; i++)
			{
				String line = readFile.nextLine();
				String [] component = line.split(",");
				
				className[i] = component[893];
				survival[i] = Integer.parseInt(component[892]);
				for (int j = 0; j < 890; j++)
				{
					expression [i][j] = Float.parseFloat(component[j+2]);
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
		
		String layoutFile = "TerrainData_DEMA/GBM_DEMA_Layout.txt"; // by default
		String outputFolder = "TerrainImage_DEMA"; // by default
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
		
		new GBM_MultiTerrain(layoutFile, outputFolder, argWeightScale);
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Generating 447 terrains in " + elapsedTime / 1000.0 + " seconds");
	}
}
