/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib.model;

import static eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj.parseDate;
import java.text.ParseException;

import java.util.Date;

import static java.util.Arrays.copyOfRange;

/**
 *
 * @author Fabio
 */
public class DadosCadastrais extends AbstractInfoCnpj {

    private final char tipoDoRegistro;
    private final char indicadorFullDiario;
    private final char tipoAtualização;
    private final String cnpj;
    private final char indetificadorMatrizFilial;
    private final String razaoSocial;
    private final String nomeFantasia;
    private final String situacaoCadastral;
    private final Date dataSituacaoCadastral;
    private final String motivoSituacaoCadastral;
    private final String nomeCidadeExterior;
    private final String codigoPais;
    private final String nomePais;
    private final String codigoNaturezaJuridica;
    private final Date dataInicioAtividade;
    private final String cnaeFiscal;
    private final String descricaoTipoLogradouro;
    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cep;
    private final String uf;
    private final String codigoMunicipio;
    private final String municipio;
    private final String dddTelefone1;
    private final String ddd1;
    private final String telefone1;
    private final String dddTelefone2;
    private final String ddd2;
    private final String telefone2;
    private final String dddFax;
    private final String numeroDddFax;
    private final String numeroFax;
    private final String correioEletronico;
    private final String qualificacaoResponsavel;
    private final String capitalSocial;
    private final String porteEmpresa;
    private final char opcaoSimples;
    private final Date dataOpcaoSimples;
    private final Date dataExclusaoSimples;
    private final char opcaoMei;
    private final String situaçãoEspecial;
    private final Date dataSituaçãoEspecial;
    private final String filler;
    private final char fimDeRegistro;

    public static DadosCadastrais parse(final byte[] source) throws ParseException {
        return new DadosCadastrais(
                new String(copyOfRange(source, 0, 1)).charAt(0),
                new String(copyOfRange(source, 1, 2)).charAt(0),
                new String(copyOfRange(source, 2, 3)).charAt(0),
                new String(copyOfRange(source, 3, 17)),
                new String(copyOfRange(source, 17, 18)).charAt(0),
                new String(copyOfRange(source, 18, 168)),
                new String(copyOfRange(source, 168, 223)),
                new String(copyOfRange(source, 223, 225)),
                parseDate(copyOfRange(source, 225, 233)),
                new String(copyOfRange(source, 233, 235)),
                new String(copyOfRange(source, 235, 290)),
                new String(copyOfRange(source, 290, 293)),
                new String(copyOfRange(source, 293, 363)),
                new String(copyOfRange(source, 363, 367)),
                parseDate(copyOfRange(source, 367, 375)),
                new String(copyOfRange(source, 375, 382)),
                new String(copyOfRange(source, 382, 402)),
                new String(copyOfRange(source, 402, 462)),
                new String(copyOfRange(source, 462, 468)),
                new String(copyOfRange(source, 468, 624)),
                new String(copyOfRange(source, 624, 674)),
                new String(copyOfRange(source, 674, 682)),
                new String(copyOfRange(source, 682, 684)),
                new String(copyOfRange(source, 684, 688)),
                new String(copyOfRange(source, 688, 738)),
                new String(copyOfRange(source, 738, 750)),
                new String(copyOfRange(source, 750, 754)),
                new String(copyOfRange(source, 754, 762)),
                new String(copyOfRange(source, 762, 774)),
                new String(copyOfRange(source, 774, 778)),
                new String(copyOfRange(source, 778, 786)),
                new String(copyOfRange(source, 786, 798)),
                new String(copyOfRange(source, 798, 802)),
                new String(copyOfRange(source, 802, 810)),
                new String(copyOfRange(source, 810, 925)),
                new String(copyOfRange(source, 925, 927)),
                new String(copyOfRange(source, 927, 941)),
                new String(copyOfRange(source, 941, 943)),
                new String(copyOfRange(source, 943, 944)).charAt(0),
                parseDate(copyOfRange(source, 944, 952)),
                parseDate(copyOfRange(source, 952, 960)),
                new String(copyOfRange(source, 960, 961)).charAt(0),
                new String(copyOfRange(source, 961, 984)),
                parseDate(copyOfRange(source, 984, 992)),
                new String(copyOfRange(source, 992, 1199)),
                new String(copyOfRange(source, 1199, 1200)).charAt(0)
        );
    }

