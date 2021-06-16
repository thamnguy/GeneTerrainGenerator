import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class DiabetesMultiTerrainData 
{

	GeneTerrainMainUI testUI ;
	String[] className;
	String [] geneName;
	float [][] expression;
	//int [] survival;

	String layoutFileName;
	String outputFolderName;
	
	public DiabetesMultiTerrainData(String argLayoutFileName, String argOutputFolderName, float argWidthScale)
	{
		layoutFileName = argLayoutFileName;
		outputFolderName = argOutputFolderName;
		//testUI = new GeneTerrainMainUI();
		className = new String [21090];
		geneName = new String [35];
		//survival = new int [447];
		expression = new float [21090] [35];
		
		readData();
		
		writeExpressionFile();
		
		testUI = new GeneTerrainMainUI();
		for (int i = 0; i < 21090; i++)
		{
			testUI.geneListFile = new File (
					"C:/IBITShared/ChineseData/Sep23_newData_diabetes/data_matrix_form/HealthTerrain/" + i + "_" + className[i] + ".txt");
			testUI.connectionFile = new File ("C:/IBITShared/ChineseData/Sep23_newData_diabetes/data_matrix_form/HealthTerrain/Correlation.txt");
			testUI.layoutFile = new File(layoutFileName);
			
			//testUI.terrainData.scaleNodeWidth(argWidthScale);
			
			testUI.eventHandler.createTerrainEvent(argWidthScale);
			saveTerrainEvent(outputFolderName + "/" + className[i] + "_"+ i + ".png");
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
			for (int i = 0; i < 21090; i++)
			{
				Formatter writeFile = new Formatter (new File (
						"C:/IBITShared/ChineseData/Sep23_newData_diabetes/data_matrix_form/HealthTerrain/" + i + "_" + className[i] + ".txt"));
				
				for (int j = 0; j < 35; j++)
				{
					writeFile.format("%s\t%f\r\n", geneName[j], expression[i][j]);
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
					"C:/IBITShared/ChineseData/Sep23_newData_diabetes/data_matrix_form/HealthTerrain/TestResult.csv"));
			
			String firstLine = readFile.nextLine();
			String [] firstComponent = firstLine.split(",");
			for (int i = 0; i < 35; i++)
			{
				geneName[i] = firstComponent[i+1];
			}
			
			for (int i = 0; i < 21090; i++)
			{
				String line = readFile.nextLine();
				String [] component = line.split(",");
				
				className[i] = component[36];
				//survival[i] = Integer.parseInt(component[892]);
				for (int j = 0; j < 35; j++)
				{
					expression [i][j] = Float.parseFloat(component[j+1]);
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
		String layoutFile = "C:/IBITShared/ChineseData/Sep23_newData_diabetes/data_matrix_form/HealthTerrain/Layout.txt"; // by default
		String outputFolder = "C:/IBITShared/ChineseData/Sep23_newData_diabetes/data_matrix_form/HealthTerrain"; // by default
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
		
		new DiabetesMultiTerrainData(layoutFile, outputFolder, argWeightScale);
	}
}
