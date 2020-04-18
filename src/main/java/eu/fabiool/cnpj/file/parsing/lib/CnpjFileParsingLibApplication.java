package eu.fabiool.cnpj.file.parsing.lib;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CnpjFileParsingLibApplication {

    public static void main(String[] args) {
        final Path zipFile = Paths.get(args[0]);
        final Path outputFolder = Paths.get(args[1]);
        // CnpjFileParser.getParser(zipFile, outputFolder).run();
        // CnpjFileParserApache.getParser(zipFile, outputFolder).run();
        
        CnpjFileParserRaw.getParser(zipFile, outputFolder).run();
    }
}
