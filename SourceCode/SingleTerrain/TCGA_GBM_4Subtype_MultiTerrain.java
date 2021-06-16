import java.io.File;
import java.text.NumberFormat;
import java.util.Scanner;

public class TCGA_GBM_4Subtype_MultiTerrain 
{
	final int NUM_GENE = 3;
	final int NUM_CLASSICAL_SAMPLE = 105;
	final int NUM_MESENCHYMAL_SAMPLE = 119;

	final int NUM_NEURAL_SAMPLE = 69;
	final int NUM_PRONEURAL_SAMPLE = 96;

	GeneTerrainMainUI testUI ;

	NumberFormat usFormat;

	public TCGA_GBM_4Subtype_MultiTerrain()
	{
		testUI = new GeneTerrainMainUI();

		createClassicalTerrain();
		createMesenchymalTerrain();

		createNeuralTerrain();
		createProneuralTerrain();

		createClassicalProfileTerrain();
		createMesenchymalProfileTerrain();

		createNeuralProfileTerrain();
		createProneuralProfileTerrain();
	}


	private void createClassicalProfileTerrain()
	{
		try
		{
			testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainData/Classical/profileExpression.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");
			
			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");

			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainImage/Original/Classical/profile.png");
			
			
			testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainData/Classical/variance.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");
			
			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");

			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainImage/Original/Classical/variance.png");
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createMesenchymalProfileTerrain()
	{
		try
		{
			testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainData/Mesenchymal/profileExpression.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");
			
			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainImage/Original/Mesenchymal/profile.png");
			
			
			testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainData/Mesenchymal/variance.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");
			
			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainImage/Original/Mesenchymal/variance.png");
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createNeuralProfileTerrain()
	{
		try
		{
			testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainData/Neural/profileExpression.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");
			
			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainImage/Original/Neural/profile.png");
			
			
			testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainData/Neural/variance.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");
			
			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainImage/Original/Neural/variance.png");
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createProneuralProfileTerrain()
	{
		try
		{
			testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainData/Proneural/profileExpression.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");
			
			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainImage/Original/Proneural/profile.png");
			
			
			testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainData/Proneural/variance.txt");
			testUI.connectionFile = null;
			testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

			testUI.smallInfTxt.setText("1.0");
			testUI.largeInfTxt.setText("1.0");
			
			testUI.eventHandler.createTerrainEvent();

			/*testUI.peakHeightTxt.setText("10.0");
			testUI.valleyHeightTxt.setText("-10.0");
			testUI.flatHeightTxt.setText("0.0");
			testUI.upperHeighTxt.setText("5.0");
			testUI.lowerHeightTxt.setText("-5.0");
			testUI.eventHandler.updateColorBtnEvent();*/

			testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
					+ "TerrainImage/Original/Proneural/variance.png");
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createClassicalTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_CLASSICAL_SAMPLE; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
						+ "TerrainData/Classical/" + i + ".txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

				testUI.smallInfTxt.setText("1.0");
				testUI.largeInfTxt.setText("1.0");
				
				testUI.eventHandler.createTerrainEvent();

				/*testUI.peakHeightTxt.setText("10.0");
				testUI.valleyHeightTxt.setText("-10.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("5.0");
				testUI.lowerHeightTxt.setText("-5.0");
				testUI.eventHandler.updateColorBtnEvent();*/

				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
						+ "TerrainImage/Original/Classical/" + i + ".png");
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createMesenchymalTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_MESENCHYMAL_SAMPLE; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
						+ "TerrainData/Mesenchymal/" + i + ".txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

				testUI.smallInfTxt.setText("1.0");
				testUI.largeInfTxt.setText("1.0");
				
				testUI.eventHandler.createTerrainEvent();

				/*testUI.peakHeightTxt.setText("10.0");
				testUI.valleyHeightTxt.setText("-10.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("5.0");
				testUI.lowerHeightTxt.setText("-5.0");
				testUI.eventHandler.updateColorBtnEvent();*/

				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
						+ "TerrainImage/Original/Mesenchymal/" + i + ".png");
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createNeuralTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_NEURAL_SAMPLE; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
						+ "TerrainData/Neural/" + i + ".txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

				testUI.smallInfTxt.setText("1.0");
				testUI.largeInfTxt.setText("1.0");
				
				testUI.eventHandler.createTerrainEvent();

				/*testUI.peakHeightTxt.setText("10.0");
				testUI.valleyHeightTxt.setText("-10.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("5.0");
				testUI.lowerHeightTxt.setText("-5.0");
				testUI.eventHandler.updateColorBtnEvent();*/

				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
						+ "TerrainImage/Original/Neural/" + i + ".png");
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createProneuralTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_PRONEURAL_SAMPLE; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
						+ "TerrainData/Proneural/" + i + ".txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/TerrainData/Layout_equalWeight.txt");

				testUI.smallInfTxt.setText("1.0");
				testUI.largeInfTxt.setText("1.0");
				
				testUI.eventHandler.createTerrainEvent();

				/*testUI.peakHeightTxt.setText("10.0");
				testUI.valleyHeightTxt.setText("-10.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("5.0");
				testUI.lowerHeightTxt.setText("-5.0");
				testUI.eventHandler.updateColorBtnEvent();*/

				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM_Subtype_CellCycle/"
						+ "TerrainImage/Original/Proneural/" + i + ".png");
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
		new TCGA_GBM_4Subtype_MultiTerrain();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Generating 400 terrains in " + elapsedTime / 1000.0 + " seconds");
	}
}
