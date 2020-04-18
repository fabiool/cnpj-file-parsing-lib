/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib.model;

import static eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj.parseInt;
import static eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj.parseLong;

import static java.util.Arrays.copyOfRange;

/**
 *
 * @author Fabio
 */
public class Trailler extends AbstractInfoCnpj {
    
    private final char tipoDoRegistro;
    private final String filler01;
    private final int totalRegistrosT1;
    private final int totalRegistrosT2;
    private final int totalRegistrosT3;
    private final long totalRegistros;
    private final String filler02;
    private final char fimDeRegistro;
    
    public static Trailler parse(final byte[] source) {
        
        return new Trailler(
                new String(copyOfRange(source, 0, 1)).charAt(0),
                new String(copyOfRange(source, 1, 17)).trim(),
                parseInt(copyOfRange(source, 17, 26)),
                parseInt(copyOfRange(source, 26, 35)),
                parseInt(copyOfRange(source, 35, 44)),
                parseLong(copyOfRange(source, 44, 55)),
                new String(copyOfRange(source, 55, 1199)).trim(),
                new String(copyOfRange(source, 1199, 1200)).charAt(0)
        );
    }
    
    private Trailler(char tipoDoRegistro, String filler01, int totalRegistrosT1, int totalRegistrosT2, int totalRegistrosT3, long totalRegistros, String filler02, char fimDeRegistro) {
        this.tipoDoRegistro = tipoDoRegistro;
        this.filler01 = filler01;
        this.totalRegistrosT1 = totalRegistrosT1;
        this.totalRegistrosT2 = totalRegistrosT2;
        this.totalRegistrosT3 = totalRegistrosT3;
        this.totalRegistros = totalRegistros;
        this.filler02 = filler02;
        this.fimDeRegistro = fimDeRegistro;
    }
    
    public char getTipoDoRegistro() {
        return tipoDoRegistro;
    }
    
    public String getFiller01() {
        return filler01;
    }
    
    public int getTotalRegistrosT1() {
        return totalRegistrosT1;
    }
    
    public int getTotalRegistrosT2() {
        return totalRegistrosT2;
    }
    
    public int getTotalRegistrosT3() {
        return totalRegistrosT3;
    }
    
    public long getTotalRegistros() {
        return totalRegistros;
    }
    
    public String getFiller02() {
        return filler02;
    }
    
    public char getFimDeRegistro() {
        return fimDeRegistro;
    }
}
