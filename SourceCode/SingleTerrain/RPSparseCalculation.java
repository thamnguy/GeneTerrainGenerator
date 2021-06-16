/* Code for RP score calculation
 * Input: 
 *    - network. This is the most difficult part. If the network is small ( < 1000 nodes ), then we can use the 2d matrix to store the network. 
 *               If the network is big, then we need sparse matrix to store the network. This class is for small network.
 *               The network[i][j] = 0 means that there is no edge from node i to node j
 *                   network[i][j] != 0 means that there is an edge from node i to node j with weight = network[i][j]
 *    - user node weight (optional)
 * Output:
 *    - RP score
 * */
public class RPSparseCalculation 
{
	int numNode; // number of nodes in the network
	float [] RP; // RP score (output). The sum of RP is 1 to match with the math theory
	float [] RP0; // initial RP score. The sum of RP0 is 1 to match with the math theory
	SparseMatrix network; // network, in 2d square matrix
	int maxIter = 100; // number of iteraions running PageRank, by default, 100
	
	float [] nodeWeightDeg; // temporary field, node weighted degree
	int [] nodeDeg;   // temporary field, node degree


	// ------ default constructor without optional input ------------
	public RPSparseCalculation (SparseMatrix argNet)
	{
		numNode = argNet.getN();
		RP = new float [numNode];
		RP0 = new float [numNode];
		nodeWeightDeg = new float [numNode];
		nodeDeg = new int [numNode];
		network = argNet;

		computeNodeDeg();
		initializeRP();
		computeRP();
	}


	// ------ default constructor with optional input ------------
	public RPSparseCalculation (SparseMatrix argNet, float [] argNodeWeight)
	{
		numNode = argNet.getN();
		RP = new float [numNode];
		RP0 = new float [numNode];
		nodeWeightDeg = new float [numNode];
		nodeDeg = new int [numNode];
		network = argNet;

		for (int i = 0; i < numNode; i++)
		{
			RP0[i] = argNodeWeight[i];
			RP[i] = RP0[i];
		}
		
		computeNodeDeg();
		computeRP();
	}
	
	
	// ----------- compute node degree ----------------------------
	private void computeNodeDeg ()
	{
		for (int i = 0; i < numNode; i++)
		{
			nodeDeg[i] = 0;
			nodeWeightDeg[i] = 0;
			for (int j = 0; j < numNode; j++)
			{
				if (network.get(i, j) > 0)
				{
					nodeDeg[i] ++;
					nodeWeightDeg[i] += network.get(i, j);
				}
			}
		}
	}


	// ------------- initialize RP score, without user input -----------------
	private void initializeRP()
	{
		for (int i = 0; i < numNode; i++)
		{
			RP0[i] = (float) (2*Math.log(nodeWeightDeg[i]) - Math.log(nodeDeg[i]));
			RP0[i] = (float) Math.exp(RP[i]);
			RP[i] = RP0[i];
		}
	}
	
	
	// -------------------- compute the RP score-------------
	private void computeRP()
	{
		float sigma = 0.85f;
		for (int t = 0; t < maxIter; t++)
		{
			float [] tempRP = RP.clone();
			for (int i = 0; i < numNode; i++)
			{
				RP[i] = (1 - sigma) * RP0[i];
				for (int j = 0; j < numNode; j++)
				{
					if (nodeWeightDeg[j] > 0)
					{
						RP[i] += sigma* tempRP[j] * network.get(j, i) / nodeWeightDeg[j];
					}
				}
			}
		}
	}


	// ----------- get the RP score ----------------------
	public float [] getRP()
	{
		return RP;
	}
}
