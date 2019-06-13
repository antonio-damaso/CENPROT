package teste;

import br.com.cartorio.protesto.cenprot.util.ChaveUnicaUtils;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@org.junit.FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ChaveUnicaTest 
{
    
    @Test
    public void gerar01()
    {
        System.out.println( "Verificando o primeiro caso de chave..." );
        
        int ibge         = 3550308;
        int apontamentoN = 127;
        int cartorioN    = 1;
        
        Calendar c = Calendar.getInstance();
        c.set( Calendar.YEAR  , 2014 );
        c.set( Calendar.MONTH , 11   );         // dezembro
        c.set( Calendar.DAY_OF_MONTH , 29 );
        
        String esperado   = "35503080000127401141229";
        String chave = ChaveUnicaUtils.gerar( ibge , apontamentoN , cartorioN , c.getTime() );
        
        System.out.println( "esperado....: " + esperado );
        System.out.println( "resultado...: " + chave );
        
        Assert.assertTrue( "A chave gerada não é igual a esperada." , esperado.equals( chave ) );
        Assert.assertTrue("A chave gerada não é válida." , ChaveUnicaUtils.isValido( chave ) );
    }
    
    @Test
    public void gerar02()
    {
        System.out.println( "Verificando o segundo caso de chave..." );
        
        int ibge         = 2606804;
        int apontamentoN = 117872;
        int cartorioN    = 1;
        
        Calendar c = Calendar.getInstance();
        c.set( Calendar.YEAR  , 2015 );
        c.set( Calendar.MONTH , 11   );         // dezembro
        c.set( Calendar.DAY_OF_MONTH , 9 );
        
        //String esperado = "2606804 0117558 0 01 151203";
        String esperado = "26068040117872001151209";
        String chave = ChaveUnicaUtils.gerar( ibge , apontamentoN , cartorioN , c.getTime() );
        
        System.out.println( "esperado....: " + esperado );
        System.out.println( "resultado...: " + chave );
        
        Assert.assertTrue( "A chave gerada não é igual a esperada." , esperado.equals( chave ) );
        Assert.assertTrue("A chave gerada não é válida." , ChaveUnicaUtils.isValido( chave ) );
    }
    
}