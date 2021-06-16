package GenerateBackendMatrix;

public class GenerateBackendGBMMatrixMain 
{

	public static void main(String[] args) 
	{
		final int NUM_LONG_RNA = 54;
		final int NUM_SHORT_RNA = 54;
		final int NUM_LONG_MIC = 64;
		final int NUM_SHORT_MIC = 55;
		final int resolution = 512;
		
		for (int i = 0; i < NUM_LONG_RNA; i++)
		{
			String nodeFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/"
					+ "rnaSeq/terrainData/long/" + i + "height.txt";
			String layoutFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt";

			String outputFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/"
					+ "rnaSeq/terrainData/long/" + i + "Matrix.txt";

			GeneTerrainData backendData = new GeneTerrainData(nodeFile, layoutFile, resolution);
			backendData.updateBackendWeight(5, 20);
			backendData.setupAllPixelVal();
			backendData.savePixelValue(outputFile);
		}
		
		for (int i = 0; i < NUM_SHORT_RNA; i++)
		{
			String nodeFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/"
					+ "rnaSeq/terrainData/short/" + i + "height.txt";
			String layoutFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt";

			String outputFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/"
					+ "rnaSeq/terrainData/short/" + i + "Matrix.txt";

			GeneTerrainData backendData = new GeneTerrainData(nodeFile, layoutFile, resolution);
			backendData.updateBackendWeight(5, 20);
			backendData.setupAllPixelVal();
			backendData.savePixelValue(outputFile);
		}
		
		for (int i = 0; i < NUM_SHORT_MIC; i++)
		{
			String nodeFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/"
					+ "microArray/terrainData/short/" + i + "height.txt";
			String layoutFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt";

			String outputFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/"
					+ "microArray/terrainData/short/" + i + "Matrix.txt";

			GeneTerrainData backendData = new GeneTerrainData(nodeFile, layoutFile, resolution);
			backendData.updateBackendWeight(5, 20);
			backendData.setupAllPixelVal();
			backendData.savePixelValue(outputFile);
		}
		
		for (int i = 0; i < NUM_LONG_MIC; i++)
		{
			String nodeFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/"
					+ "microArray/terrainData/long/" + i + "height.txt";
			String layoutFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/trianlgeLayout.txt";

			String outputFile = "/home/thanhnguyen/RWorkspace/TCGA_GBM/LEF1_PPBP_RPL39L/"
					+ "microArray/terrainData/long/" + i + "Matrix.txt";

			GeneTerrainData backendData = new GeneTerrainData(nodeFile, layoutFile, resolution);
			backendData.updateBackendWeight(5, 20);
			backendData.setupAllPixelVal();
			backendData.savePixelValue(outputFile);
		}

	}

}
