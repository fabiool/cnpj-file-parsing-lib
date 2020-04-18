/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib.model;

import static eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj.parseDate;
import static eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj.parseInt;
import java.text.ParseException;

import java.util.Date;

import static java.util.Arrays.copyOfRange;

/**
 * Header 1200 bytes Contém os itens do registro Header
 *
 * @author Fabio
 */
public class Header extends AbstractInfoCnpj {

    private final char tipoDoRegistro;
    private final String filler01;
    private final String nomeDoArquivo;
    private final Date dataDeGravacao;
    private final int numeroDaRemessa;
    private final String filler02;
    private final char fimDeRegistro;

    public static Header parse(final byte[] source) throws ParseException {
        return new Header(
                new String(copyOfRange(source, 0, 1)).charAt(0), // tipoDoRegistro
                new String(copyOfRange(source, 1, 17)), // filler01, 
                new String(copyOfRange(source, 17, 28)), // nomeDoArquivo, 
                parseDate(copyOfRange(source, 28, 36)), // dataDeGravacao, 
                parseInt(copyOfRange(source, 36, 44)), // numeroDaRemessa, 
                new String(copyOfRange(source, 44, 1199)), // filler02, 
                new String(copyOfRange(source, 1199, 1200)).charAt(0)); // fimDeRegistro);
    }

    private Header(char tipoDoRegistro, String filler01, String nomeDoArquivo, Date dataDeGravacao, int numeroDaRemessa, String filler02, char fimDeRegistro) {
        this.tipoDoRegistro = tipoDoRegistro;
        this.filler01 = filler01;
        this.nomeDoArquivo = nomeDoArquivo;
        this.dataDeGravacao = dataDeGravacao;
        this.numeroDaRemessa = numeroDaRemessa;
        this.filler02 = filler02;
        this.fimDeRegistro = fimDeRegistro;
    }

    public char getTipoDoRegistro() {
        return tipoDoRegistro;
    }

    public String getFiller01() {
        return filler01;
    }

    public String getNomeDoArquivo() {
        return nomeDoArquivo;
    }

    public Date getDataDeGravacao() {
        return dataDeGravacao;
    }

    public int getNumeroDaRemessa() {
        return numeroDaRemessa;
    }

    public String getFiller02() {
        return filler02;
    }

    public char getFimDeRegistro() {
        return fimDeRegistro;
    }
}
