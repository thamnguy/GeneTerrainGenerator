package GenerateBackendMatrix;

public class CommonMath 
{
	// --------------------- maximum value in an array --------------------------
	public static float max(float [] array)
	{
		float maxValue = array[0];
		for (int i = 1; i < array.length; i++)
		{
			if (array[i] > maxValue)
			{
				maxValue = array[i];
			}
		}

		return maxValue;
	}


	// --------------------- maximum value in an array with specification of valid/invalid data point --------------------------
	public static float max(float [] array, boolean [] isValid)
	{
		float maxValue = Float.MIN_VALUE;
		for (int i = 0; i < isValid.length; i++)
		{
			if (isValid[i])
			{
				maxValue = array[i];
				break;
			}
		}

		for (int i = 1; i < array.length; i++)
		{
			if (array[i] > maxValue && isValid[i])
			{
				maxValue = array[i];
			}
		}

		return maxValue;
	}


	//------------------ minimum value in an array -----------------------
	public static float min(float [] array)
	{
		float minValue = array[0];
		for (int i = 1; i < array.length; i++)
		{
			if (array[i] < minValue)
			{
				minValue = array[i];
			}
		}

		return minValue;
	}


	// --------------------- minimum value in an array with specification of valid/invalid data point --------------------------
	public static float min(float [] array, boolean [] isValid)
	{
		float minValude = Float.MAX_VALUE;
		for (int i = 0; i < isValid.length; i++)
		{
			if (isValid[i])
			{
				minValude = array[i];
				break;
			}
		}

		for (int i = 1; i < array.length; i++)
		{
			if (array[i] < minValude && isValid[i])
			{
				minValude = array[i];
			}
		}

		return minValude;
	}


	// ------------- get the square distance between two points (x1, y1), (x2, y2) ------------------------
	public static float getSquareDistance(float x1, float y1, float x2, float y2)
	{
		return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
	}

	// ------------- get the square distance between two points (x1, y1), (x2, y2) ------------------------
	public static float getDistance(float x1, float y1, float x2, float y2)
	{
		return (float) Math.sqrt(getSquareDistance(x1, y1, x2, y2));
	}
}
