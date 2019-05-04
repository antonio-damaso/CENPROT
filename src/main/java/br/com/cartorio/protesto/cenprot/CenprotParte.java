package br.com.cartorio.protesto.cenprot;

import java.util.LinkedList;
import java.util.List;

public class CenprotParte 
{
    private String cpfOuCnpj;
    private String nome;
    private final List<CenprotEndereco> enderecos;

    public CenprotParte() 
    {
        this.enderecos = new LinkedList<CenprotEndereco>();
    }

    public CenprotParte( String tipoDocumento , String cpfOuCnpj , String nome )
    {
        this.enderecos = new LinkedList<CenprotEndereco>();
        this.cpfOuCnpj = cpfOuCnpj;
        this.nome = nome;
    }

    public CenprotParte( String tipoDocumento , String cpfOuCnpj , String nome , CenprotEndereco endereco )
    {
        this.enderecos = new LinkedList<CenprotEndereco>();
        this.enderecos.add( endereco );
        
        this.cpfOuCnpj = cpfOuCnpj;
        this.nome = nome;
    }
    
    public String getNome()
    {
        return nome;
    }

    public CenprotParte setNome( String nome )
    {
        this.nome = nome;
        return this;
    }

    public String getCpfOuCnpj() 
    {
        return cpfOuCnpj;
    }

    public CenprotParte setCpfOuCnpj( String cpfOuCnpj )
    {
        this.cpfOuCnpj = cpfOuCnpj;
        return this;
    }
    
    public CenprotParte add( CenprotEndereco endereco )
    {
        this.enderecos.add( endereco );
        return this;
    }

    public List<CenprotEndereco> getEnderecos() 
    {
        return enderecos;
    }
    
}