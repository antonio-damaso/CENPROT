package br.com.cartorio.protesto.cenprot;

public class CenprotDevedor extends CenprotParte
{
    private boolean intimado;
    private boolean edital;
    private String editalMotivo;
    
    public CenprotDevedor()
    {
        // faz nada
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
    
    @Override
    public CenprotDevedor setCpfOuCnpj( String cpfOuCnpj )
    {
        super.setCpfOuCnpj( cpfOuCnpj );
        return this;
    }
    
    @Override
    public CenprotDevedor setNome( String nome )
    {
        super.setNome( nome );
        return this;
    }
    
    @Override
    public CenprotDevedor add( CenprotEndereco endereco )
    {
        super.add( endereco );
        return this;
    }
    
}