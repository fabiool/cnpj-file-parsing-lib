/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib.model;

import java.text.ParseException;

/**
 *
 * @author Fabio
 */
public class RecordFactory {

    public static InfoCnpj getRecord(final byte[] source) throws ParseException {
        
        switch (source[0]) {
            case '0':
                return Header.parse(source);
            case '1':
                return DadosCadastrais.parse(source);
            case '2':
                return Socio.parse(source);
            case '6':
                return CnaeSecundaria.parse(source);
            case '9':
                return Trailler.parse(source); 
            default:
                break;
        }
        
        throw new IllegalArgumentException("Tipo de Registro Desconhecido");
    }
}
