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
public class CnaeSecundaria extends AbstractInfoCnpj {

    private final char tipoDoRegistro;
    private final char indicadorFullDiario;
    private final char tipoAtualização;
    private final String cnpj;
    private final String cnaeSecundaria;
    private final String filler;
    private final char fimDeRegistro;
    
    public static CnaeSecundaria parse(final byte[] source) {
        return new CnaeSecundaria(
                new String(copyOfRange(source, 0, 1)).charAt(0),
                new String(copyOfRange(source, 1, 2)).charAt(0),
                new String(copyOfRange(source, 2, 3)).charAt(0),
                new String(copyOfRange(source, 3, 17)).trim(),
                new String(copyOfRange(source, 17, 488)).trim(),
                new String(copyOfRange(source, 488, 1199)).trim(),
                new String(copyOfRange(source, 1199, 1200)).charAt(0)
        );
    }

    public CnaeSecundaria(char tipoDoRegistro, char indicadorFullDiario, char tipoAtualização, String cnpj, String cnaeSecundaria, String filler, char fimDeRegistro) {
        this.tipoDoRegistro = tipoDoRegistro;
        this.indicadorFullDiario = indicadorFullDiario;
        this.tipoAtualização = tipoAtualização;
        this.cnpj = cnpj;
        this.cnaeSecundaria = cnaeSecundaria;
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

    public String getCnaeSecundaria() {
        return cnaeSecundaria;
    }

    public String getFiller() {
        return filler;
    }

    public char getFimDeRegistro() {
        return fimDeRegistro;
    }
}
