/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib;

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
public class CnpjFileParserApache implements Runnable {

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
    private final Path zipFilePath;

    /**
     * 
     */
    private final Path outputFolder;

    /**
     * 
     * @param zipFilePath
     * @param outputFolder
     * @return
     */
    public static CnpjFileParserApache getParser(final Path zipFilePath, final Path outputFolder) {
        if (!zipFilePath.toFile().exists() || !zipFilePath.toFile().canRead()) {
            throw new IllegalArgumentException(String.format("Can´t read from %s", zipFilePath.toAbsolutePath().toString()));
        }
        outputFolder.toFile().mkdirs();
        return new CnpjFileParserApache(zipFilePath, outputFolder);
    }

    /**
     * 
     * @param zipFile
     * 
     * @param outputFolder
     */
    private CnpjFileParserApache(Path zipFilePath, Path outputFolder) {
        this.zipFilePath = zipFilePath;
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

        LOGGER.log(Level.INFO, String.format("Processing file %s started!", zipFilePath.toAbsolutePath().toString()));

        try (final ZipFile zf = new ZipFile(zipFilePath.toFile());
        		final OutputFilesBundle bundle = new OutputFilesBundle(outputFolder, new CSVFormatter())) {
                    	
        	
        	Enumeration<ZipArchiveEntry> entries = zf.getEntries();
        	            
            while (entries.hasMoreElements()) {
            	
            	ZipArchiveEntry entry = entries.nextElement();

                LOGGER.log(Level.INFO, String.format("Processing entry: %s", entry.getName()));

                final long size = entry.getSize();

                int offset = 0;

                try(final InputStream content = zf.getInputStream(entry)) {
                
                    while (offset < size) {
                    	
                    	final byte[] buff = new byte[RECORD_SIZE_BYTES+2];

                    	for(int i = 0; i < RECORD_SIZE_BYTES; i++) {
                    		buff[i] = 0;
                    	}
                    	
//                    	for(int i = 0; i < RECORD_SIZE_BYTES; i++) {
//                    		buff[i] = (byte) content.read();
//                    	}

                    	final int bytesRead = content.read(buff);

                        LOGGER.log(Level.INFO, 
                        		String.format(
                        				"bytesRead = %d, payload = '%s'", 
                        				bytesRead, new String(buff)));
                    	
                    	
                    	if (RECORD_SIZE_BYTES == bytesRead) {

                    		final InfoCnpj infoCnpj = RecordFactory.getRecord(buff);

                            LOGGER.log(Level.INFO, 
                            		String.format(
                            				"Got a %s record at position %d of %d", 
                            				infoCnpj.getClass().getSimpleName(), 
                            				offset, 
                            				size));
                                                
                            bundle.write(infoCnpj);
                            
                            // discards the line separator (CR+LF)
                            // content.read(); 
                            // content.read();
                    	
                    	}
                    	
                    	offset += bytesRead;
                    }
                }
            }
            
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, String.format("Processing file %s aborted due to exception %s!", zipFilePath.toAbsolutePath().toString(), ex.getMessage()), ex);
            throw new RuntimeException(ex);
        }

        LOGGER.log(Level.INFO, String.format("Processing file %s finished!", zipFilePath.toAbsolutePath().toString()));
    }
}
