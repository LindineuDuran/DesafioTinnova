/**
 * 
 */

/**
 * @author LDURAN
 *
 */
public class Ordenacao
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int array[] = new int[] {5, 3, 2, 4, 7, 1, 0, 6};
		
		exibeArray(array);
		
		bubleSort(array);
	}

	/**
	 * M�todo de Ordena��o
	 * @param array
	 */
	private static void bubleSort(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			for (int j = 0; j < array.length-1; j++)
			{
				if(array[j] > array[j+1])
				{
					int varTemp = array[j];
					array[j] = array[j+1];
					array[j+1] = varTemp;
				}
				
				exibeArray(array);
			}
		}
	}
	
	/**
	 * Exibe sequ�ncia de n�meros do Array
	 * @param array
	 */
	public static void exibeArray(int[] array)
	{
		String listaExibe = "";
		for (int i = 0; i < array.length; i++)
		{
			listaExibe += array[i];
			if (i < array.length-1)
			{
				listaExibe += ",";
			}
		}
		
		System.out.println(listaExibe);
	}
}
