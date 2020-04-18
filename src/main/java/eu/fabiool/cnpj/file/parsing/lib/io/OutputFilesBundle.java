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
	private final HashMap<Class<? extends InfoCnpj>, BufferedWriter> bundle = new HashMap<>();

	/**
	 * 
	 */
	private final Path outputDir;
	
	/**
	 * 
	 */
	private final Formatter formatter;
	
	/**
	 * @param outputDir
	 */
	public OutputFilesBundle(Path outputDir, Formatter formatter) {
		this.outputDir = outputDir;
		this.formatter = formatter;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void open() throws Exception {

		if (!this.outputDir.toFile().canWrite()) {
			throw new IllegalArgumentException(String.format("Can't write to %s", this.outputDir.toFile().getAbsolutePath()));
		}
		
		MODEL_CLASSES.stream().forEach(c -> {
			try {
				bundle.put(c, openFile(c));
			} catch (IOException e) {
				// swallow exception
			}
		});
		
	}
	
	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	private BufferedWriter openFile(Class<? extends InfoCnpj> clazz) throws IOException {
		final Path fileName = Paths.get(this.outputDir.toFile().getAbsolutePath(), String.format("%s.txt", clazz.getSimpleName()));
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

		if (bundle.containsKey(line.getClass())) {
			bundle.get(line.getClass()).write(String.format("%s%s", formatter.format(line), System.getProperty("line.separator")));
		}

		throw new IllegalArgumentException(String.format("Unsupported class %s", line.getClass().getSimpleName()));
	}
}
