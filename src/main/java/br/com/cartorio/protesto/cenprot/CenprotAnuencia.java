package br.com.cartorio.protesto.cenprot;

import java.util.Date;

public class CenprotAnuencia 
{
    public static final String CREDOR_APRESENTANTE = "A";
    public static final String CREDOR_CEDENTE = "C";
    public static final String CREDOR_SACADOR = "S";
    
    private boolean flag;
    private int origem;
    private Date data;
    private boolean apresentantePermitito;
    private boolean sacadorPermitida;
    private boolean cedentePermitido;
    //private String credorAtual;     // "A" para apresentante, "C" para Cedente, "S" para Sacador

    public CenprotAnuencia() 
    {
        apresentantePermitito = true;
        sacadorPermitida = true;
        cedentePermitido = true;
    }
    
    public CenprotAnuencia( boolean flag , int origem , Date data )
    {
        this.flag = flag;
        this.origem = origem;
        this.data = data;
        this.apresentantePermitito = true;
        this.sacadorPermitida = true;
        this.cedentePermitido = true;
    }

    public CenprotAnuencia( boolean flag , int origem , Date data , boolean apresentantePermitido , boolean sacadorPermitido , boolean cedentePermitido )
    {
        this.flag = flag;
        this.origem = origem;
        this.data = data;
        this.apresentantePermitito = apresentantePermitido;
        this.sacadorPermitida = sacadorPermitido;
        this.cedentePermitido = cedentePermitido;
    }
    
    public boolean isFlag()
    {
        return flag;
    }

    public CenprotAnuencia setFlag( boolean flag )       
    {
        this.flag = flag;
        return this;
    }

    public int getOrigem()
    {
        return origem;
    }

    public CenprotAnuencia setOrigem( int origem )
    {
        this.origem = origem;
        return this;
    }

    public Date getData()
    {
        return data;
    }

    public CenprotAnuencia setData( Date data )
    {
        this.data = data;
        return this;
    }

    public boolean isApresentantePermitido()
    {
        return apresentantePermitito;
    }

    public CenprotAnuencia setApresentantePermitido( boolean apresentantePermitido )
    {
        this.apresentantePermitito = apresentantePermitido;
        return this;
    }

    public boolean isSacadorPermitido()
    {
        return sacadorPermitida;
    }

    public CenprotAnuencia setSacadorPermitido( boolean sacadorPermitido )
    {
        this.sacadorPermitida = sacadorPermitido;
        return this;
    }

    public boolean isCedentePermitido()
    {
        return cedentePermitido;
    }

    public CenprotAnuencia setCedentePermitido( boolean cedentePermitido )
    {
        this.cedentePermitido = cedentePermitido;
        return this;
    }
    
}