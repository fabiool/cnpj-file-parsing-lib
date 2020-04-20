package eu.fabiool.cnpj.file.parsing.lib;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import eu.fabiool.cnpj.file.parsing.lib.utils.CPFUtils;

public class CnpjFileParsingLibApplication {

	public static void main(String[] args) {
		final Path zipFile = Paths.get(args[0]);
		final Path outputFolder = Paths.get(args[1]);
		// CnpjFileParser.getParser(zipFile, outputFolder).run();
		// CnpjFileParserApache.getParser(zipFile, outputFolder).run();
		// CnpjFileParserRaw.getParser(zipFile, outputFolder).run();

		// String[] cpfs = { "88883990625", "51943255172", "12345678901", "32985693286"
		// };

		Random rand = new Random();

		Set<String> cpfs = new HashSet<>();

		while (cpfs.size() < 200000) {

			String cpf = CPFUtils.generate(rand);

			if (!CPFUtils.validate(cpf) && !cpfs.contains(cpf)) {

				cpfs.add(cpf);

				final String row = String.format("%d;%s", cpfs.size(), cpf);

				System.out.println(row);

			}
		}
	}
}
