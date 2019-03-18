/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib.model;



import static java.util.Arrays.copyOfRange;
/**
 *
 * @author Fabio
 */
public class Socio implements InfoCnpj {

    private final char tipoDoRegistro;
    private final char indicadorFullDiario;
    private final char tipoAtualização;
    private final String cnpj;
    private final char identificadorDeSocio;
    private final String nomeSocio;
    private final String cnpjCpf;
    private final String qualificacaoSocio;
    private final String percentualCapitalSocial;
    private final String dataEntradaSociedade;
    private final String codigoPais;
    private final String nomePaisSocio;
    private final String cpfRepresentanteLegal;
    private final String nomeRepresentante;
    private final String qualificacaoRepresentante;
    private final String filler;
    private final char fimDeRegistro;

    public static Socio parse(final byte[] source) {
        return new Socio(
            new String(copyOfRange(source, 0, 1)).charAt(0),
            new String(copyOfRange(source, 1, 2)).charAt(0),
            new String(copyOfRange(source, 2, 3)).charAt(0),
            new String(copyOfRange(source, 3, 17)),
            new String(copyOfRange(source, 17, 18)).charAt(0),
            new String(copyOfRange(source, 18, 168)),
            new String(copyOfRange(source, 168, 182)),
            new String(copyOfRange(source, 182, 184)),
            new String(copyOfRange(source, 184, 189)),
            new String(copyOfRange(source, 189, 197)),
            new String(copyOfRange(source, 197, 200)),
            new String(copyOfRange(source, 200, 270)),
            new String(copyOfRange(source, 270, 281)),
            new String(copyOfRange(source, 281, 341)),
            new String(copyOfRange(source, 341, 343)),
            new String(copyOfRange(source, 343, 1198)),
            new String(copyOfRange(source, 1198, 1199)).charAt(0)
        );
    }

    public Socio(char tipoDoRegistro, char indicadorFullDiario, char tipoAtualização, String cnpj, char identificadorDeSocio, String nomeSocio, String cnpjCpf, String qualificacaoSocio, String percentualCapitalSocial, String dataEntradaSociedade, String codigoPais, String nomePaisSocio, String cpfRepresentanteLegal, String nomeRepresentante, String qualificacaoRepresentante, String filler, char fimDeRegistro) {
        this.tipoDoRegistro = tipoDoRegistro;
        this.indicadorFullDiario = indicadorFullDiario;
        this.tipoAtualização = tipoAtualização;
        this.cnpj = cnpj;
        this.identificadorDeSocio = identificadorDeSocio;
        this.nomeSocio = nomeSocio;
        this.cnpjCpf = cnpjCpf;
        this.qualificacaoSocio = qualificacaoSocio;
        this.percentualCapitalSocial = percentualCapitalSocial;
        this.dataEntradaSociedade = dataEntradaSociedade;
        this.codigoPais = codigoPais;
        this.nomePaisSocio = nomePaisSocio;
        this.cpfRepresentanteLegal = cpfRepresentanteLegal;
        this.nomeRepresentante = nomeRepresentante;
        this.qualificacaoRepresentante = qualificacaoRepresentante;
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

    public char getIdentificadorDeSocio() {
        return identificadorDeSocio;
    }

    public String getNomeSocio() {
        return nomeSocio;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public String getQualificacaoSocio() {
        return qualificacaoSocio;
    }

    public String getPercentualCapitalSocial() {
        return percentualCapitalSocial;
    }

    public String getDataEntradaSociedade() {
        return dataEntradaSociedade;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public String getNomePaisSocio() {
        return nomePaisSocio;
    }

    public String getCpfRepresentanteLegal() {
        return cpfRepresentanteLegal;
    }

    public String getNomeRepresentante() {
        return nomeRepresentante;
    }

    public String getQualificacaoRepresentante() {
        return qualificacaoRepresentante;
    }

    public String getFiller() {
        return filler;
    }

    public char getFimDeRegistro() {
        return fimDeRegistro;
    }
}
