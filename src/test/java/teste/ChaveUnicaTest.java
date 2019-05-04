package teste;

import br.com.cartorio.protesto.cenprot.util.ChaveUnicaUtils;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;

public class ChaveUnicaTest 
{
    
    @Test
    public void gerar()
    {
        int ibge         = 3550308;
        int apontamentoN = 127;
        int cartorioN    = 1;
        
        Calendar c = Calendar.getInstance();
        c.set( Calendar.YEAR  , 2014 );
        c.set( Calendar.MONTH , 11   );         // dezembro
        c.set( Calendar.DAY_OF_MONTH , 29 );
        
        String esperado = "35503080000127401141229";
        String chave = ChaveUnicaUtils.gerar( ibge , apontamentoN , cartorioN , c.getTime() );
        
        System.out.println( "esperado....: " + esperado );
        System.out.println( "resultado...: " + chave );
        
        Assert.assertTrue( "A chave gerada não é igual a esperada." , esperado.equals( chave ) );
        Assert.assertTrue("A chave gerada não é válida." , ChaveUnicaUtils.isValido( chave ) );
    }
    
}