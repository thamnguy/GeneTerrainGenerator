package GenerateBackendMatrix;

public class GenerateBackendDrugMatrixMain 
{

	public static void main(String[] args) 
	{		
		// generate matrix for positive drugs
		String [] positiveDrug = {"Anastrozole" , "Arzoxifene" , "Canertinib" , "Donepezil" , "Erbitux" , 
				"Exemestane" , "Fadrozole" , "Fenretinide" , "Fluorouracil" , "Fluoxymesterone" , "Fulvestrant" , 
				"Hydroxyurea" , "Lapatinib" , "Letrozole" , "Lithium Chloride" , "Melatonin" , "Miltefosine" , 
				"Neratinib" , "Nocodazole" , "Onapristone" , "Ondansetron" , "Paclitaxel" , "Pamidronate" , 
				"Plicamycin" , "Raloxifene" , "Tamoxifen" , "Tetradecanoylphorbol Acetate" , "Thiotepa" , 
				"Vandetanib" , "Avastin"};

		for (int i = 0; i < positiveDrug.length; i++)
		{
			String nodeFile = "/home/thanhnguyen/Desktop/Project/Project_GeneTerrain/"
					+ "Kuma_BreastCancerDrugCase/TerrainData/PositiveScore/" + positiveDrug[i] + "_height.txt";
			String layoutFile = "/home/thanhnguyen/Desktop/Project/Project_GeneTerrain/" 
					+ "Kuma_BreastCancerDrugCase/TerrainData/ER+Layout.txt";

			String outputFile = "/home/thanhnguyen/Desktop/Project/Project_GeneTerrain/"
					+ "Kuma_BreastCancerDrugCase/TerrainData/PositiveMatrix/" + positiveDrug[i] + ".txt";
			int resolution = 2048;

			GeneTerrainData backendData = new GeneTerrainData(nodeFile, layoutFile, resolution);
			backendData.updateBackendWeight(5, 20);
			backendData.setupAllPixelVal();
			backendData.savePixelValue(outputFile);
		}


		// generate matrix for negative drugs
		String [] negativeDrug = {"Bleomycin" , "Celecoxib" , "Conjugated Estrogens" , "Dexamethasone" , "Diethylstilbestrol" , 
				"Dihydrotestosterone" , "Dromostanolone Propionate" , "Estradiol" , "Ethinyl Estradiol" , "Flutamide" , 
				"Formestane" , "Medrysone" , "Methyl Methanesulfonate" , "Methylprednisolone" , "Prednisolone" , 
				"Prednisone" , "Progesterone" , "Testosterone" , "Trilostane" , "Velcade" , "Ethyl Carbamate"};

		for (int i = 0; i < negativeDrug.length; i++)
		{
			String nodeFile = "/home/thanhnguyen/Desktop/Project/Project_GeneTerrain/"
					+ "Kuma_BreastCancerDrugCase/TerrainData/NegativeScore/" + negativeDrug[i] + "_height.txt";
			String layoutFile = "/home/thanhnguyen/Desktop/Project/Project_GeneTerrain/" 
					+ "Kuma_BreastCancerDrugCase/TerrainData/ER+Layout.txt";

			String outputFile = "/home/thanhnguyen/Desktop/Project/Project_GeneTerrain/"
					+ "Kuma_BreastCancerDrugCase/TerrainData/NegativeMatrix/" + negativeDrug[i] + ".txt";
			int resolution = 2048;

			GeneTerrainData backendData = new GeneTerrainData(nodeFile, layoutFile, resolution);
			backendData.updateBackendWeight(5, 20);
			backendData.setupAllPixelVal();
			backendData.savePixelValue(outputFile);
		}
	}

}
