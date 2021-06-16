import java.io.File;
import java.text.NumberFormat;
import java.util.Scanner;

public class BreastCaner_Kuma_MultiTerrain 
{
	final int NUM_GENE = 43;
	final int NUM_POS_SAMPLE = 30;
	final int NUM_NEG_SAMPLE = 21;

	GeneTerrainMainUI testUI ;

	String [] posDrugList;
	String [] negDrugList;

	String layoutFileName;
	String outputFolderName;

	NumberFormat usFormat;

	public BreastCaner_Kuma_MultiTerrain()
	{
		testUI = new GeneTerrainMainUI();
		
		readPosDrug();
		createPosTerrain();

		readNegDrug();
		createNegTerrain();
	}

	
	private void readNegDrug()
	{
		negDrugList = new String[NUM_NEG_SAMPLE];
		try
		{
			Scanner readFile = new Scanner (new File("/home/thanhnguyen/Desktop/Project/"
					+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/NegativeDruglist.txt"));
			int index = 0;
			while (readFile.hasNextLine())
			{
				negDrugList[index] = readFile.nextLine();
				index++;
			}

			readFile.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	private void readPosDrug()
	{
		posDrugList = new String[NUM_POS_SAMPLE];
		try
		{
			Scanner readFile = new Scanner (new File("/home/thanhnguyen/Desktop/Project/"
					+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/PositiveDruglist.txt"));
			int index = 0;
			while (readFile.hasNextLine())
			{
				posDrugList[index] = readFile.nextLine();
				index++;
			}

			readFile.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createPosTerrain()
	{
		try
		{
			for (int i = 0; i < posDrugList.length; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/Desktop/Project/"
						+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/PositiveScore/" + posDrugList[i] + "_height.txt");
				testUI.connectionFile = new File ("/home/thanhnguyen/Desktop/Project/"
						+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/PPI.txt");
				testUI.layoutFile = new File("/home/thanhnguyen/Desktop/Project/"
						+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/ER+Layout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("2.0");
				testUI.valleyHeightTxt.setText("-2.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("1.0");
				testUI.lowerHeightTxt.setText("-1.0");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/Desktop/Project/"
						+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/PositiveScore/" + posDrugList[i] + ".png");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void createNegTerrain()
	{
		try
		{
			for (int i = 0; i < negDrugList.length; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/Desktop/Project/"
						+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/NegativeScore/" + negDrugList[i] + "_height.txt");
				testUI.connectionFile = new File ("/home/thanhnguyen/Desktop/Project/"
						+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/PPI.txt");
				testUI.layoutFile = new File("/home/thanhnguyen/Desktop/Project/"
						+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/ER+Layout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("2.0");
				testUI.valleyHeightTxt.setText("-2.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("1.0");
				testUI.lowerHeightTxt.setText("-1.0");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/Desktop/Project/"
						+ "Project_GeneTerrain/Kuma_BreastCancerDrugCase/NegativeScore/" + negDrugList[i] + ".png");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String [] arg)
	{
		long startTime = System.currentTimeMillis();
		new BreastCaner_Kuma_MultiTerrain();
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Generating 60 terrains in " + elapsedTime / 1000.0 + " seconds");
	}
}
