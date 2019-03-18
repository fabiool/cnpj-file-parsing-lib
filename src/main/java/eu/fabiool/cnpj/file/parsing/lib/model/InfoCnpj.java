/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Fabio
 */
public interface InfoCnpj {
    
    static final String PATTERN = "yyyyMMdd";
    
    static Date parseDate(byte[] source) throws ParseException {
        return new SimpleDateFormat(PATTERN).parse(new String(source));
    }

    static int parseInt(byte[] source) {
        return Integer.parseInt(new String(source));
    }

    static long parseLong(byte[] source) {
        return Long.parseLong(new String(source));
    }
}
