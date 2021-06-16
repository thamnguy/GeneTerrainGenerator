import java.util.HashSet;
import java.util.Vector;

/* This class find all of the connected components in a network
 * Each connected component is represented by a set. Basically the algorithm is about set operators and data structure. The idea is as follow
 * Initialize: C (collection of component): null
 * For i from 0 to n-1 // n is the number of nodes
 *     isContain = check if any existing component already contains i
 *     if isContain = false
 *     	   make a new component C(i)
 *         add C(i) into the collection C
 *     C(i): existing component containing i
 *         for j from i to n
 *              if C(j) already exist
 *                   add all elements from C(i) into C(j)
 *                   remove C(i) from the collection
 *               else
 *                   just add j into C(i)
 *              
 *              
 * This class assumes that the node ID is the node index
 * */

public class ConnectedComponent 
{
	Vector <HashSet<Integer>> allComponent = new Vector<HashSet<Integer>>();

	// ------------- constructor: input is a full matrix in float format -----------------------------
	// the constructor only does one thing: finding all connected components in a network
	public ConnectedComponent (float [][] argNet)
	{
		for (int i = 0; i < argNet.length; i++)
		{
			boolean iContain = false;
			HashSet<Integer> iCompo = null;

			for (int t = 0; t < allComponent.size(); t++)
			{
				if (allComponent.elementAt(t).contains(i))
				{
					iContain = true;
					iCompo = allComponent.elementAt(t);
					break;
				}
			}

			if (iContain == false) // haven't seen i in any existing component. So create a new one for it
			{
				iCompo = new HashSet<Integer>();
				allComponent.add(iCompo);
				iCompo.add(i);

			}

			for (int j = i+1; j < argNet.length; j++)
			{
				if (Math.abs(argNet[i][j]) > 0.000001 || Math.abs(argNet[j][i]) > 0.000001 ) // it is acutally if argNet[i][j] != 0
				{
					boolean jContain = false;
					HashSet<Integer> jCompo = null;

					for (int t = 0; t < allComponent.size(); t++)
					{
						if (allComponent.elementAt(t).contains(j))
						{
							jContain = true;
							jCompo = allComponent.elementAt(t);
							break;
						}
					}

					if (jContain == false)
					{
						iCompo.add(j);
					}
					else
					{
						jCompo.addAll(iCompo);
						allComponent.remove(iCompo);
					}
				}
			}
		}
	}


	// ------------- constructor: input is a full matrix in sparse format -----------------------------
	// the constructor only does one thing: finding all connected components in a network
	public ConnectedComponent (SparseMatrix argNet)
	{
		for (int i = 0; i < argNet.getN(); i++)
		{
			boolean iContain = false;
			HashSet<Integer> iCompo = null;

			for (int t = 0; t < allComponent.size(); t++)
			{
				if (allComponent.elementAt(t).contains(i))
				{
					iContain = true;
					iCompo = allComponent.elementAt(t);
					break;
				}
			}

			if (iContain == false) // haven't seen i in any existing component. So create a new one for it
			{
				iCompo = new HashSet<Integer>();
				allComponent.add(iCompo);
				iCompo.add(i);

			}

			for (int j = i+1; j < argNet.getN(); j++)
			{
				if (Math.abs(argNet.get(i, j)) > 0.000001 || Math.abs(argNet.get(j, i)) > 0.000001 ) // it is acutally if argNet[i][j] != 0
				{
					boolean jContain = false;
					HashSet<Integer> jCompo = null;

					for (int t = 0; t < allComponent.size(); t++)
					{
						if (allComponent.elementAt(t).contains(j))
						{
							jContain = true;
							jCompo = allComponent.elementAt(t);
							break;
						}
					}

					if (jContain == false)
					{
						iCompo.add(j);
					}
					else
					{
						jCompo.addAll(iCompo);
						allComponent.remove(iCompo);
					}
				}
			}
		}
	}



	// --------------- get the largest connected component as the array of node index ----------------------
	// simply find in allComponent the largest one
	// this code doesn't handle very well when the two largest components has the same size. It simply chose one with 'smaller index'
	public int [] getLargestComponent()
	{
		int maxSize = 0;
		int theLargestIndex = 0;
		for (int i = 0; i < allComponent.size(); i++)
		{
			if (allComponent.elementAt(i).size() > maxSize)
			{
				theLargestIndex = i;
				maxSize = allComponent.elementAt(i).size();
			}
		}

		Integer[] temp = (Integer[]) allComponent.elementAt(theLargestIndex).toArray( new Integer[maxSize] );
		// I don't know why this has to be Integer[] instead of int[].

		int[] largestComponent = new int[maxSize];
		for (int i = 0; i < largestComponent.length; i++)
		{
			largestComponent[i] = temp[i];
		}

		return largestComponent;
	}


	// -------------------- test the ConnectedComponent class -------------------------
	public static void main (String[] arg)
	{

		// test with full matrix
		float [][] network = {
				{0, 1, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 0},
				{0, 0, 0, 0, 0, 1},
				{0, 0, 0, 0, 1, 0},
		};

		ConnectedComponent result = new ConnectedComponent(network);

		// print all components
		System.out.println("All components: ");
		for (int i = 0; i < result.allComponent.size(); i++)
		{
			System.out.print("     Component " + i + ": ");
			Integer[] temp = (Integer[]) result.allComponent.elementAt(i).toArray( new Integer[result.allComponent.elementAt(i).size()] );
			// I don't know why this has to be Integer[] instead of int[].
			for (int j = 0; j < temp.length; j++)
			{
				System.out.print(temp[j] + ", ");
			}
			System.out.println();
		}

		// print the largest connected component
		int [] largestComponent = result.getLargestComponent();
		System.out.println("The largest component: ");
		for (int j = 0; j < largestComponent.length; j++)
		{
			System.out.print(largestComponent[j] + ", ");
		}
		System.out.println();
	}

}
