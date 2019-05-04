package br.com.cartorio.protesto.cenprot;

import java.util.LinkedList;
import java.util.List;

public class CenprotDevedor 
{
    private String cpfOuCnpj;
    private String nome;
    private boolean intimado;
    private boolean edital;
    private String editalMotivo;
    private final List<CenprotEndereco> enderecos;

    public CenprotDevedor()
    {
        enderecos = new LinkedList<CenprotEndereco>();
    }

    public String getCpfOuCnpj()
    {
        return cpfOuCnpj;
    }

    public CenprotDevedor setCpfOuCnpj( String cpfOuCnpj )
    {
        this.cpfOuCnpj = cpfOuCnpj;
        return this;
    }

    public boolean isIntimado()
    {
        return intimado;
    }

    public CenprotDevedor setIntimado( boolean intimado )
    {
        this.intimado = intimado;
        return this;
    }

    public boolean isEdital()
    {
        return edital;
    }

    public CenprotDevedor setEdital( boolean edital )
    {
        this.edital = edital;
        return this;
    }

    public String getEditalMotivo()
    {
        return editalMotivo;
    }

    public CenprotDevedor setEditalMotivo( String editalMotivo )
    {
        this.editalMotivo = editalMotivo;
        return this;
    }
    
    public String getNome()
    {
        return nome;
    }

    public CenprotDevedor setNome( String nome )
    {
        this.nome = nome;
        return this;
    }

    public List<CenprotEndereco> getEnderecos()
    {
        return enderecos;
    }

    public CenprotDevedor addEndereco( CenprotEndereco endereco )
    {
        enderecos.add( endereco );
        return this;
    }
    
}