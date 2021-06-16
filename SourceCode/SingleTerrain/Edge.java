import java.io.Serializable;

// this class simply represents an edge

public class Edge implements Serializable
{
	int beginIndex; // begin node index
	int endIndex; // end node index
	float strength;
	String annotation;
	boolean isDirected; // true means there is a direction from beginIndex to endIndex. False means no direction

	public Edge (int begin, int end)
	{
		beginIndex = begin;
		endIndex = end;
	}

	public Edge (int begin, int end, boolean argDirect)
	{
		beginIndex = begin;
		endIndex = end;
		isDirected = argDirect;
	}

	public Edge (int begin, int end, float argStr)
	{
		beginIndex = begin;
		endIndex = end;
		strength = argStr;
	}
	
	public Edge (int begin, int end, float argStr, boolean argDirect)
	{
		beginIndex = begin;
		endIndex = end;
		strength = argStr;
		isDirected = argDirect;
	}

	// set edge strength
	public void setStrength(float argStr)
	{
		strength = argStr;
	}

	// set edge annotation
	public void setAnnotation(String argAnn)
	{
		annotation = argAnn;
	}

	// set edge directionality
	public void setDirect(boolean argDirect)
	{
		isDirected = argDirect;
	}
}
