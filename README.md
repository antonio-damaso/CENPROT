# CENPROT
Projeto desenvolvido para exportar para o CENPROT (Central Nacional de Protesto). 
Ele é não possui nenhuma dependência.

# Exemplo de código

O exemplo abaixo possui apenas um Cartório, um Apresentante, um Título e um Devedor.

```java
Cenprot cenprot = new Cenprot();
cenprot.setUF( "RJ" );
cenprot.setCodigoIBGE( "3302700" );
cenprot.setCodigoCartorio( "000001" );

// ---------------- apenas para compor o nome do arquivo
cenprot.setTipoTransacao( "P" );  
cenprot.setDataEnvio( data( 18 , 1 , 2019 ) );
cenprot.setRemessaNumero ( 1 );

cenprot.getApresentantes().add( new CenprotApresentante()
    .setNome  ( "BANCO BRADESCO S/A" )
    .setCpfOuCnpj( "60746948242043" )
    .setCodigo( "237" )
    .setTipo  ( CenprotApresentante.TIPO_BANCO )
    .add( new CenprotEndereco()
        .setEndereco( "RUA RIBEIRO DE ALMEIDA,077" )
        .setComplemento( "CASA 01" )
        .setCep( "24900001" )
        .setBairro( "CENTRO" )
        .setCidade( "MARICA" )
        .setUf( "RJ" )
    )
    .add( new CenprotTitulo()
        .setChaveUnica( "33027000134943001190326" )
        .setCedente( new CenprotParte()
            .setNome( "LORENZETTI SA" )
            .setCpfOuCnpj( "61413282000143" ) 
            .add( new CenprotEndereco()
                .setEndereco( "RUA DO NORTE 153" )
                .setComplemento( "SALA 01" )
                .setCep( "21110000" )
                .setBairro( "CENTRO" )
                .setCidade( "RIO DE JANEIRO" )
                .setUf( "RJ" )
            )
        )
        .setSacador( new CenprotParte()
            .setNome( "LORENZETTI SA" )
            .setCpfOuCnpj( "61413282000143" ) 
            .add( new CenprotEndereco()
                .setEndereco( "AV PRESIDENTE WILSON 1230" )
                .setComplemento( "SALA 01" )
                .setCep( "03107901" )
                .setBairro( "CENTRO" )
                .setCidade( "SAO PAULO" )
                .setUf( "SP" )
            )
        )
        .add( new CenprotDevedor()
            .setNome( "SA REGO E TEIXEIRA LTDA" ) 
            .setCpfOuCnpj( "29789518000138" ) 
            .setIntimado( true )
            .setEdital( true )
            .setEditalMotivo( "AR NAO RETORNOU" )
            .addEndereco( new CenprotEndereco()
                .setEndereco( "RUA RIBEIRO DE ALMEIDA,077" )
                .setComplemento( "SALA 01" )
                .setBairro( "CENTRO" )
                .setCidade( "MARICA" )
                .setUf( "RJ" )
                .setCep( "24900001" )
            ) 
        )

        .setNossoNumero( "1426919/01" )
        .setEspecie( "DMI" )
        .setNumeroTitulo( "1426919/01" )
        .setDataEmissao( data( 30 , 11 , 2018 ) )
        .setDataVencimento( "2019-01-18" )
        .setTipoMoeda( "1" )
        .setValorTitulo( 1764.55 )
        .setValorProtestado( 1764.55 )
        .setPracaProtesto( "MARICA" )
        .setEndosso( "G" )
        .setTipoEndosso( "M" )
        .setAceite( "N" )

        .setDataApresentacao( data( 24 , 1 , 2019 ) )
        .setProtocolo( "134943" )
        .setDataProtocolo( data( 24 , 1 , 2019 ) )
        .setOcorrencia( 2 )
        .setDataOcorrencia( data( 7 , 2 , 2019 ) )
        .setContratoOperacao( "12345" )
        .setContratoIdentificador( "237"   )
        .setContratoParcela ( "01" )
        .setContratoTipo( "C" )
        .setTipoProtesto( "C" )
        .setMotivoProtesto( 1 )
        .setValorOutrasDespesas( 10.00 )
        .setTipoLivro( "A" )
        .setNumeroLivro( 1 )
        .setNumeroFolha( 10 )
        .setAcervoHerdado( true )
        .setAcervoCartorio( 2 )
        .setDataAverbacao( data( 25 , 1, 2019 ) )
        .setFaixaValorProtestado( "C" )
        .add( new CenprotCancelamentoOnline( "04" , 1 ) )
            .setAnuencia( new CenprotAnuencia()
            .setFlag( true )
            .setOrigem( 1 )
            .setData( data( 28, 2 , 2019 ) )
            .setApresentantePermitido( true ) 
            .setSacadorPermitido( true  )
            .setCedentePermitido( false ) 
        )
        .setMicroempresa( false )
        .setInstrumentoEletronico( "" )
) );
```


O exemplo abaixo mostra como gerar a chave única:

```java
int ibge         = 3550308;
int apontamentoN = 127;
int cartorioN    = 1;

Calendar c = Calendar.getInstance();
c.set( Calendar.YEAR  , 2014 );
c.set( Calendar.MONTH , 11   );         // dezembro
c.set( Calendar.DAY_OF_MONTH , 29 );

String resultado = ChaveUnicaUtils.gerar( ibge , apontamentoN , cartorioN , c.getTime() );
```

# Mais informações
Todas as informações sobre o CENPROT podem ser encontradas em: https://dev.azure.com/IEPTB-BR/Public