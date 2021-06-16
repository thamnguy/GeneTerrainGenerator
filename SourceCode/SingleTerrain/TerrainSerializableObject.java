import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TerrainSerializableObject implements Serializable
{
	GeneTerrainData terrainData;
	
	File geneListFile;
	File connectionFile;
	File layoutFile;
	File maskFile;
	
	float minBackendWidth;
	float maxBackendWidth;
	
	String currentTerrainName;
	
	public TerrainSerializableObject (GeneTerrainData argTerrainData, File argGeneListFile, 
			File argConnectionFile, File argLayoutFile, File argMaskFile, String argTerrainName, 
			Float argMinBackendWidth, Float argMaxBackendWidth)
	{
		terrainData = argTerrainData;
		System.out.println("terrainData.nodeID.length: " + terrainData.nodeID.length);
		geneListFile = argGeneListFile;
		connectionFile = argConnectionFile;
		layoutFile = argLayoutFile;
		maskFile = argMaskFile;
		currentTerrainName = argTerrainName;
		
		minBackendWidth = argMinBackendWidth;
		maxBackendWidth = argMaxBackendWidth;
		
		terrainData.mainUI = null;
		terrainData.importer = null;
	}
	
	
	public void saveToSerializableFile(String argFileName)
	{
		FileOutputStream fout = null;
        ObjectOutputStream oos = null;
		try 
		{
            fout = new FileOutputStream(argFileName);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);

        } 
		catch (Exception ex) 
		{	
            ex.printStackTrace();
        }
	}
}
