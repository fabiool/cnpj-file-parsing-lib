package eu.fabiool.cnpj.file.parsing.lib.io;

import java.util.Arrays;
import java.util.stream.Collectors;

import eu.fabiool.cnpj.file.parsing.lib.Formatter;
import eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj;

/**
 * 
 * @author fabiool
 *
 */
public class CSVFormatter implements Formatter {

	/**
	 * 
	 */
	private static final String DEFAULT_DELIMITER = ",";
	
	/**
	 * 
	 */
	private final boolean encloseInQuote;
	
	/**
	 * 
	 */
	private final String delimiter;
			
	/**
	 * @param encloseInQuote
	 * @param delimiter
	 */
	public CSVFormatter(boolean encloseInQuote, String delimiter) {
		this.encloseInQuote = encloseInQuote;
		this.delimiter = delimiter;
	}

	/**
	 * 
	 */
	public CSVFormatter() {
		this(true, DEFAULT_DELIMITER);
	}
	
	/**
	 * 
	 */
	@Override
	public String formatHeader(final Class<? extends InfoCnpj> clazz) {
		return formatArr(InfoCnpj.headers(clazz));
	}

	/**
	 * 
	 */
	@Override
	public String formatHeader(final InfoCnpj info) {
		return formatArr(info.getHeaders());
	}

	/**
	 * 
	 */
	@Override
	public String format(final InfoCnpj info) {
		return formatArr(info.getValues());
	}
	
	/**
	 * 
	 * @param arr
	 * @return
	 */
	private String formatArr(final String[]  arr) {
		return Arrays.asList(arr)
			  .stream()
			  .map(s -> { return enclose(s); })
			  .collect(Collectors.joining(delimiter));
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	private String enclose(String s) {
		return (encloseInQuote) ?  String.format("\"%s\"", s) : s;
	}
}
