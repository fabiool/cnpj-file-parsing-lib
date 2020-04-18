/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import eu.fabiool.cnpj.file.parsing.lib.io.CSVFormatter;
import eu.fabiool.cnpj.file.parsing.lib.io.OutputFilesBundle;
import eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj;
import eu.fabiool.cnpj.file.parsing.lib.model.RecordFactory;

/**
 *
 * @author Fabio
 */
public class CnpjFileParserRaw implements Runnable {

	/**
	 * 
	 */
    private static final Logger LOGGER = Logger.getLogger(CnpjFileParsingLibApplication.class.getName());

    /**
     * 
     */
    private static final int RECORD_SIZE_BYTES = 1200;

    /**
     * 
     */
    private static final int CHUNK_RECORDS_COUNT = 1000;

    /**
     * 
     */
    private final Path inputFilePath;

    /**
     * 
     */
    private final Path outputFolder;

    /**
     * 
     * @param inputFilePath
     * @param outputFolder
     * @return
     */
    public static CnpjFileParserRaw getParser(final Path inputFilePath, final Path outputFolder) {
        if (!inputFilePath.toFile().exists() || !inputFilePath.toFile().canRead()) {
            throw new IllegalArgumentException(String.format("Can´t read from %s", inputFilePath.toAbsolutePath().toString()));
        }
        outputFolder.toFile().mkdirs();
        return new CnpjFileParserRaw(inputFilePath, outputFolder);
    }

    /**
     * 
     * @param zipFile
     * 
     * @param outputFolder
     */
    private CnpjFileParserRaw(Path inputFilePath, Path outputFolder) {
        this.inputFilePath = inputFilePath;
        this.outputFolder = outputFolder;
    }

    /**
     * 
     * @return
     */
    private int getChunckSizeBytes() {
        return RECORD_SIZE_BYTES * CHUNK_RECORDS_COUNT;
    }

    /**
     * 
     */
    @Override
    public void run() {

        LOGGER.log(Level.INFO, String.format("Processing file %s started!", inputFilePath.toAbsolutePath().toString()));

        try ( final FileReader fr = new FileReader(inputFilePath.toFile());
        		final BufferedReader br = new BufferedReader(fr);
        		final OutputFilesBundle bundle = new OutputFilesBundle(outputFolder, new CSVFormatter())) {
            
        	long lineNumber = 0;
        	
        	String line;
        	while ((line = br.readLine()) != null) {

        		lineNumber++;
        	
	            LOGGER.log(Level.INFO, 
	            		String.format(
	            				"bytesRead = %d, payload = '%s'", 
	            				line.length(), line));
                    	
	            final InfoCnpj infoCnpj = RecordFactory.getRecord(line.getBytes());

                LOGGER.log(Level.INFO, 
                		String.format(
                				"Got a %s record at position %d. Payload = '%s'", 
                				infoCnpj.getClass().getSimpleName(), 
                				lineNumber, 
                				line));
                                                
                bundle.write(infoCnpj);
                            
        	}
            
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, String.format("Processing file %s aborted due to exception %s!", inputFilePath.toAbsolutePath().toString(), ex.getMessage()), ex);
            throw new RuntimeException(ex);
        }

        LOGGER.log(Level.INFO, String.format("Processing file %s finished!", inputFilePath.toAbsolutePath().toString()));
    }
}
