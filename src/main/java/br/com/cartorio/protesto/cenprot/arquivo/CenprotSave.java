    package br.com.cartorio.protesto.cenprot.arquivo;

import br.com.cartorio.protesto.cenprot.Cenprot;
import br.com.cartorio.protesto.cenprot.CenprotAnuencia;
import br.com.cartorio.protesto.cenprot.CenprotApresentante;
import br.com.cartorio.protesto.cenprot.CenprotCancelamentoOnline;
import br.com.cartorio.protesto.cenprot.CenprotDevedor;
import br.com.cartorio.protesto.cenprot.CenprotEndereco;
import br.com.cartorio.protesto.cenprot.CenprotParte;
import br.com.cartorio.protesto.cenprot.CenprotTitulo;
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
            
        append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
        append( "<estados total=\"1\" versao=\"1.19\">"   );
        append( "<estado uf=\"" + cenprot.getUF() + "\">" );
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
        /*
        <codigo>001</codigo>
        <tipo_apresentante>00</tipo_apresentante>
        <tp_doc_apresentante>1</tp_doc_apresentante>
        <doc_apresentante>00000000377104</doc_apresentante>
        <nome_apresentante>BANCO DO BRASIL S/A</nome_apresentante>
        <end_apresentante>AV SAO JOAO 32 16 ANDAR</end_apresentante>
        <bairro_apresentante>CENTRO</bairro_apresentante>
        <cep_apresentante>01012000</cep_apresentante>
        <cidade_apresentante>SAO PAULO</cidade_apresentante>
        <uf_apresentante>SP</uf_apresentante>
        */
        
        String documento = 
            apresentante.getCpfOuCnpj() != null && apresentante.getCpfOuCnpj().length() <= 11
            ? "CPF" : "CNPJ";
        
        append( "<apresentante codigo=\""+ apresentante.getCodigo() +"\">"  );
        append( "nome"    , apresentante.getNome() );
        append( documento , apresentante.getCpfOuCnpj() );
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
        append( "<enderecos total=\""+ enderecos.size() +"\">" );
        
        for(int i = 0 ; i < enderecos.size() ; i++ )
        {
            CenprotEndereco endereco = enderecos.get( i );
            
            append( "<endereco sequencia=\""+ (i + 1) +"\">" );
            append( "endereco"     , endereco.getEndereco() );
            append( "complemento"  , endereco.getComplemento() );
            append( "cep"          , endereco.getCep()      );
            append( "bairro"       , endereco.getBairro()   );
            append( "cidade"       , endereco.getCidade()   );
            append( "uf"           , endereco.getUf()       );
            append( "</endereco>" );
        }
        
        append( "</enderecos>" );
    }
    
    private void titulo( CenprotTitulo titulo )
    {
        append( "<titulo id=\"" + titulo.getChaveUnica() + "\">"  );
        parte ( "cedente"           , titulo.getCedente() );
        parte ( "sacador"           , titulo.getSacador() );
        append( "nosso_numero"      , titulo.getNossoNumero() );
        append( "especie"           , titulo.getEspecie() );
        append( "numero_titulo"     , titulo.getNumeroTitulo() );
        append( "data_emissao"      , titulo.getDataEmissao() );
        append( "data_vencimento"   , titulo.getDataVencimento() );
        append( "tipo_moeda"        , titulo.getTipoMoeda() );
        append( "valor"             , titulo.getValorTitulo() );
        append( "saldo"             , titulo.getValorProtestado() );
        append( "praca_protesto"    , titulo.getPracaProtesto() );
        append( "endosso"           , titulo.getEndosso() );
        append( "tipo_endosso"      , titulo.getTipoEndosso() );
        append( "aceite"            , titulo.getAceite() );
        
        append( "<devedores total=\""+ titulo.getDevedores().size() +"\">"  );
        
        for( int i = 0 ; i < titulo.getDevedores().size() ; i++ )
        {
            devedor( i + 1 , titulo.getDevedores().get( i ) );
        }
        
        append( "</devedores>" );
        
        append( "data_apresentacao"     , titulo.getDataApresentacao() );
        append( "protocolo"             , titulo.getProtocolo() );
        append( "data_protocolo"        , titulo.getDataProtocolo() );
        append( "ocorrencia"            , titulo.getOcorrencia() );
        append( "data_ocorrencia"       , titulo.getDataOcorrencia() );
        
        append( "<contrato>" );
        append( "operacao" , titulo.getContratoOperacao() );
        append( "identificador" , titulo.getContratoIdentificador() );
        append( "parcela"  , titulo.getContratoParcela() );
        append( "tipo"     , titulo.getContratoTipo() );
        append( "</contrato>" );
        
        append( "tipo_protesto"   , titulo.getTipoProtesto() );
        append( "motivo_protesto" , titulo.getMotivoProtesto() );
        append( "outras_despesas" , titulo.getValorOutrasDespesas() );
        append( "tipo_livro"      , titulo.getTipoLivro() );
        append( "num_livro"       , titulo.getNumeroLivro() );
        append( "num_folha"       , titulo.getNumeroFolha() );
        append( "acervo_herdado"  , titulo.isAcervoHerdado() );
        append( "acervo_cartorio" , titulo.getAcervoCartorio() );
        
        append( "data_averbacao"  , titulo.getDataAverbacao() );
        cancelamento( titulo.getFaixaValorProtestado() , titulo.getCancelamentos() );
        anuencia( titulo.getAnuencia() );
        
        append( "microempresa"    , titulo.isMicroempresa() );
        append( "instrumento_eletronico" , titulo.getInstrumentoEletronico() );
        
        irregularidades( titulo.getIrregulares() );
        append( "</titulo>" );
    }
    
    private void devedor( int sequencia , CenprotDevedor devedor )
    {
        String documento = 
            devedor.getCpfOuCnpj() != null && devedor.getCpfOuCnpj().length() <= 11
            ? "CPF" : "CNPJ";
        
        append( "<devedor sequencia=\"" + sequencia + "\">"  );
        append( "nome"     , devedor.getNome()      );
        append( documento  , devedor.getCpfOuCnpj() );
        append( "intimado" , devedor.isIntimado()   );
        append( "edital"   , devedor.isIntimado()   );
        append( "edital_motivo"   , devedor.getEditalMotivo() );
        
        enderecos( devedor.getEnderecos() );
        append( "</devedor>" );
    }
    
    private void parte( String tipo , CenprotParte parte )
    {
        String documento = 
            parte.getCpfOuCnpj() != null && parte.getCpfOuCnpj().length() <= 11
            ? "CPF" : "CNPJ";
        
        append( "<"+ tipo +">"  );
        append( "nome"     , parte.getNome()      );
        append( documento  , parte.getCpfOuCnpj() );
        enderecos( parte.getEnderecos() );
        append( "</"+ tipo +">" );
    }
    
    private void cancelamento( String faixa , List<CenprotCancelamentoOnline> cancelamentos )
    {
        append( "<dados_cancelamento>"  );
        append( "faixa_valor_protestado" , faixa );
        
        append( "<custas_online total=\"" + cancelamentos.size() + "\">" );
        for( CenprotCancelamentoOnline cancelamento : cancelamentos )
        {
            append( "<custa_online>" );
            append( "codigo"     , cancelamento.getCodigoCusta() );
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
            append( "<dados_anuencia>" );
            append( "<flag />"  );
            append( "</dados_anuencia>" );
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
        builder.append( text );
    }
    
    private void append( String tag , String valor )
    {
        if( valor == null )
        {
            builder.append( "<" )
            .append( tag )
            .append( " />" );
        }
        else
        {
            builder.append( "<" ).append( tag ).append( ">" )
            .append( valor )
            .append( "</" ).append( tag ).append( ">" );
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
    
}