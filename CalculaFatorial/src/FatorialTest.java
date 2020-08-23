import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class FatorialTest
{
	Fatorial fat;
	
	@Test
	public void testaFatorialNum_6()
	{
		// Instancia um novo Fatorial
		fat = new Fatorial(6);

		// Exibe o fatorial de 6
		System.out.println(fat.getFat());
		
		// Valida o teste
		Assert.assertEquals(720, fat.getFat());
	}
	
	@Test
	public void testaFatorialNum_10()
	{
		// Instancia um novo Fatorial
		fat = new Fatorial(10);

		// Exibe o fatorial de 10
		System.out.println(fat.getFat());
		
		// Valida o teste
		Assert.assertEquals(3628800, fat.getFat());
	}
}
