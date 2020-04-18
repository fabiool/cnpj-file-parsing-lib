/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import eu.fabiool.cnpj.file.parsing.lib.CnpjFileParsingLibApplication;

/**
 *
 * @author Fabio
 */
public interface InfoCnpj {
	/**
	 * 
	 */
    static final Logger LOGGER = Logger.getLogger(CnpjFileParsingLibApplication.class.getName());
	
	String[] getHeaders();
	
	String[] getValues();
    	
    static final String PATTERN = "yyyyMMdd";
    
    static Date parseDate(byte[] source) throws ParseException {
    	
    	try {
	    	String date;
	    	
	    	if ((null != source) && !"".equals(date = new String(source).trim())) {
	    		return new SimpleDateFormat(PATTERN).parse(new String(date));
	    	}
	    	
    	} catch (ParseException pe) {
    		// swallow exception
    	}
	    	
    	return null;
    }

    static int parseInt(byte[] source) {
        return Integer.parseInt(new String(source));
    }

    static long parseLong(byte[] source) {
        return Long.parseLong(new String(source));
    }
    
    static String[] headers(Class<? extends InfoCnpj> clazz) {
    	return Arrays.asList(clazz.getDeclaredFields())
    			.stream()
    			.map(f -> { 

    					final int mod = f.getModifiers(); 
	    				
						if (Modifier.isFinal(mod) && Modifier.isPrivate(mod) && !Modifier.isTransient(mod)) {
    
							return f.getName();
						
						}
						
						return null;

    				})
    			.filter(Objects::nonNull)
    			.collect(Collectors.toList())
    			.toArray(new String[0]);
    }

	static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
    
    
    static String[] values(AbstractInfoCnpj abstractInfoCnpj) {
    	return Arrays.asList(abstractInfoCnpj.getClass().getDeclaredFields())
    			.stream()
    			.map(f -> { 
    				
    					final int mod = f.getModifiers(); 
    				
    					if (Modifier.isFinal(mod) && Modifier.isPrivate(mod) && !Modifier.isTransient(mod)) {
    						
    						try {
								
    							final Method method = abstractInfoCnpj.getClass().getMethod(String.format("get%s", capitalize(f.getName())), new Class<?>[0]);
								
    							final Object rawValue = method.invoke(abstractInfoCnpj, new Object[0]);
    							
    							return (null != rawValue) ? rawValue.toString() : null;
    							
							} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {

								return e1.toString();
							}
    					}
    				
    					return null;
    				
	    			})
    			.filter(Objects::nonNull)
    			.collect(Collectors.toList())
    			.toArray(new String[0]);
    }
}
