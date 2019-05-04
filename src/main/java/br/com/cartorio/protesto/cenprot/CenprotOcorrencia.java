package br.com.cartorio.protesto.cenprot;

public class CenprotOcorrencia 
{
    private String ocorrencia;
    private int quantidade;
    private double valor;

    public CenprotOcorrencia() 
    {
        // faz nada
    }
    
    public CenprotOcorrencia( String ocorrencia )
    {
        this.ocorrencia = ocorrencia;
    }
    
    public CenprotOcorrencia( String ocorrencia , int quantidade , double valor )
    {
        this.ocorrencia = ocorrencia;
        this.quantidade = quantidade;
        this.valor = valor;
    }
    
    public String getOcorrencia()
    {
        return ocorrencia;
    }
    
    public CenprotOcorrencia setOcorrencia( String ocorrencia )
    {
        this.ocorrencia = ocorrencia;
        return this;
    }
    
    public int getQuantidade()
    {
        return quantidade;
    }
    
    public CenprotOcorrencia setQuantidade( int quantidade )
    {
        this.quantidade = quantidade;
        return this;
    }

    public double getValor()
    {
        return valor;
    }

    public CenprotOcorrencia setValor( double valor )
    {
        this.valor = valor;
        return this;
    }
    
}