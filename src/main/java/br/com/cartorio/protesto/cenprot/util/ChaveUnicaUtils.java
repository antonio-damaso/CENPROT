package br.com.cartorio.protesto.cenprot.util;

import java.util.Calendar;
import java.util.Date;

public class ChaveUnicaUtils 
{
    private static final int[] FATORES  = {5,6,7,3,8,4,5,7,6,6,7,6,7,7,4,1,9,5,1,1,0,2,0};
    private static final int QUANTIDADE = 23;
    private static final int DV_POSICAO = 14;
    
    private ChaveUnicaUtils()
    {
        // faz nada
    }
    
    public static final String gerar( int ibge , int apontamentoN , int cartorioN , Date data )
    {
        int[] chave = new int[ QUANTIDADE ];
        copia( chave , ibge         ,  0 , 7 );  // 0 a 6
        copia( chave , apontamentoN ,  7 , 7 );  // 7 a 13
        copia( chave , cartorioN    , 15 , 2 );  // 15 a 16
        copia( chave , data         , 17 , 6 );  // 17 a 23
        
        chave[ DV_POSICAO ] = digitoVerificador( chave );  // 14
        
        // ------
        
        String chaveStr = "";
        for( int i : chave )
        {
            chaveStr += i;
        }
        
        return chaveStr;
    }
    
    public static final int digitoVerificador( int[] chave )
    {
        int total = 0;
        
        for( int i = 0 ; i < chave.length ; i++ )
        {
            int fator = FATORES[ i ];
            int valor = chave[ i ];
            
            total += ((valor * fator) % 10);
        }
        
        return 10 - (total % 10);
    }
    
    public static final boolean isValido( String chave )
    {
        if( chave == null 
            || chave.trim().isEmpty() 
            || chave.length() != QUANTIDADE )
        {
            return false;
        }
        
        // ---------------
        
        int[] chaveInt = new int[ QUANTIDADE ];
        
        for( int i = 0 ; i < chave.length() ; i++ )
        {
            int valor = Integer.parseInt( chave.charAt( i ) + "" );
            chaveInt[ i ] = valor;
        }
        
        chaveInt[ DV_POSICAO ] = 0;
        int dv = digitoVerificador( chaveInt );
        
        return (dv + "").equals( chave.charAt( DV_POSICAO ) + "");
    }
    
    private static void copia( int[] chave , int valor , int posicao , int quantidade )
    {
        String valorStr = valor + "";
        int diff = quantidade - valorStr.length();
        
        if( diff > 0 )
        {
            String zero = "";
            
            for( int i = 0 ; i < diff ; i++ )
            {
                zero += "0";
            }
            
            valorStr = zero + valorStr;
        }
        
        // ----------------
        
        for( int i = 0 ; i < quantidade ; i++ )
        {
            chave[ posicao + i ] = Integer.parseInt( valorStr.charAt( i ) + "" );
        }
    }
    
    private static void copia( int[] chave , Date data , int posicao , int quantidade )
    {
        Calendar c = Calendar.getInstance();
        c.setTime( data );
        
        final int ano = c.get( Calendar.YEAR ) % 100;
        final int mes = c.get( Calendar.MONTH ) + 1;
        final int dia = c.get( Calendar.DAY_OF_MONTH );
        
        final int valor = ano * 10000
                        + mes * 100
                        + dia;
        
        copia( chave , valor , posicao , quantidade );
    }
    
}