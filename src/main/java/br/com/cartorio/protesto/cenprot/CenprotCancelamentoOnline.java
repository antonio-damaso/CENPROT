package br.com.cartorio.protesto.cenprot;

public class CenprotCancelamentoOnline 
{
    private String codigoCusta;
    private int quantidadeCustaEfetuada;

    public CenprotCancelamentoOnline()
    {
        // faz nada
    }

    public CenprotCancelamentoOnline( String codigoCusta , int quantidade )
    {
        this.codigoCusta = codigoCusta;
        this.quantidadeCustaEfetuada = quantidade;
    }
    
    public String getCodigoCusta()
    {
        return codigoCusta;
    }

    public void setCodigoCusta( String codigoCusta )
    {
        this.codigoCusta = codigoCusta;
    }

    public int getQuantidade()
    {
        return quantidadeCustaEfetuada;
    }

    public void setQuantidade( int quantidade )
    {
        this.quantidadeCustaEfetuada = quantidade;
    }
    
}