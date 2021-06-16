
public class TestRPCalculation 
{
	public static void main (String [] arg)
	{
		
		float [][] network = {{0f, 0f, 0f, 1f, 0f}, // represent the network as 2d matrix
				{0f, 0f, 1f, 0f, 1f}, 
				{1f, 0f, 0f, 0f, 0f}, 
				{0f, 1f, 1f, 0f, 1f},
				{0f, 1f, 0f, 0f, 0f}};
		
		SparseMatrix sparseNet = new SparseMatrix(5); // represent network as sparse matrix
		sparseNet.store(0, 3, 1f); sparseNet.store(1, 2, 1f); sparseNet.store(1, 4, 1f); sparseNet.store(2, 0, 1f);
		sparseNet.store(3, 1, 1f); sparseNet.store(3, 2, 1f); sparseNet.store(3, 4, 1f); sparseNet.store(4, 1, 1f);
		
		float [] initialWeight = {0.2f, 0.2f, 0.2f, 0.2f, 0.2f}; // example of initial node weight
		
		// case 1: synthetic network, 5 nodes, use the small network code
		RPCalculation result1 = new RPCalculation(network); // call the RPCalculation
		float [] RP = result1.getRP();
		System.out.println("Case 1: synthetic net, test with small network code, without user input for node weight");
		for (int i = 0; i < RP.length; i++)
		{
			System.out.print("node " + i + ": " + RP[i] + ", ");
		}
		System.out.println("\n");
		
		// case 2: synthetic network, 5 nodes,  use the small network code, optional node weight
		RPCalculation result2 = new RPCalculation(network, initialWeight); // call the RPCalculation
		RP = result2.getRP();
		System.out.println("Case 2: synthetic net, test with small network code, with user input for node weight");
		for (int i = 0; i < RP.length; i++)
		{
			System.out.print("node " + i + ": " + RP[i] + ", ");
		}
		System.out.println("\n");
		
		// case 3: synthetic network, 5 nodes, test with large network code
		RPSparseCalculation result3 = new RPSparseCalculation(sparseNet); // call the RPSparseCalculation
		RP = result3.getRP();
		System.out.println("Case 3: synthetic net, test with large (sparse) network code, without user input for node weight");
		for (int i = 0; i < RP.length; i++)
		{
			System.out.print("node " + i + ": " + RP[i] + ", ");
		}
		System.out.println("\n");
		
		
		// case 4: synthetic network, 5 nodes, test with large network code, with initial weight from the user
		RPSparseCalculation result4 = new RPSparseCalculation(sparseNet, initialWeight); // call the RPSparseCalculation
		RP = result4.getRP();
		System.out.println("Case 4: synthetic net, test with large (sparse) network code, with user input for node weight");
		for (int i = 0; i < RP.length; i++)
		{
			System.out.print("node " + i + ": " + RP[i] + ", ");
		}
		System.out.println("\n");
	}
}
