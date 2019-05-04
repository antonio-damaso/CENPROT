package br.com.cartorio.protesto.cenprot;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CenprotTitulo 
{
    public static final String VENCIMENTO_CONTRA_PROTESTO = "8888-88-88";
    public static final String VENCIMENTO_01_DIA  = "9999-00-01";
    public static final String VENCIMENTO_30_DIAS = "9999-00-30";
    public static final String VENCIMENTO_A_VISTA = "9999-99-99";
    
    public static final String MOEDA_REAL = "001";
    
    private String chaveUnica;
    private String agencia;         // Somente para Bancos
    private String tipoDocumento;
    private String documento;
    private CenprotParte cedente;
    private CenprotParte sacador;
    private String nossoNumero;
    private String especie;
    private String numeroTitulo;
    private Date dataEmissao;
    private String dataVencimento;
    private String tipoMoeda;
    private double valorTitulo;
    private double valorProtestado;
    private String pracaProtesto;
    private String endosso;         // G – Cedente igual ao Sacador H – Cedente diferente do Sacador
    private String tipoEndosso;     // M – Mandato T – Translativo ou Branco.
    private String aceite;          // A – aceito N – não aceito pelo devedor ou "branco"
    private final List<CenprotDevedor> devedores;
    
    private Date dataApresentacao;
    private String protocolo;
    private Date dataProtocolo;
    private int ocorrencia;
    private Date dataOcorrencia;
    private final List<Integer> irregulares;
    
    private String contratoOperacao;
    private String contratoIdentificador;
    private String contratoParcela;
    private String contratoTipo;
    
    private String tipoProtesto; // Tipo de protesto: C - Comum, F - Falimentar
    private int motivoProtesto;  // Motivo do protesto: 01 - Falta de Pagamento, 02 - Falta de Aceite, 03 - falta de devolução
    
    private String tipoLivro;
    private int numeroLivro;
    private int numeroFolha;
    
    private double valorOutrasDespesas;
    
    private boolean acervoHerdado;
    private int acervoCartorio;
    
    private Date dataAverbacao;
    private String dadosCancelamento;
    private String faixaValorProtestado;
    
    private final List<CenprotCancelamentoOnline> cancelamentos;
    private CenprotAnuencia anuencia;
    
    private boolean microempresa;
    private String instrumentoEletronico;

    public CenprotTitulo() 
    {
        this.devedores     = new LinkedList<CenprotDevedor>();
        this.cancelamentos = new LinkedList<CenprotCancelamentoOnline>();
        this.irregulares   = new LinkedList<Integer>();
    }
    
    public String getChaveUnica()
    {
        return chaveUnica;
    }

    public CenprotTitulo setChaveUnica( String chaveUnica )
    {
        this.chaveUnica = chaveUnica;
        return this;
    }

    public String getAgencia()
    {
        return agencia;
    }

    public CenprotTitulo setAgencia( String agencia )
    {
        this.agencia = agencia;
        return this;
    }

    public String getTipoDocumento()
    {
        return tipoDocumento;
    }

    public CenprotTitulo setTipoDocumento( String tipoDocumento )
    {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public String getDocumento()
    {
        return documento;
    }

    public CenprotTitulo setDocumento( String documento )
    {
        this.documento = documento;
        return this;
    }
    
    public CenprotParte getCedente()
    {
        return cedente;
    }

    public CenprotTitulo setCedente( CenprotParte cedente )
    {
        this.cedente = cedente;
        return this;
    }

    public CenprotParte getSacador()
    {
        return sacador;
    }

    public CenprotTitulo setSacador( CenprotParte sacador )
    {
        this.sacador = sacador;
        return this;
    }

    public String getNossoNumero()
    {
        return nossoNumero;
    }

    public CenprotTitulo setNossoNumero( String nossoNumero )
    {
        this.nossoNumero = nossoNumero;
        return this;
    }

    public String getEspecie()
    {
        return especie;
    }

    public CenprotTitulo setEspecie( String especie )
    {
        this.especie = especie;
        return this;
    }

    public String getNumeroTitulo()
    {
        return numeroTitulo;
    }

    public CenprotTitulo setNumeroTitulo( String numeroTitulo )
    {
        this.numeroTitulo = numeroTitulo;
        return this;
    }

    public Date getDataEmissao()
    {
        return dataEmissao;
    }

    public CenprotTitulo setDataEmissao( Date dataEmissao )
    {
        this.dataEmissao = dataEmissao;
        return this;
    }

    public String getDataVencimento()
    {
        return dataVencimento;
    }

    public CenprotTitulo setDataVencimento( String dataVencimento )
    {
        this.dataVencimento = dataVencimento;
        return this;
    }

    public String getTipoMoeda()
    {
        return tipoMoeda;
    }

    public CenprotTitulo setTipoMoeda( String tipoMoeda )
    {
        this.tipoMoeda = tipoMoeda;
        return this;
    }

    public double getValorTitulo()
    {
        return valorTitulo;
    }

    public CenprotTitulo setValorTitulo( double valorTitulo )
    {
        this.valorTitulo = valorTitulo;
        return this;
    }

    public double getValorProtestado()
    {
        return valorProtestado;
    }

    public CenprotTitulo setValorProtestado( double valorProtestado )
    {
        this.valorProtestado = valorProtestado;
        return this;
    }

    public String getPracaProtesto()
    {
        return pracaProtesto;
    }

    public CenprotTitulo setPracaProtesto( String pracaProtesto )
    {
        this.pracaProtesto = pracaProtesto;
        return this;
    }

    public String getEndosso()
    {
        return endosso;
    }

    public CenprotTitulo setEndosso( String endosso )
    {
        this.endosso = endosso;
        return this;
    }

    public String getTipoEndosso()
    {
        return tipoEndosso;
    }

    public CenprotTitulo setTipoEndosso( String tipoEndosso )
    {
        this.tipoEndosso = tipoEndosso;
        return this;
    }

    public String getAceite()
    {
        return aceite;
    }

    public CenprotTitulo setAceite( String aceite )
    {
        this.aceite = aceite;
        return this;
    }

    public Date getDataApresentacao()
    {
        return dataApresentacao;
    }

    public CenprotTitulo setDataApresentacao( Date dataApresentacao )
    {
        this.dataApresentacao = dataApresentacao;
        return this;
    }

    public String getProtocolo()
    {
        return protocolo;
    }

    public CenprotTitulo setProtocolo( String protocolo )
    {
        this.protocolo = protocolo;
        return this;
    }

    public Date getDataProtocolo() 
    {
        return dataProtocolo;
    }

    public CenprotTitulo setDataProtocolo( Date dataProtocolo )
    {
        this.dataProtocolo = dataProtocolo;
        return this;
    }

    public int getOcorrencia()
    {
        return ocorrencia;
    }

    public CenprotTitulo setOcorrencia( int ocorrencia )
    {
        this.ocorrencia = ocorrencia;
        return this;
    }

    public Date getDataOcorrencia()
    {
        return dataOcorrencia;
    }

    public CenprotTitulo setDataOcorrencia( Date dataOcorrencia )
    {
        this.dataOcorrencia = dataOcorrencia;
        return this;
    }
    
    public String getContratoOperacao()
    {
        return contratoOperacao;
    }

    public CenprotTitulo setContratoOperacao( String contratoOperacao )
    {
        this.contratoOperacao = contratoOperacao;
        return this;
    }

    public String getContratoIdentificador()
    {
        return contratoIdentificador;
    }

    public CenprotTitulo setContratoIdentificador( String contratoIdentificador ) 
    {
        this.contratoIdentificador = contratoIdentificador;
        return this;
    }

    public String getContratoParcela()
    {
        return contratoParcela;
    }

    public CenprotTitulo setContratoParcela( String contratoParcela )
    {
        this.contratoParcela = contratoParcela;
        return this;
    }

    public String getContratoTipo()
    {
        return contratoTipo;
    }

    public CenprotTitulo setContratoTipo( String contratoLetra )
    {
        this.contratoTipo = contratoLetra;
        return this;
    }

    public String getTipoProtesto()
    {
        return tipoProtesto;
    }

    public CenprotTitulo setTipoProtesto( String tipoProtesto )
    {
        this.tipoProtesto = tipoProtesto;
        return this;
    }

    public int getMotivoProtesto()
    {
        return motivoProtesto;
    }

    public CenprotTitulo setMotivoProtesto( int motivoProtesto )
    {
        this.motivoProtesto = motivoProtesto;
        return this;
    }

    public String getTipoLivro()
    {
        return tipoLivro;
    }

    public CenprotTitulo setTipoLivro( String tipoLivro )
    {
        this.tipoLivro = tipoLivro;
        return this;
    }

    public int getNumeroLivro()
    {
        return numeroLivro;
    }

    public CenprotTitulo setNumeroLivro( int numeroLivro )
    {
        this.numeroLivro = numeroLivro;
        return this;
    }

    public int getNumeroFolha()
    {
        return numeroFolha;
    }

    public CenprotTitulo setNumeroFolha( int numeroFolha )
    {
        this.numeroFolha = numeroFolha;
        return this;
    }

    public double getValorOutrasDespesas()
    {
        return valorOutrasDespesas;
    }

    public CenprotTitulo setValorOutrasDespesas( double valorOutrasDespesas )
    {
        this.valorOutrasDespesas = valorOutrasDespesas;
        return this;
    }

    public boolean isAcervoHerdado()
    {
        return acervoHerdado;
    }

    public CenprotTitulo setAcervoHerdado( boolean acervoHerdado )
    {
        this.acervoHerdado = acervoHerdado;
        return this;
    }

    public int getAcervoCartorio()
    {
        return acervoCartorio;
    }

    public CenprotTitulo setAcervoCartorio( int acervoCartorio )
    {
        this.acervoCartorio = acervoCartorio;
        return this;
    }

    public Date getDataAverbacao()
    {
        return dataAverbacao;
    }

    public CenprotTitulo setDataAverbacao( Date dataAverbacao )
    {
        this.dataAverbacao = dataAverbacao;
        return this;
    }

    public String getDadosCancelamento() 
    {
        return dadosCancelamento;
    }

    public CenprotTitulo setDadosCancelamento( String dadosCancelamento )
    {
        this.dadosCancelamento = dadosCancelamento;
        return this;
    }

    public String getFaixaValorProtestado()
    {
        return faixaValorProtestado;
    }

    public CenprotTitulo setFaixaValorProtestado( String faixaValorProtestado )
    {
        this.faixaValorProtestado = faixaValorProtestado;
        return this;
    }

    public boolean isMicroempresa()
    {
        return microempresa;
    }

    public CenprotTitulo setMicroempresa( boolean microempresa )
    {
        this.microempresa = microempresa;
        return this;
    }

    public String getInstrumentoEletronico()
    {
        return instrumentoEletronico;
    }

    public CenprotTitulo setInstrumentoEletronico( String instrumentoEletronico )
    {
        this.instrumentoEletronico = instrumentoEletronico;
        return this;
    }
    
    public CenprotAnuencia getAnuencia()
    {
        return anuencia;
    }

    public CenprotTitulo setAnuencia( CenprotAnuencia anuencia )
    {
        this.anuencia = anuencia;
        return this;
    }
    
    public List<CenprotDevedor> getDevedores()
    {
        return devedores;
    }

    public List<CenprotCancelamentoOnline> getCancelamentos()
    {
        return cancelamentos;
    }
    
    public List<Integer> getIrregulares()
    {
        return irregulares;
    }
    
    public CenprotTitulo add( int codigoIrregular )
    {
        this.irregulares.add( codigoIrregular );
        return this;
    }
    
    public CenprotTitulo add( CenprotDevedor devedor )
    {
        this.devedores.add( devedor );
        return this;
    }
    
    public CenprotTitulo add( CenprotCancelamentoOnline cancelamentoOnline )
    {
        this.cancelamentos.add( cancelamentoOnline );
        return this;
    }
    
}