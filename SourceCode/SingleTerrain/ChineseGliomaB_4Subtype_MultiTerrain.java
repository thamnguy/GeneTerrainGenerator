import java.io.File;
import java.text.NumberFormat;
import java.util.Scanner;

public class ChineseGliomaB_4Subtype_MultiTerrain
{
	final int NUM_GENE = 3;
	final int NUM_Mutant_SAMPLE = 49;
	final int NUM_Wildtype_SAMPLE = 190;
	final int NUM_NA_SAMPLE = 10;

	GeneTerrainMainUI testUI ;

	NumberFormat usFormat;

	public ChineseGliomaB_4Subtype_MultiTerrain()
	{
		testUI = new GeneTerrainMainUI();

		createMutantTerrain();
		createWildtypeTerrain();
		createNATerrain();

		createMutantProfileTerrain();
		createWildtypeProfileTerrain();
		createNAProfileTerrain();
	}


	private void createMutantProfileTerrain()
	{
		try
		{
			testUI.geneListFile = new File ("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
					+ "TerrainData/Mutant/profileExpression.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/TerrainData/Layout_equalWeight.txt");
			
			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");

			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
					+ "TerrainImage/Original/Mutant/profile.png");
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createWildtypeProfileTerrain()
	{
		try
		{
			testUI.geneListFile = new File ("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
					+ "TerrainData/Wildtype/profileExpression.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/TerrainData/Layout_equalWeight.txt");

			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");
			
			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
					+ "TerrainImage/Original/Wildtype/profile.png");
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createNAProfileTerrain()
	{
		try
		{
			testUI.geneListFile = new File ("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
					+ "TerrainData/NA/profileExpression.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/TerrainData/Layout_equalWeight.txt");

			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");
			
			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
					+ "TerrainImage/Original/NA/profile.png");
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createMutantTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_Mutant_SAMPLE; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
						+ "TerrainData/Mutant/" + i + ".txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/TerrainData/Layout_equalWeight.txt");

				testUI.smallInfTxt.setText("1.0");
				testUI.largeInfTxt.setText("1.0");
				
				testUI.eventHandler.createTerrainEvent();

				/*testUI.peakHeightTxt.setText("10.0");
				testUI.valleyHeightTxt.setText("-10.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("5.0");
				testUI.lowerHeightTxt.setText("-5.0");
				testUI.eventHandler.updateColorBtnEvent();*/

				testUI.terrainPanel.saveImage("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
						+ "TerrainImage/Original/Mutant/" + i + ".png");
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createWildtypeTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_Wildtype_SAMPLE; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
						+ "TerrainData/Wildtype/" + i + ".txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/TerrainData/Layout_equalWeight.txt");

				testUI.smallInfTxt.setText("1.0");
				testUI.largeInfTxt.setText("1.0");
				
				testUI.eventHandler.createTerrainEvent();

				/*testUI.peakHeightTxt.setText("10.0");
				testUI.valleyHeightTxt.setText("-10.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("5.0");
				testUI.lowerHeightTxt.setText("-5.0");
				testUI.eventHandler.updateColorBtnEvent();*/

				testUI.terrainPanel.saveImage("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
						+ "TerrainImage/Original/Wildtype/" + i + ".png");
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createNATerrain()
	{
		try
		{
			for (int i = 0; i < NUM_NA_SAMPLE; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
						+ "TerrainData/NA/" + i + ".txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/TerrainData/Layout_equalWeight.txt");

				testUI.smallInfTxt.setText("1.0");
				testUI.largeInfTxt.setText("1.0");
				
				testUI.eventHandler.createTerrainEvent();

				/*testUI.peakHeightTxt.setText("10.0");
				testUI.valleyHeightTxt.setText("-10.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("5.0");
				testUI.lowerHeightTxt.setText("-5.0");
				testUI.eventHandler.updateColorBtnEvent();*/

				testUI.terrainPanel.saveImage("/home/thanhnguyen/Desktop/ChineseGlioma/PartB/"
						+ "TerrainImage/Original/NA/" + i + ".png");
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
		new ChineseGliomaB_4Subtype_MultiTerrain();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Generating 400 terrains in " + elapsedTime / 1000.0 + " seconds");
	}
}
