import java.io.File;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Vector;

public class TCGA_GBM_Multi_ClinicalTerrain 
{
	final int NUM_GENE = 426;
	final int NUM_CLASSICAL_SAMPLE = 105;
	final int NUM_MESENCHYMAL_SAMPLE = 119;

	final int NUM_NEURAL_SAMPLE = 69;
	final int NUM_PRONEURAL_SAMPLE = 96;

	GeneTerrainMainUI testUI ;

	NumberFormat usFormat;
	
	String [] fileList;

	public TCGA_GBM_Multi_ClinicalTerrain()
	{
		testUI = new GeneTerrainMainUI();

		getFileList();
		
		createAllTerrain();
		
	}
	
	// get all terrain file list
	private void getFileList()
	{
		try
		{
			Scanner expressionMeta = new Scanner (new File("/home/thanhnguyen/RWorkspace/"
					+ "TCGA_GBM_Subtype/clinicalProfileTerrain/Metadata.txt"));
			expressionMeta.nextLine();
			Vector<String> tempVec = new Vector<String>();
			while(expressionMeta.hasNextLine())
			{
				String line = expressionMeta.nextLine();
				String[] component = line.split("\t");
				tempVec.add(component[0]);
			}
			expressionMeta.close();
			
			fileList = new String[tempVec.size()];
			for (int i = 0; i < fileList.length; i++)
			{
				fileList[i] = tempVec.elementAt(i);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createAllTerrain()
	{
		try
		{
			for (int i = 0; i < fileList.length; i++)
			{
				testUI.geneListFile = new File ("/home/thanhnguyen/RWorkspace/"
						+ "TCGA_GBM_Subtype/clinicalProfileTerrain/expressionFile/" + fileList[i] + ".txt");
				testUI.connectionFile = null;
				testUI.layoutFile = new File("/home/thanhnguyen/RWorkspace/"
						+ "TCGA_GBM_Subtype/clinicalProfileTerrain/Layout_equalWeight.txt");
				
				testUI.smallInfTxt.setText("1.0");
				testUI.largeInfTxt.setText("1.0");

				testUI.eventHandler.createTerrainEvent();

				/*testUI.peakHeightTxt.setText("10.0");
				testUI.valleyHeightTxt.setText("-10.0");
				testUI.flatHeightTxt.setText("0.0");
				testUI.upperHeighTxt.setText("5.0");
				testUI.lowerHeightTxt.setText("-5.0");
				testUI.eventHandler.updateColorBtnEvent();*/

				testUI.terrainPanel.saveImage("/home/thanhnguyen/RWorkspace/"
						+ "TCGA_GBM_Subtype/clinicalProfileTerrain/terrainImage/" + fileList[i] + ".png");
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
		new TCGA_GBM_Multi_ClinicalTerrain();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Generating all terrains in " + elapsedTime / 1000.0 + " seconds");
	}
}
