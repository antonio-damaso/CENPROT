package br.com.cartorio.protesto.cenprot;

import java.util.LinkedList;
import java.util.List;

public class CenprotApresentante extends CenprotParte
{
    public static final String PARTICULAR = "TAB";
    
    public static final int TIPO_BANCO      = 0;
    public static final int TIPO_EMPRESA    = 1;
    public static final int TIPO_CONDOMINIO = 2;
    public static final int TIPO_GOVERNO    = 3;
    public static final int TIPO_PARTICULAR = 4;
    
    // --------------
    
    private String codigo;                          // código do CRA
    private int tipo;                               // banco, empresa, condominio, governo ou particular
    private final List<CenprotTitulo> titulos;      // os títulos relacionados com esse apresentante

    public CenprotApresentante()
    {
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
    
    @Override
    public CenprotApresentante setCpfOuCnpj( String cpfOuCnpj )
    {
        super.setCpfOuCnpj( cpfOuCnpj );
        return this;
    }
    
    @Override
    public CenprotApresentante setNome( String nome )
    {
        super.setNome( nome );
        return this;
    }
    
    @Override
    public CenprotApresentante add( CenprotEndereco endereco )
    {
        super.add( endereco );
        return this;
    }
    
    public CenprotApresentante add( CenprotTitulo titulo )
    {
        titulos.add( titulo );
        return this;
    }
    
    public List<CenprotTitulo> getTitulos()
    {
        return titulos;
    }
    
}