    private DadosCadastrais(char tipoDoRegistro, char indicadorFullDiario, char tipoAtualização, String cnpj, char indetificadorMatrizFilial, String razaoSocial, String nomeFantasia, String situacaoCadastral, Date dataSituacaoCadastral, String motivoSituacaoCadastral, String nomeCidadeExterior, String codigoPais, String nomePais, String codigoNaturezaJuridica, Date dataInicioAtividade, String cnaeFiscal, String descricaoTipoLogradouro, String logradouro, String numero, String complemento, String bairro, String cep, String uf, String codigoMunicipio, String municipio, String dddTelefone1, String ddd1, String telefone1, String dddTelefone2, String ddd2, String telefone2, String dddFax, String numeroDddFax, String numeroFax, String correioEletronico, String qualificacaoResponsavel, String capitalSocial, String porteEmpresa, char opcaoSimples, Date dataOpcaoSimples, Date dataExclusaoSimples, char opcaoMei, String situaçãoEspecial, Date dataSituaçãoEspecial, String filler, char fimDeRegistro) {
        this.tipoDoRegistro = tipoDoRegistro;
        this.indicadorFullDiario = indicadorFullDiario;
        this.tipoAtualização = tipoAtualização;
        this.cnpj = cnpj;
        this.indetificadorMatrizFilial = indetificadorMatrizFilial;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.situacaoCadastral = situacaoCadastral;
        this.dataSituacaoCadastral = dataSituacaoCadastral;
        this.motivoSituacaoCadastral = motivoSituacaoCadastral;
        this.nomeCidadeExterior = nomeCidadeExterior;
        this.codigoPais = codigoPais;
        this.nomePais = nomePais;
        this.codigoNaturezaJuridica = codigoNaturezaJuridica;
        this.dataInicioAtividade = dataInicioAtividade;
        this.cnaeFiscal = cnaeFiscal;
        this.descricaoTipoLogradouro = descricaoTipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.uf = uf;
        this.codigoMunicipio = codigoMunicipio;
        this.municipio = municipio;
        this.dddTelefone1 = dddTelefone1;
        this.ddd1 = ddd1;
        this.telefone1 = telefone1;
        this.dddTelefone2 = dddTelefone2;
        this.ddd2 = ddd2;
        this.telefone2 = telefone2;
        this.dddFax = dddFax;
        this.numeroDddFax = numeroDddFax;
        this.numeroFax = numeroFax;
        this.correioEletronico = correioEletronico;
        this.qualificacaoResponsavel = qualificacaoResponsavel;
        this.capitalSocial = capitalSocial;
        this.porteEmpresa = porteEmpresa;
        this.opcaoSimples = opcaoSimples;
        this.dataOpcaoSimples = dataOpcaoSimples;
        this.dataExclusaoSimples = dataExclusaoSimples;
        this.opcaoMei = opcaoMei;
        this.situaçãoEspecial = situaçãoEspecial;
        this.dataSituaçãoEspecial = dataSituaçãoEspecial;
        this.filler = filler;
        this.fimDeRegistro = fimDeRegistro;
    }

    public char getTipoDoRegistro() {
        return tipoDoRegistro;
    }

    public char getIndicadorFullDiario() {
        return indicadorFullDiario;
    }

    public char getTipoAtualização() {
        return tipoAtualização;
    }

    public String getCnpj() {
        return cnpj;
    }

    public char getIndetificadorMatrizFilial() {
        return indetificadorMatrizFilial;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getSituacaoCadastral() {
        return situacaoCadastral;
    }

    public Date getDataSituacaoCadastral() {
        return dataSituacaoCadastral;
    }

    public String getMotivoSituacaoCadastral() {
        return motivoSituacaoCadastral;
    }

    public String getNomeCidadeExterior() {
        return nomeCidadeExterior;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public String getCodigoNaturezaJuridica() {
        return codigoNaturezaJuridica;
    }

    public Date getDataInicioAtividade() {
        return dataInicioAtividade;
    }

    public String getCnaeFiscal() {
        return cnaeFiscal;
    }

    public String getDescricaoTipoLogradouro() {
        return descricaoTipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getUf() {
        return uf;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getDddTelefone1() {
        return dddTelefone1;
    }

    public String getDdd1() {
        return ddd1;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public String getDddTelefone2() {
        return dddTelefone2;
    }

    public String getDdd2() {
        return ddd2;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public String getDddFax() {
        return dddFax;
    }

    public String getNumeroDddFax() {
        return numeroDddFax;
    }

    public String getNumeroFax() {
        return numeroFax;
    }

    public String getCorreioEletronico() {
        return correioEletronico;
    }

    public String getQualificacaoResponsavel() {
        return qualificacaoResponsavel;
    }

    public String getCapitalSocial() {
        return capitalSocial;
    }

    public String getPorteEmpresa() {
        return porteEmpresa;
    }

    public char getOpcaoSimples() {
        return opcaoSimples;
    }

    public Date getDataOpcaoSimples() {
        return dataOpcaoSimples;
    }

    public Date getDataExclusaoSimples() {
        return dataExclusaoSimples;
    }

    public char getOpcaoMei() {
        return opcaoMei;
    }

    public String getSituaçãoEspecial() {
        return situaçãoEspecial;
    }

    public Date getDataSituaçãoEspecial() {
        return dataSituaçãoEspecial;
    }

    public String getFiller() {
        return filler;
    }

    public char getFimDeRegistro() {
        return fimDeRegistro;
    }
}
