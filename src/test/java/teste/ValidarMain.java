package teste;

import br.com.cartorio.protesto.cenprot.arquivo.CenprotFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ValidarMain 
{
    
    public static void main( String[] args ) throws Exception
    {
        //String xmlFile = "/tmp/cenprot421013205519862073/cenprot-7994086800950245506.xml";
        String xmlFile = "/tmp/cenprot4951847775009175574/cenprot-5314424484154520099.xml";
        String line;
        
        int number = 1;
        BufferedReader reader = new BufferedReader( new FileReader( xmlFile ) );
        while( (line = reader.readLine()) != null )
        {
            System.out.print( "Line " + (number++) + ": " );
            System.out.print( line );
            System.out.print( "\n" );
        }
        
        /*
        String newContent =  content.replaceAll( Pattern.quote( ">" ) , Matcher.quoteReplacement( ">\n" ) );
        
        File file = File.createTempFile( "cenprot-provisorio-" , ".xml" );
        FileWriter writer = new FileWriter( file );
        writer.write( newContent );
        writer.close();
        */
        
        System.out.println( "---------------" );
        System.out.println( "---------------" );
        System.out.println( "---------------" );
        
        CenprotFile.validar( new File( xmlFile ) );
    }
    
}