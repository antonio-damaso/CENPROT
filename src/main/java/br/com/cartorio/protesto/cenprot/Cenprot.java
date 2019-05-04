package br.com.cartorio.protesto.cenprot;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Cenprot 
{
    public static final String TIPO_TRANSACAO_PROTESTADO = "P";
    public static final String TIPO_TRANSACAO_OUTROS     = "D";
    public static final String TIPO_TRANSACAO_HISTORICO  = "H";
    
    private String uf;                          // PE, SP, RJ ....
    private String codigoIBGE;                  // Código IBGE da comarca
    private String codigoCartorio;              // Código do cartório no 
    
    // ------------- nome do arquivo
    private String tipoTransacao;
    private int remessaNumero;
    private Date dataEnvio;
    
    private final List<CenprotApresentante> apresentantes;
    
    public Cenprot() 
    {
        this.apresentantes = new LinkedList<CenprotApresentante>();
    }

    public String getCodigoCartorio()
    {
        return codigoCartorio;
    }

    public void setCodigoCartorio( String codigoCartorio )
    {
        this.codigoCartorio = codigoCartorio;
    }
    
    public String getUF()
    {
        return uf;
    }

    public void setUF( String uf )
    {
        this.uf = uf;
    }

    public String getCodigoIBGE()
    {
        return codigoIBGE;
    }

    public void setCodigoIBGE( String codigoIBGE )
    {
        this.codigoIBGE = codigoIBGE;
    }
    
    public List<CenprotApresentante> getApresentantes()
    {
        return apresentantes;
    }
    
    public String getTipoTransacao()
    {
        return tipoTransacao;
    }

    public void setTipoTransacao( String tipoTransacao )
    {
        this.tipoTransacao = tipoTransacao;
    }

    public int getRemessaNumero()
    {
        return remessaNumero;
    }

    public void setRemessaNumero( int remessaNumero )
    {
        this.remessaNumero = remessaNumero;
    }

    public Date getDataEnvio()
    {
        return dataEnvio;
    }

    public void setDataEnvio( Date dataEnvio )
    {
        this.dataEnvio = dataEnvio;
    }
    
}