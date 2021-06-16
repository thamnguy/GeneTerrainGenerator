/*Sparse matrix class
 * Retrieve from: http://www.sanfoundry.com/java-program-implement-sparse-matrix/
 * Modification: change from Object datatype to Float datatype
 * */

public class SparseMatrix

{

	private int N;
	private SparseArray sparsearray[];


	public SparseMatrix(int N)
	{
		this.N = N;
		sparsearray = new SparseArray[N];

		for (int index = 0; index < N; index++)
		{
			sparsearray[index] = new SparseArray(N);
		}
	}


	public void store(int rowindex, int colindex, float value)
	{
		if (rowindex < 0 || rowindex > N)
			throw new RuntimeException("row index out of bounds");

		if (colindex < 0 || colindex > N)
			throw new RuntimeException("col index out of bounds");

		sparsearray[rowindex].store(colindex, value);
	}


	public float get(int rowindex, int colindex)
	{
		if (rowindex < 0 || colindex > N)
			throw new RuntimeException("row index out of bounds");

		if (rowindex < 0 || colindex > N)
			throw new RuntimeException("col index out of bounds");

		return (sparsearray[rowindex].fetch(colindex));
	}
	
	
	public int getN()
	{
		return N;
	}


	public static void main(String[] arg)
	{
		Integer[][] iarray = new Integer[3][3];
		iarray[0][0] = 1;
		iarray[0][1] = null;
		iarray[0][2] = 2;
		iarray[1][0] = null;
		iarray[1][1] = 3;
		iarray[1][2] = null;
		iarray[2][0] = 4;
		iarray[2][1] = 6;
		iarray[2][2] = null;

		SparseMatrix sparseMatrix = new SparseMatrix(3);
		for (int rowindex = 0; rowindex < 3; rowindex++)
		{
			for (int colindex = 0; colindex < 3; colindex++)
			{
				sparseMatrix.store(rowindex, colindex,
						iarray[rowindex][colindex]);
			}
		}

		System.out.println("the sparse Matrix is ");

		for (int rowindex = 0; rowindex < 3; rowindex++)
		{
			for (int colindex = 0; colindex < 3; colindex++)
			{
				System.out.print(sparseMatrix.get(rowindex, colindex) + "\t");
			}
			System.out.println();
		}
	}

}