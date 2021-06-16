/*Sparse matrix class
 * Retrieve from: http://www.sanfoundry.com/java-program-implement-sparse-matrix/
 * Modification: change from Object datatype to Float datatype
 * */

public class SparseArray
{
	private List start;
	private int index;

	SparseArray(int index)
	{
		start = new List();
		this.index = index;
	}


	public void store(int index, float value)
	{
		if (index >= 0 && index < this.index)
		{
			if (value != 0)
				start.store(index, value);

		} else
		{
			System.out.println("INDEX OUT OF BOUNDS");
		}
	}



	public float fetch(int index)
	{
		if (index >= 0 && index < this.index)
			return start.fetch(index);

		else
		{
			System.out.println("INDEX OUT OF BOUNDS");
			return 0f;
		}
	}


	public int elementCount()
	{
		return start.elementCount();
	}

}