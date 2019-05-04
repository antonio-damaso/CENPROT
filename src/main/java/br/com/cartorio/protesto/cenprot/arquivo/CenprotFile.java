package br.com.cartorio.protesto.cenprot.arquivo;

import br.com.cartorio.protesto.cenprot.Cenprot;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class CenprotFile 
{
    private static final SimpleDateFormat DF = new SimpleDateFormat( "yyyyMMdd" );
    private static final String FORMAT = "EE-MMMMMMM-CC-T-AAAAAMMDD-SS.xml";
    
    /**
     * EE-MMMMMMM-CC-T-AAAAAMMDD-SS.xml
     * 
     * EE:          2 dígitos para Estado
     * MMMMMMM:     7 dígitos para código IBGE da comarca
     * CC:          2 dígitos para código do cartório
     * T:           1 dígito para o tipo de transação do arquivo (P = protestado, D= demais ocorrências)
     * AAAAMMDD:    8 dígitos para data de envio (ano, mês, dia) da remessa
     * SS:          2 dígitos para sequencia da remessa dentro do dia, por Estado/comarca/cartório.
     * 
     */
    
    public static File salvar( File file , Cenprot cenprot ) throws Exception
    {
        File f = file.isDirectory()
            ? new File( file , createName( cenprot ) )
            : file; 
        
        if( !f.exists() )
        {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        
        FileWriter writer = new FileWriter( f );
        writer.write( new CenprotSave().converter( cenprot ) );
        writer.close();
        
        return f;
    }
    
    public static String createName( Cenprot cenprot )
    {
        String cartorio = cenprot.getCodigoCartorio();
        if( cartorio.length() > 2 )
        {
            cartorio = cartorio.substring( cartorio.length() - 2 );
        }
        
        String dia = (cenprot.getRemessaNumero() < 10 ? "0" : "") 
            + cenprot.getRemessaNumero();
        
        return FORMAT
            .replace( "EE"      , cenprot.getUF()         )
            .replace( "MMMMMMM" , cenprot.getCodigoIBGE() )
            .replace( "CC"      , cartorio )
            .replace( "T"       , cenprot.getTipoTransacao() )
            .replace( "AAAAAMMDD" , DF.format( cenprot.getDataEnvio() ) )
            .replace( "SS"      , dia );
    }
    
}