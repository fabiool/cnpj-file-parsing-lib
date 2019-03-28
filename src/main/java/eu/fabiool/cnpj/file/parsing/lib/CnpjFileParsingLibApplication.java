package eu.fabiool.cnpj.file.parsing.lib;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CnpjFileParsingLibApplication {

    public static void main(String[] args) {
//        final Path zipFile = Paths.get(args[0]);
        final Path zipFile = Paths.get("/home/matheusvargas/Documentos/TATIC/CNPJ/DADOS/F.K032001K.D81106D");
//        final Path outputFolder = Paths.get(args[1]);
        final Path outputFolder = Paths.get("/home/matheusvargas/Documentos/TATIC/CNPJ/");
        CnpjFileParser.getParser(zipFile, outputFolder).run();
    }
}
