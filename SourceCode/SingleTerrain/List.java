/*Sparse matrix class
 * Retrieve from: http://www.sanfoundry.com/java-program-implement-sparse-matrix/
 * */

public class List
{
	private int index;
	private float value;
	private List nextindex;


	public List(int index)
	{
		this.index = index;
		nextindex = null;
		//value = null;
	}


	public List()
	{
		index = -1;
		//value = null;
		nextindex = null;
	}


	public void store(int index, float value)
	{
		List current = this;
		List previous = null;
		List node = new List(index);

		node.value = value;

		while (current != null && current.index < index)
		{
			previous = current;
			current = current.nextindex;
		}

		if (current == null)
		{
			previous.nextindex = node;
		}

		else
		{
			if (current.index == index)
			{
				System.out.println("DUPLICATE INDEX");
				return;
			}
			previous.nextindex = node;
			node.nextindex = current;
		}

		return;
	}



	public float fetch(int index)
	{
		List current = this;
		float value = 0;

		while (current != null && current.index != index)
		{
			current = current.nextindex;
		}

		if (current != null)
		{
			value = current.value;
		}

		else
		{
			value = 0;
		}

		return value;
	}


	public int elementCount()
	{
		int elementCount = 0;

		for (List current = this.nextindex; (current != null); current = current.nextindex)
		{
			elementCount++;
		}

		return elementCount;
	}

}