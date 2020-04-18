/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Fabio
 */
public interface InfoCnpj {
	
	String[] getHeaders();
	
	String[] getValues();
    	
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
    
    static String[] headers(AbstractInfoCnpj abstractInfoCnpj) {
    	return (String[]) Arrays.asList(abstractInfoCnpj.getClass().getFields()).stream().map(f -> { return f.getName(); }).toArray();
    }

    static String[] values(AbstractInfoCnpj abstractInfoCnpj) {
    	return (String[]) Arrays.asList(abstractInfoCnpj.getClass().getFields())
    			.stream()
    			.map(f -> { 
    			
    				f.setAccessible(true);
    				
    				String value = null;
    				
					try {
					
						value = f.get(f.getName()).toString();
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

    				f.setAccessible(false);
    				
    				return value;
    			
    			}).toArray();
    }
}
