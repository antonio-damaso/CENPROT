package br.com.cartorio.protesto.cenprot;

import java.util.LinkedList;
import java.util.List;

public class CenprotApresentante
{
    public static final String PARTICULAR = "TAB";
    
    public static final int TIPO_BANCO      = 0;
    public static final int TIPO_EMPRESA    = 1;
    public static final int TIPO_CONDOMINIO = 2;
    public static final int TIPO_GOVERNO    = 3;
    public static final int TIPO_PARTICULAR = 4;
    
    // --------------
    
    private String codigo;
    private int tipo;
    private String cpfOuCnpj;
    private String nome;
    private final List<CenprotEndereco> enderecos;
    private final List<CenprotTitulo> titulos;

    public CenprotApresentante()
    {
        enderecos = new LinkedList<CenprotEndereco>();
        titulos = new LinkedList<CenprotTitulo>();
    }

    public String getCodigo()
    {
        return codigo;
    }

    public CenprotApresentante setCodigo( String codigo )
    {
        this.codigo = codigo;
        return this;
    }

    public int getTipo()
    {
        return tipo;
    }

    public CenprotApresentante setTipo( int tipo )
    {
        this.tipo = tipo;
        return this;
    }

    public String getCpfOuCnpj()
    {
        return cpfOuCnpj;
    }

    public CenprotApresentante setCpfOuCnpj( String cpfOuCnpj )
    {
        this.cpfOuCnpj = cpfOuCnpj;
        return this;
    }

    public String getNome()
    {
        return nome;
    }

    public CenprotApresentante setNome( String nome )
    {
        this.nome = nome;
        return this;
    }
    
    public CenprotApresentante add( CenprotEndereco endereco )
    {
        enderecos.add( endereco );
        return this;
    }
    
    public CenprotApresentante add( CenprotTitulo titulo )
    {
        titulos.add( titulo );
        return this;
    }
    
    public List<CenprotEndereco> getEnderecos() 
    {
        return enderecos;
    }
    
    public List<CenprotTitulo> getTitulos()
    {
        return titulos;
    }
    
}