/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fabiool.cnpj.file.parsing.lib;

import eu.fabiool.cnpj.file.parsing.lib.model.CnaeSecundaria;
import eu.fabiool.cnpj.file.parsing.lib.model.DadosCadastrais;
import eu.fabiool.cnpj.file.parsing.lib.model.Header;
import eu.fabiool.cnpj.file.parsing.lib.model.InfoCnpj;
import eu.fabiool.cnpj.file.parsing.lib.model.RecordFactory;
import eu.fabiool.cnpj.file.parsing.lib.model.Socio;
import eu.fabiool.cnpj.file.parsing.lib.model.Trailler;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Fabio
 */
public class CnpjFileParser implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(CnpjFileParsingLibApplication.class.getName());

    private static final int RECORD_SIZE_BYTES = 1200;

    private static final int CHUNK_RECORDS_COUNT = 1000;

    private final Path zipFile;

    private final Path outputFolder;

    public static CnpjFileParser getParser(final Path zipFile, final Path outputFolder) {
        if (!zipFile.toFile().exists() || !zipFile.toFile().canRead()) {
            throw new IllegalArgumentException(String.format("Can´t read from %s", zipFile.toAbsolutePath().toString()));
        }
        outputFolder.toFile().mkdirs();
        return new CnpjFileParser(zipFile, outputFolder);
    }

    private CnpjFileParser(Path zipFile, Path outputFolder) {
        this.zipFile = zipFile;
        this.outputFolder = outputFolder;
    }

    private int getChunckSizeBytes() {
        return RECORD_SIZE_BYTES * CHUNK_RECORDS_COUNT;
    }

    @Override
    public void run() {

        LOGGER.log(Level.INFO, String.format("Processing file %s started!", zipFile.toAbsolutePath().toString()));

        try (final FileInputStream fis = new FileInputStream(zipFile.toFile());
                final ZipInputStream zis = new ZipInputStream(fis)) {
            
            ZipEntry currentFile = zis.getNextEntry();

            while (null != currentFile) {
                
                LOGGER.log(Level.INFO, String.format("Processing entry: %s", currentFile.getName()));

                final byte[] buff = new byte[1200];
                int readCount = zis.read(buff);

                while (1200 == readCount) {

                    final InfoCnpj infoCnpj = RecordFactory.getRecord(buff);

                    if (infoCnpj instanceof Header) {
                        LOGGER.log(Level.INFO, "Got a Header record");
                    } else if (infoCnpj instanceof DadosCadastrais) {
                        LOGGER.log(Level.INFO, "Got a DadosCadastrais record");
                    } else if (infoCnpj instanceof Socio) {
                        LOGGER.log(Level.INFO, "Got a Socio record");
                    } else if (infoCnpj instanceof CnaeSecundaria) {
                        LOGGER.log(Level.INFO, "Got a CnaeSecundaria record");
                    } else if (infoCnpj instanceof Trailler) {
                        LOGGER.log(Level.INFO, "Got a Trailler record");
                    } else {
                        LOGGER.log(Level.WARNING, "Unknown record!");
                    }

                    readCount = zis.read(buff);
                }
                
                currentFile = zis.getNextEntry();
            }
            
        } catch (IOException | ParseException ex) {
            LOGGER.log(Level.SEVERE, String.format("Processing file %s aborted due to exception %s!", zipFile.toAbsolutePath().toString(), ex.getMessage()), ex);
            throw new RuntimeException(ex);
        }

        LOGGER.log(Level.INFO, String.format("Processing file %s finished!", zipFile.toAbsolutePath().toString()));
    }
}
