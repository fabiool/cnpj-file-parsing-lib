package eu.fabiool.cnpj.file.parsing.lib.io;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import eu.fabiool.cnpj.file.parsing.lib.Formatter;
import eu.fabiool.cnpj.file.parsing.lib.model.CnaeSecundaria;
import eu.fabiool.cnpj.file.parsing.lib.model.DadosCadastrais;
import eu.fabiool.cnpj.file.parsing.lib.model.Header;
import eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj;
import eu.fabiool.cnpj.file.parsing.lib.model.Socio;
import eu.fabiool.cnpj.file.parsing.lib.model.Trailler;

/**
 * 
 * @author fabiool
 *
 */
public class OutputFilesBundle implements Closeable {

	/**
	 * 
	 */
	private static final List<Class<? extends InfoCnpj>> MODEL_CLASSES = 
			Arrays.asList(CnaeSecundaria.class, DadosCadastrais.class, Header.class, Socio.class, Trailler.class);
	
	/**
	 * 
	 */
	private final HashMap<Class<? extends InfoCnpj>, BufferedWriter> bundle;
	
	/**
	 * 
	 */
	private final Formatter formatter;
	
	/**
	 * @param outputDir
	 * @throws Exception 
	 */
	public OutputFilesBundle(final Path outputDir, final Formatter formatter) throws Exception {
		this.bundle = open(outputDir, formatter);
		this.formatter = formatter;
		
		writeHeaders(bundle, this.formatter);
	}

	/**
	 * 
	 * @throws Exception
	 */
	private static HashMap<Class<? extends InfoCnpj>, BufferedWriter> open(final Path outputDir, final Formatter formatter) throws Exception {

		final HashMap<Class<? extends InfoCnpj>, BufferedWriter> bundle = new HashMap<>();
		
		if (!outputDir.toFile().canWrite()) {
			throw new IllegalArgumentException(String.format("Can't write to %s", outputDir.toFile().getAbsolutePath()));
		}
		
		MODEL_CLASSES.stream().forEach(c -> {
			try {
				bundle.put(c, openFile(c, outputDir));
			} catch (IOException e) {
				// swallow exception
			}
		});
		
		return bundle;
	}

	/**
	 * 
	 * @param clazz
	 * @param line
	 * @throws IOException
	 */
	private static void writeLine(final BufferedWriter writer, String line) throws IOException {
		writer.write(String.format("%s%s", line, System.getProperty("line.separator")));
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	private static void writeHeaders(final HashMap<Class<? extends InfoCnpj>, BufferedWriter> bundle, final Formatter formatter) throws Exception {
		
		bundle.keySet().stream().forEach(c -> {

			try {
				
				writeLine(bundle.get(c), formatter.formatHeader(c));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
	}
	
	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	private static BufferedWriter openFile(Class<? extends InfoCnpj> clazz, final Path outputDir) throws IOException {
		final Path fileName = Paths.get(outputDir.toFile().getAbsolutePath(), String.format("%s.txt", clazz.getSimpleName()));
		return new BufferedWriter(new FileWriter(fileName.toFile()));		
	}
	
	/**
	 * 
	 */
	@Override
	public void close() {
		if (null != bundle) {
			bundle.values().stream().forEach(t -> {
				try {
					t.close();
				} catch (IOException e) {
					// swallow the exception
				}
			});
		}
	}
	
	/**
	 * 
	 * @param line
	 * @throws IOException
	 */
	public void write(final InfoCnpj line) throws IOException {

		if (!bundle.containsKey(line.getClass())) {
			throw new IllegalArgumentException(String.format("Unsupported class %s", line.getClass().getSimpleName()));
		}

		bundle.get(line.getClass()).write(String.format("%s%s", formatter.format(line), System.getProperty("line.separator")));
	}
}
