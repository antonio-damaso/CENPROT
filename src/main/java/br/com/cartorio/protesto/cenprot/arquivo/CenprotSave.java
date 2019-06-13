package br.com.cartorio.protesto.cenprot.arquivo;

import br.com.cartorio.protesto.cenprot.Cenprot;
import br.com.cartorio.protesto.cenprot.CenprotAnuencia;
import br.com.cartorio.protesto.cenprot.CenprotApresentante;
import br.com.cartorio.protesto.cenprot.CenprotCancelamentoOnline;
import br.com.cartorio.protesto.cenprot.CenprotDevedor;
import br.com.cartorio.protesto.cenprot.CenprotEndereco;
import br.com.cartorio.protesto.cenprot.CenprotParte;
import br.com.cartorio.protesto.cenprot.CenprotTitulo;
import br.com.cartorio.protesto.cenprot.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CenprotSave 
{
    public static final SimpleDateFormat DF = new SimpleDateFormat( "yyyy-MM-dd" );
    private final StringBuilder builder;
    
    public CenprotSave() 
    {
        builder = new StringBuilder();
    }
    
    public String converter( Cenprot cenprot )
    {
        //empty StringBuilder
        builder.setLength( 0 );
        
        if( cenprot.getCodigoCartorio() != null 
            && cenprot.getCodigoCartorio().length() < 2 )
        {
            cenprot.setCodigoCartorio( "0" + cenprot.getCodigoCartorio() );
        }
        
        append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
        append( "<estados total=\"1\" versao=\"1.19\">"   );
        append( "<estado uf=\"" + StringUtils.validoUF( cenprot.getUF() ) + "\">" );
        append( "<cartorios total=\"1\">" );
        append( "<cartorio codigo=\"" + cenprot.getCodigoCartorio() + "\" comarca=\"" + cenprot.getCodigoIBGE()+ "\">" );
        
        append( "<apresentantes total=\"" + cenprot.getApresentantes().size() + "\">" );
        
        for( CenprotApresentante apresentante : cenprot.getApresentantes() )
        {
            apresentante( apresentante );
        }
        
        append( "</apresentantes>" );
        append( "</cartorio>"  );
        append( "</cartorios>" );
        append( "</estado>"    );
        append( "</estados>"   );
        
        return builder.toString();
    }
    
    private void apresentante( CenprotApresentante apresentante )
    {
        String codigo = apresentante.getCodigo() == null || apresentante.getCodigo().isEmpty()
                ? "000"
                : apresentante.getCodigo();
        
        if( codigo.length() < 3 )
        {
            codigo = "00" + codigo;
        }
        
        append( "<apresentante codigo=\""+ codigo +"\">"  );
        append( "nome"    , apresentante.getNome() , 150 );
        
        if( apresentante.getCpfOuCnpj() != null 
            && !apresentante.getCpfOuCnpj().trim().isEmpty() )
        {
            String documento = 
                apresentante.getCpfOuCnpj().length() <= 11
                ? "CPF" : "CNPJ";
            
            append( documento , apresentante.getCpfOuCnpj() );
        }
        
        append( "tipo_apresentante" , apresentante.getTipo() );
        
        append( "<titulos total=\"" + apresentante.getTitulos().size() + "\">"  );
        
        for( CenprotTitulo titulo : apresentante.getTitulos() )
        {
            titulo( titulo );
        }
        
        append( "</titulos>" );
        
        enderecos( apresentante.getEnderecos() );
        append( "</apresentante>" );
        
        //controles
    }
    
    private void enderecos( List<CenprotEndereco> enderecos )
    {
        if( enderecos == null || enderecos.isEmpty() )
        {
            return ;
        }
        
        append( "<enderecos total=\""+ enderecos.size() +"\">" );
        
        for(int i = 0 ; i < enderecos.size() ; i++ )
        {
            CenprotEndereco endereco = enderecos.get( i );
            
            append( "<endereco sequencia=\""+ (i + 1) +"\">" );
            append( "endereco"     , endereco.getEndereco() );
            append( "complemento"  , endereco.getComplemento() );
            append( "cep"          , StringUtils.numeroValido( endereco.getCep() ) );
            append( "bairro"       , endereco.getBairro()   );
            append( "cidade"       , endereco.getCidade()   );
            append( "uf"           , StringUtils.validoUF( endereco.getUf() )      );
            append( "</endereco>" );
        }
        
        append( "</enderecos>" );
    }
    
    private void titulo( CenprotTitulo titulo )
    {
        if( titulo.getValorTitulo() == 0 
            && titulo.getValorProtestado() > 0 )
        {
            titulo.setValorTitulo( titulo.getValorProtestado() );
        }
        
        append( "<titulo id=\"" + titulo.getChaveUnica() + "\">"  );
        parte ( "cedente"           , titulo.getCedente()     );
        parte ( "sacador"           , titulo.getSacador()     );
        append( "nosso_numero"      , titulo.getNossoNumero()  , 15 );
        append( "especie"           , StringUtils.validoEspecie( titulo.getEspecie() ) );
        append( "numero_titulo"     , titulo.getNumeroTitulo() , 40 );
        append( "data_emissao"      , titulo.getDataEmissao()       );
        append( "data_vencimento"   , titulo.getDataVencimento()    );
        append( "tipo_moeda"        , titulo.getTipoMoeda()         );
        append( "valor"             , titulo.getValorTitulo()       );
        append( "saldo"             , titulo.getValorProtestado()   );
        append( "praca_protesto"    , titulo.getPracaProtesto() , 20 );
        append( "endosso"           , titulo.getEndosso()           );
        append( "tipo_endosso"      , titulo.getTipoEndosso()       );
        append( "aceite"            , titulo.getAceite()            );
        
        append( "<devedores total=\""+ titulo.getDevedores().size() +"\">"  );
        
        for( int i = 0 ; i < titulo.getDevedores().size() ; i++ )
        {
            devedor( i + 1 , titulo.getDevedores().get( i ) );
        }
        
        append( "</devedores>" );
        
        append( "data_apresentacao"     , titulo.getDataApresentacao() );
        append( "protocolo"             , titulo.getProtocolo()  , 10 );
        append( "data_protocolo"        , titulo.getDataProtocolo() );
        append( "ocorrencia"            , titulo.getOcorrencia() );
        append( "data_ocorrencia"       , titulo.getDataOcorrencia() );
        
        append( "<contrato>" );
        append( "operacao" , titulo.getContratoOperacao() , 5 );
        append( "identificador" , titulo.getContratoIdentificador() , 15 );
        append( "parcela"  , titulo.getContratoParcela() , 3 );
        append( "tipo"     , titulo.getContratoTipo()    , 1 );
        append( "</contrato>" );
        
        append( "tipo_protesto"   , titulo.getTipoProtesto() );
        append( "motivo_protesto" , titulo.getMotivoProtesto() );
        
        if( titulo.getValorOutrasDespesas() > 0 )
        {
            append( "outras_despesas" , titulo.getValorOutrasDespesas() );
        }
        
        append( "tipo_livro"      , titulo.getTipoLivro()   , 1 );
        append( "num_livro"       , titulo.getNumeroLivro() );
        append( "num_folha"       , titulo.getNumeroFolha() );
        append( "acervo_herdado"  , titulo.isAcervoHerdado() );
        
        if( titulo.getAcervoCartorio() > 0 )
        {
            append( "acervo_cartorio" , titulo.getAcervoCartorio() );
        }
        
        if( titulo.getDataAverbacao() != null )
        {
            append( "data_averbacao"  , titulo.getDataAverbacao() );
        }
        
        cancelamento( titulo.getFaixaValorProtestado() , titulo.getCancelamentos() );
        anuencia( titulo.getAnuencia() );
        
        append( "microempresa"    , titulo.isMicroempresa() );
        append( "instrumento_eletronico" , titulo.getInstrumentoEletronico() );
        
        irregularidades( titulo.getIrregulares() );
        append( "</titulo>" );
    }
    
    private void devedor( int sequencia , CenprotDevedor devedor )
    {
        append( "<devedor sequencia=\"" + sequencia + "\">"  );
        append( "nome"     , devedor.getNome()      );
        
        if( devedor.getCpfOuCnpj() != null 
            && !devedor.getCpfOuCnpj().trim().isEmpty() )
        {
            String documento = devedor.getCpfOuCnpj().length() <= 11
                ? "CPF" : "CNPJ";
            
            append( documento  , devedor.getCpfOuCnpj() );
        }
        
        append( "intimado" , devedor.isIntimado()   );
        append( "edital"   , devedor.isIntimado()   );
        append( "edital_motivo"   , devedor.getEditalMotivo() , 200 );
        
        enderecos( devedor.getEnderecos() );
        append( "</devedor>" );
    }
    
    private void parte( String tipo , CenprotParte parte )
    {
        if( parte == null )
        {
            parte = new CenprotParte();
        }
        
        append( "<"+ tipo +">"  );
        append( "nome"     , parte.getNome()      );
        
        if( parte.getCpfOuCnpj() != null 
            && !parte.getCpfOuCnpj().trim().isEmpty() )
        {
            String documento = 
                parte.getCpfOuCnpj().length() <= 11
                ? "CPF" : "CNPJ";
            
            append( documento  , parte.getCpfOuCnpj() );
        }
        
        enderecos( parte.getEnderecos() );
        append( "</"+ tipo +">" );
    }
    
    private void cancelamento( String faixa , List<CenprotCancelamentoOnline> cancelamentos )
    {
        if( faixa == null 
            || faixa.trim().isEmpty() 
            || cancelamentos == null
            || cancelamentos.isEmpty() )
        {
            return ;
        }
        
        append( "<dados_cancelamento>"  );
        append( "faixa_valor_protestado" , faixa , 2 );
        
        append( "<custas_online total=\"" + cancelamentos.size() + "\">" );
        for( CenprotCancelamentoOnline cancelamento : cancelamentos )
        {
            append( "<custa_online>" );
            append( "codigo"     , cancelamento.getCodigoCusta() , 2 );
            append( "quantidade" , cancelamento.getQuantidade()  );
            append( "</custa_online>" );
        }
        append( "</custas_online>" );
        
        append( "</dados_cancelamento>" );
        
        /*
        <dados_cancelamento>
        <faixa_valor_protestado>01</faixa_valor_protestado>
        <custa_canc_online>
        <codigo_custa>A</codigo_custa>
        <qtde_custa_efetuada>2</qtde_custa_efetuada>
        </custa_canc_online>
        <custa_canc_online>
        <codigo_custa>B</codigo_custa>
        <qtde_custa_efetuada>1</qtde_custa_efetuada>
        </custa_canc_online>
        </dados_cancelamento>
        */
    }
    
    private void anuencia( CenprotAnuencia anuencia )
    {
        if( anuencia == null )
        {
            append( "<anuencia>"   );
            append( "<flag>false</flag>" );
            append( "</anuencia>"  );
            
            return ;
        }
        
        append( "<anuencia>"  );
        append( "flag"     , anuencia.isFlag()    );
        append( "origem"   , anuencia.getOrigem() );
        append( "origem_datahora" , anuencia.getData() );
        append( "apresentante_permitido" , anuencia.isApresentantePermitido() );
        append( "sacador_permitido"      , anuencia.isSacadorPermitido() );
        append( "cedente_permitido"      , anuencia.isCedentePermitido() );
        //append( "credor_atual"      , anuencia.getCredorAtual() );
        append( "</anuencia>" );
        
        /*
        <dados_anuencia>
        <flag_anuencia>N</flag_anuencia>
        <origem_anuencia />
        <data_anuencia />
        <anuencia_apresentante_permitida>S<anuencia_apresentante_permitida>
        <anuencia_sacador_permitida>S<anuencia_sacador_permitida>
        <anuencia_cedente_permitida>S<anuencia_cedente_permitida>
        <credor_atual>S<credor_atual>
        </dados_anuencia>
        
        <anuencia_nao_cra />
        */
    }
    
    private void irregularidades( List<Integer> irregularidades )
    {
        if( irregularidades == null || irregularidades.isEmpty() )
        {
            //append( "<irregularidades />" );
            return ;
        }
        
        append( "<irregularidades total=\""+ irregularidades.size() +"\">" );
        
        for( Integer codigo : irregularidades )
        {
            append( "codigo" , codigo );
        }
        
        append( "</irregularidades>" );
    }
    
    // -----------------------------------
    // ----------------------------------- METODOS AUXILIARES
    // -----------------------------------
    
    private void append( String text )
    {
        builder.append( text )
            .append( "\n" );
    }
    
    private void append( String tag , String valor )
    {
        append( tag , valor , -1 );
    }
    
    private void append( String tag , String valor , int size )
    {
        if( valor == null || valor.trim().isEmpty() )
        {
            builder.append( "<" )
            .append( tag )
            .append( " />\n" );
        }
        else
        {
            builder.append( "<" ).append( tag ).append( ">" )
            .append( xmlEscapeText( valor , size ) )
            .append( "</" ).append( tag ).append( ">\n" );
        }
    }
    
    private void append( String tag , boolean valor )
    {
         append( tag , valor + "" );
    }
    
    private void append( String tag , int valor )
    {
        if( valor < 0 )
        {
            append( tag , (String) null );
        }
        else
        {
            String valorStr = valor + "";
            
            /*
            if( valorStr.length() < casas )
            {
                while( valorStr.length() < casas )
                {
                    valorStr = "0" + casas;
                }
            }
            */
            
            append( tag , valorStr );
        }
    }
    
    private void append( String tag , Double valor )
    {
        append( tag , valor < 0 ? null : String.format( Locale.ENGLISH , "%.2f" , valor ) );
    }
    
    private void append( String tag , Date valor )
    {
        append( tag , valor == null ? null : DF.format( valor ) );
    }
    
    /**
    * This method ensures that the output String has only
    * valid XML unicode characters as specified by the
    * XML 1.0 standard. For reference, please see
    * <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the
    * standard</a>. This method will return an empty
    * String if the input is null or empty.
    *
    * from: https://stackoverflow.com/questions/4237625/removing-invalid-xml-characters-from-a-string-in-java
    * 
    * @param in   The String whose non-valid characters we want to remove.
    * @param size max size that output could be
    * 
    * @return The in String, stripped of non-valid characters.
    */
   public static String xmlEscapeText( final String in , final int size ) 
   {
        // vacancy test.
        if ( in == null || in.trim().isEmpty() )
        {
            return "";
        }
       
        StringBuilder out = new StringBuilder();
        for( char c : in.toCharArray() )
        {
           switch( c )
           {
            case '<' : out.append("&lt;");   break;
            case '>' : out.append("&gt;");   break;
            case '\"': out.append("&quot;"); break;
            case '&' : out.append("&amp;");  break;
            case '\'': out.append("&apos;"); break;
            default:
                if( c > 0x7e )
                {
                   out.append( "&#" ).append((int) c).append( ";" );
                }
                else
                {
                   out.append( c );
                }
            }
        }
        
        if( "YK0010000006340".equals( in ) )
        {
            System.out.println( "size: " + size );
            System.out.println( "out.: " + out.substring( 0 , size ) );
        }
        
        return out.length() > size && size > 0
            ? out.substring( 0 , size ) 
            : out.toString();
   }
   
}