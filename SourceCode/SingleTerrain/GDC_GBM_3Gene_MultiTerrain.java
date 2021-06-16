import java.io.File;
import java.text.NumberFormat;
import java.util.Scanner;

public class GDC_GBM_3Gene_MultiTerrain 
{
	final int NUM_GENE = 3;
	final int NUM_LONG_SAMPLE_RNA = 54;
	final int NUM_SHORT_SAMPLE_RNA = 54;
	
	final int NUM_LONG_SAMPLE_MIC = 64;
	final int NUM_SHORT_SAMPLE_MIC = 55;

	GeneTerrainMainUI testUI ;

	NumberFormat usFormat;

	public GDC_GBM_3Gene_MultiTerrain()
	{
		testUI = new GeneTerrainMainUI();
		
		//createRNALongTerrain();
		//createRNAShortTerrain();
		
		//createMICLongTerrain();
		//createMICShortTerrain();
		
		createRNALongProfileTerrain();
		//createRNAShortProfileTerrain();
		
		//createMICLongProfileTerrain();
		//createMICShortProfileTerrain();
	}
	
	
	private void createRNALongProfileTerrain()
	{
		try
		{
			for (int i = 1; i <= 1000; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM/"
						+ "LEF1_PPBP_RPL39L/rnaSeq/terrainData/Cross-validation/longProfile_" + i + "_height.txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("1.0");
				testUI.valleyHeightTxt.setText("-1.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("0.5");
				testUI.lowerHeightTxt.setText("-0.5");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L"
						+ "/rnaSeq/terrainImage/Cross-validation/OriginalTerrain/longProfile_" + i + ".png");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void createRNAShortProfileTerrain()
	{
		try
		{
			for (int i = 1; i <= 1000; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM/"
						+ "LEF1_PPBP_RPL39L/rnaSeq/terrainData/Cross-validation/shortProfile_" + i + "_height.txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("1.0");
				testUI.valleyHeightTxt.setText("-1.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("0.5");
				testUI.lowerHeightTxt.setText("-0.5");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L"
						+ "/rnaSeq/terrainImage/Cross-validation/OriginalTerrain/shortProfile_" + i + ".png");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void createMICLongProfileTerrain()
	{
		try
		{
			for (int i = 1; i <= 1000; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM/"
						+ "LEF1_PPBP_RPL39L/microArray/terrainData/Cross-validation/longProfile_" + i + "_height.txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("1.0");
				testUI.valleyHeightTxt.setText("-1.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("0.5");
				testUI.lowerHeightTxt.setText("-0.5");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L"
						+ "/microArray/terrainImage/Cross-validation/OriginalTerrain/longProfile_" + i + ".png");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void createMICShortProfileTerrain()
	{
		try
		{
			for (int i = 1; i <= 1000; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM/"
						+ "LEF1_PPBP_RPL39L/microArray/terrainData/Cross-validation/shortProfile_" + i + "_height.txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("1.0");
				testUI.valleyHeightTxt.setText("-1.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("0.5");
				testUI.lowerHeightTxt.setText("-0.5");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L"
						+ "/microArray/terrainImage/Cross-validation/OriginalTerrain/shortProfile_" + i + ".png");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void createRNALongTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_LONG_SAMPLE_RNA; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM/"
						+ "LEF1_PPBP_RPL39L/rnaSeq/terrainData/long/" + i + "height.txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("1.0");
				testUI.valleyHeightTxt.setText("-1.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("0.5");
				testUI.lowerHeightTxt.setText("-0.5");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L"
						+ "/rnaSeq/terrainImage/OriginalTerrain/long/" + i + ".png");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void createRNAShortTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_SHORT_SAMPLE_RNA; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM/"
						+ "LEF1_PPBP_RPL39L/rnaSeq/terrainData/short/" + i + "height.txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("1.0");
				testUI.valleyHeightTxt.setText("-1.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("0.5");
				testUI.lowerHeightTxt.setText("-0.5");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L"
						+ "/rnaSeq/terrainImage/OriginalTerrain/short/" + i + ".png");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void createMICLongTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_LONG_SAMPLE_MIC; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM/"
						+ "LEF1_PPBP_RPL39L/microArray/terrainData/long/" + i + "height.txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("1.0");
				testUI.valleyHeightTxt.setText("-1.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("0.5");
				testUI.lowerHeightTxt.setText("-0.5");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L"
						+ "/microArray/terrainImage/OriginalTerrain/long/" + i + ".png");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void createMICShortTerrain()
	{
		try
		{
			for (int i = 0; i < NUM_SHORT_SAMPLE_MIC; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/TCGA_GBM/"
						+ "LEF1_PPBP_RPL39L/microArray/terrainData/short/" + i + "height.txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt");

				testUI.eventHandler.createTerrainEvent();

				testUI.peakHeightTxt.setText("1.0");
				testUI.valleyHeightTxt.setText("-1.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("0.5");
				testUI.lowerHeightTxt.setText("-0.5");
				testUI.eventHandler.updateColorBtnEvent();
				
				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L"
						+ "/microArray/terrainImage/OriginalTerrain/short/" + i + ".png");
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
		new GDC_GBM_3Gene_MultiTerrain();
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Generating 4000 terrains in " + elapsedTime / 1000.0 + " seconds");
	}
}
