package br.com.cartorio.protesto.cenprot;

public class CenprotEndereco 
{
    private String endereco;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    
    public CenprotEndereco()
    {
        // faz nada
    }
    
    public CenprotEndereco( String endereco , String complemento , String bairro , String cidade , String uf , String cep )
    {
        this.endereco = endereco;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
    
    public String getComplemento() 
    {
        return complemento;
    }

    public CenprotEndereco setComplemento( String complemento )
    {
        this.complemento = complemento;
        return this;
    }
    
    public String getEndereco()
    {
        return endereco;
    }

    public CenprotEndereco setEndereco( String endereco )
    {
        this.endereco = endereco;
        return this;
    }

    public String getBairro()
    {
        return bairro;
    }

    public CenprotEndereco setBairro( String bairro )
    {
        this.bairro = bairro;
        return this;
    }

    public String getCidade()
    {
        return cidade;
    }

    public CenprotEndereco setCidade( String cidade )
    {
        this.cidade = cidade;
        return this;
    }

    public String getUf()
    {
        return uf;
    }

    public CenprotEndereco setUf( String uf )
    {
        this.uf = uf;
        return this;
    }

    public String getCep()
    {
        return cep;
    }

    public CenprotEndereco setCep( String cep )
    {
        this.cep = cep;
        return this;
    }
    
}