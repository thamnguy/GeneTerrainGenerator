package GenerateBackendMatrix;

public class ParallelTerrainData extends Thread
{
	final int threadID;
	final int totalNumThread;
	final GeneTerrainData sharedTerrainData;
	
	public ParallelTerrainData(int argID, int argNumOfThread, GeneTerrainData argData) 
	{
		this.threadID = argID;
		this.totalNumThread = argNumOfThread;
		this.sharedTerrainData = argData;
	}
	
	public void run()
	{
		float gridWidth = 32f / sharedTerrainData.RESOLUTION;
		float minDraw = -16f;
		float maxDraw = 16f;
		// for each state manage by this thread
		for (int h = threadID; h < sharedTerrainData.RESOLUTION; h+= totalNumThread)
		{
			for (int w = 0; w < sharedTerrainData.RESOLUTION; w++)
			{
				// calculating the backend coordinate of the pixel
				float xBackend = minDraw + w * gridWidth + gridWidth/2 ;
				float yBackend = maxDraw - h * gridWidth - gridWidth/2;

				// calculating the pixel value
				float pixelVal = 0;
				for (int i = 0; i < sharedTerrainData.nodeID.length; i++)
				{
					if (sharedTerrainData.nodeInLayout[i])
					{
						float sqrDis = CommonMath.getSquareDistance (xBackend, yBackend, sharedTerrainData.nodeX[i], sharedTerrainData.nodeY[i]);

						//pixelVal += nodeValue[i] * Math.exp(-sqrDis / nodeBackWeight[i]);
						pixelVal += sharedTerrainData.nodeValue[i] *  sharedTerrainData.nodeBackWeight[i] / (sqrDis + 1) ;
					}
				}

				//mainUI.terrainPanel.pixelValue[h][w] = pixelVal;
				sharedTerrainData.pixelValue[h][w] = pixelVal;

			}
		}
	}
}
