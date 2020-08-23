
public class Fatorial
{
	private int numCalc;	
	
	/**
	 * @param numCalc
	 */
	public Fatorial(int numCalc)
	{
		super();
		this.numCalc = numCalc;
	}

	public int getFat()
	{
	   int fat = 1;
	   for(int i = 1; i <= this.numCalc; i++)
	   {
	       fat *= i;
	   }
	   
	   return fat;
	}
}
