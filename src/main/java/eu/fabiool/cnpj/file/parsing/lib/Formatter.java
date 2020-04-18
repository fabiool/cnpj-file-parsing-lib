package eu.fabiool.cnpj.file.parsing.lib;

import eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj;

/**
 * 
 * @author fabiool
 *
 */
public interface Formatter {
	
	/**
	 * 
	 * @param info
	 * @return
	 */
	String formatHeader(final InfoCnpj info);

	/**
	 * 
	 * @param info
	 * @return
	 */
	String format(final InfoCnpj info);

}
