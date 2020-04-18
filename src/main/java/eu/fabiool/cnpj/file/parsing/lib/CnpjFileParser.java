/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import eu.fabiool.cnpj.file.parsing.lib.io.CSVFormatter;
import eu.fabiool.cnpj.file.parsing.lib.io.OutputFilesBundle;
import eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj;
import eu.fabiool.cnpj.file.parsing.lib.model.RecordFactory;

/**
 *
 * @author Fabio
 */
public class CnpjFileParser implements Runnable {

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
    private final Path zipFile;

    /**
     * 
     */
    private final Path outputFolder;

    /**
     * 
     * @param zipFile
     * @param outputFolder
     * @return
     */
    public static CnpjFileParser getParser(final Path zipFile, final Path outputFolder) {
        if (!zipFile.toFile().exists() || !zipFile.toFile().canRead()) {
            throw new IllegalArgumentException(String.format("Can´t read from %s", zipFile.toAbsolutePath().toString()));
        }
        outputFolder.toFile().mkdirs();
        return new CnpjFileParser(zipFile, outputFolder);
    }

    /**
     * 
     * @param zipFile
     * 
     * @param outputFolder
     */
    private CnpjFileParser(Path zipFile, Path outputFolder) {
        this.zipFile = zipFile;
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

        LOGGER.log(Level.INFO, String.format("Processing file %s started!", zipFile.toAbsolutePath().toString()));

        try (final FileInputStream fis = new FileInputStream(zipFile.toFile());
                final ZipInputStream zis = new ZipInputStream(fis);
        		final OutputFilesBundle bundle = new OutputFilesBundle(outputFolder, new CSVFormatter())) {
            
            ZipEntry currentFile = zis.getNextEntry();

            int pos = 0;
            
            while (null != currentFile) {
                
                LOGGER.log(Level.INFO, String.format("Processing entry: %s", currentFile.getName()));

                final byte[] buff = new byte[RECORD_SIZE_BYTES];
                int readCount = zis.read(buff);

                while (RECORD_SIZE_BYTES == readCount) {
                	
                	pos++;

                    final InfoCnpj infoCnpj = RecordFactory.getRecord(buff);

                    LOGGER.log(Level.INFO, String.format("Got a %s record at position %d", infoCnpj.getClass().getSimpleName(), pos));
                                        
                    bundle.write(infoCnpj);

                    readCount = zis.read(buff);
                }
                
                currentFile = zis.getNextEntry();
            }
            
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, String.format("Processing file %s aborted due to exception %s!", zipFile.toAbsolutePath().toString(), ex.getMessage()), ex);
            throw new RuntimeException(ex);
        }

        LOGGER.log(Level.INFO, String.format("Processing file %s finished!", zipFile.toAbsolutePath().toString()));
    }
}
