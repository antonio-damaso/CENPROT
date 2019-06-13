package br.com.cartorio.protesto.cenprot.util;

import java.util.Arrays;

public class StringUtils 
{
    public static final String[] ESTADOS = {"AC", "AL", "AM", "AP", "BA", "CE", 
        "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", 
        "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"};
    
    public static final String[] ESPECIES = { "CBI","CC","CCB","CCC","CCE"
        ,"CCI","CCJ","CCO","CCR","CD","CDA","CDI","CDJ","CH","CHP","CJV","CM"
        ,"CPH","CPS","CRH","CRP","CT","CTH","DM","DMI","DR","DRI","DS","DSI"
        ,"DV","EC","LC","NCC","NCE","NCI","NCR","NP","NPR","RA","SJ","TA","TM"
        ,"TS","W","CAC","CACI","CADC","CAM","CCJ","CDT","CF","CL","CLC","CRC"
        ,"CRJ","CRPH","DD","ED","FPS","FVM","ND","OD","RFED","RPS","RPSI","SJ"
        ,"TAJ","TJD","TS","ZZZ" };
    
    public static String validoUF( String valor )
    {
        if( valor == null || valor.trim().isEmpty() )
        {
            return "PE";
        }
        
        int index = Arrays.binarySearch( ESTADOS , valor.toUpperCase() );
        return index < 0 ? "PE": ESTADOS[ index ]; 
    }
    
    public static String validoEspecie( String valor )
    {
        if( valor == null || valor.trim().isEmpty() )
        {
            return "DMI";
        }
        
        int index = Arrays.binarySearch( ESPECIES , valor.toUpperCase() );
        return index < 0 ? "DMI": ESPECIES[ index ]; 
    }
    
    public static String numeroValido( String value )
    {
        String valor = soNumero( value );
        return valor.isEmpty() ? "0": valor;
    }
    
    public static String soNumero( String value )
    {
        if( value == null || value.trim().isEmpty() )
        {
            return "";
        }
        
        StringBuilder builder = new StringBuilder();
        
        for( char c : value.toCharArray() )
        {
            if( Character.isDigit( c ) )
            {
                builder.append( c );
            }
        }
        
        return builder.toString();
    }
    
}