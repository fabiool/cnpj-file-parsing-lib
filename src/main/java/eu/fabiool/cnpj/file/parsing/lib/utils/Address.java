package eu.fabiool.cnpj.file.parsing.lib.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Address {
	
	/**
	 * 
	 */
	private static final String ROW_REGEX = "([^;]*)|(\\\"[^\\\"]+\\\")";
	
	/**
	 * 
	 */
	private final String descricaoTipoLogradouro;
	
	/**
	 * 
	 */
	private final String logradouro;
	
	/**
	 * 
	 */
	private final String numero;
	
	/**
	 * 
	 */
	private final String complemento;
	
	/**
	 * 
	 */
	private final String bairro;
	
	/**
	 * 
	 */
	private final String cep;
	
	/**
	 * 
	 */
	private final String uf;
	
	/**
	 * 
	 */
	private final String codigoMunicipio;
	
	/**
	 * 
	 */
	private final String municipio;
	
	/**
	 * 
	 */
	private final String ddd1;
	
	/** 
	 * 
	 * @param row
	 * @return
	 */
	public static Address parse(final String row) { 

		final String[] fields = row.split(";");

		if (10 != fields.length) {
			return null;
		}
		
		for(int i=0; i < fields.length; i++) {
			fields[i] = fields[i].substring(0, fields[i].length()-1).substring(1);
		}
		
		return new Address(
				fields[0],
				fields[1],
				fields[2],
				fields[3],
				fields[4],
				fields[5],
				fields[6],
				fields[7],
				fields[8],
				fields[9]);
	}


	/**
	 * @param descricaoTipoLogradouro
	 * @param logradouro
	 * @param numero
	 * @param complemento
	 * @param bairro
	 * @param cep
	 * @param uf
	 * @param codigoMunicipio
	 * @param municipio
	 * @param ddd1
	 */
	private Address(String descricaoTipoLogradouro, String logradouro, String numero, String complemento, String bairro,
			String cep, String uf, String codigoMunicipio, String municipio, String ddd1) {
		this.descricaoTipoLogradouro = descricaoTipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.uf = uf;
		this.codigoMunicipio = codigoMunicipio;
		this.municipio = municipio;
		this.ddd1 = ddd1;
	}


	/**
	 * @return the descricaoTipoLogradouro
	 */
	public String getDescricaoTipoLogradouro() {
		return descricaoTipoLogradouro;
	}


	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}


	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}


	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}


	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}


	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}


	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}


	/**
	 * @return the codigoMunicipio
	 */
	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}


	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}


	/**
	 * @return the ddd1
	 */
	public String getDdd1() {
		return ddd1;
	}

	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {

		final String row = "\"AVENIDA\";\"PADRE ADOLPHO ROHL\";\"2409\";\"SALA  01\";\"SETOR 01\";\"76890000\";\"RO\";\"0015\";\"JARU\";\"69\"";
		
		final Address a = Address.parse(row);

		System.out.println(a.toString());
		
	}
}
