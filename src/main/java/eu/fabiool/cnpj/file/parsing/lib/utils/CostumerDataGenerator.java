package eu.fabiool.cnpj.file.parsing.lib.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.stream.Collectors;

public class CostumerDataGenerator {

	private static final int FIELDS_COUNT = 15;
	
	private static final int NOME = 0;
	private static final int CPF = 1;
	private static final int RG = 2;
	private static final int DT_NASC = 3;
	private static final int EMAIL = 4;
	private static final int SEXO = 5;
	private static final int TELEFONE = 6;
	private static final int ENDERECO = 7;
	private static final int NR_ENDERECO = 8;
	private static final int COMPLEMENTO = 9;
	private static final int BAIRRO = 10;
	private static final int CEP = 11;
	private static final int CIDADE = 12;
	private static final int UF = 13;
	private static final int DT_CADASTRO = 14;

	private static final int EARLIEST_BIRTH = 1932;
	private static final int LATEST_BIRTH = 2001;

	private static final int EARLIEST_RECORD = 1982;
	private static final int LATEST_RECORD = 2019;
		
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {

		final Path seedStore = Paths.get(args[0]);
		
		int count = Integer.parseInt(args[1]);

		final NameGenerator g = NameGenerator.newInstance(seedStore);
		final AddressGenerator a = AddressGenerator.newInstance(seedStore);
		
		final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		final Random rand = new Random(System.currentTimeMillis());

		System.out.println("\"NOME\";\"CPF\";\"RG\";\"DT_NASC\";\"EMAIL\";\"SEXO\";\"TELEFONE\";\"ENDERECO\";\"NR_ENDERECO\";\"COMPLEMENTO\";\"BAIRRO\";\"CEP\";\"CIDADE\";\"UF\";\"DT_CADASTRO\"");
		
		while(count > 0) {

			final String[] row = new String[FIELDS_COUNT];
			
			Address address;
			while((address = Address.parse(a.generate(rand))) == null);
			
			row[NOME] = g.generate(rand);
			row[CPF] = generateCPF(rand);
			row[RG] = generateRG(rand);
			row[DT_NASC] = sdf.format(generateDate(rand, EARLIEST_BIRTH, LATEST_BIRTH));
			row[EMAIL] = generateEmail(rand, row[NOME]);
			row[SEXO] = generateGender(rand);
			row[TELEFONE] = generatePhoneNumber(rand, address);
			row[ENDERECO] = getAddressLine1(address);
			row[NR_ENDERECO] = address.getNumero();
			row[COMPLEMENTO] = address.getComplemento();
			row[BAIRRO] = address.getBairro();
			row[CEP] = address.getCep();
			row[CIDADE] = address.getMunicipio();
			row[UF] = address.getUf();
			row[DT_CADASTRO] = sdf.format(generateDate(rand, EARLIEST_RECORD, LATEST_RECORD));

			System.out.println(format(row));
			
			count--;
		}
	}
	
	/**
	 * 
	 * @param row
	 * @return
	 */
	private static String format(String[] row) {
		return Arrays.asList(row)
				.stream()
				.map(s -> { return String.format("\"%s\"", s); })
				.collect(Collectors.joining(";"));
	}

	/**
	 * 
	 * @param rand
	 * @return
	 * @throws ParseException
	 */
	private static Date generateDate(Random rand, int minYear, int maxYear) throws ParseException {

		final Calendar c = new GregorianCalendar();
		
		c.set(Calendar.YEAR, rand.nextInt(maxYear - minYear) + minYear);
		c.set(Calendar.MONTH, rand.nextInt(12));
		c.set(Calendar.DAY_OF_MONTH, rand.nextInt(31));
		c.set(Calendar.HOUR, rand.nextInt(24));
		c.set(Calendar.MINUTE, rand.nextInt(60));
		c.set(Calendar.SECOND, rand.nextInt(60));
		
		return c.getTime();
	}
	
//	/**
//	 * 
//	 * @param rand
//	 * @return
//	 * @throws ParseException
//	 */
//	private static Date generateBirthDate(Random rand, int minYear, int maxYear) throws ParseException {
//
//		final Calendar c = new GregorianCalendar();
//		
//		c.set(Calendar.YEAR, rand.nextInt(maxYear - minYear) + minYear);
//		c.set(Calendar.MONTH, rand.nextInt(12));
//		c.set(Calendar.DAY_OF_MONTH, rand.nextInt(31));
//		c.set(Calendar.HOUR, rand.nextInt(24));
//		c.set(Calendar.MINUTE, rand.nextInt(60));
//		c.set(Calendar.SECOND, rand.nextInt(60));
//
//		return c.getTime();
//	}

	/**
	 * 
	 * @param address
	 * @return
	 */
	private static String getAddressLine1(Address address) {
		return String.format(
				"%s %s", 
				address.getDescricaoTipoLogradouro(), 
				address.getLogradouro()).trim();
	}

	/**
	 * 
	 * @param rand
	 * @param address
	 * @return
	 */
	private static String generatePhoneNumber(Random rand, Address address) {
		
		return String.format("%s%s%04d%04d"
				, address.getDdd1()
				, (rand.nextInt(100) >= 70) ? "9" : ""
				, rand.nextInt(7000)+3000
				, rand.nextInt(10000));
	}

	/**
	 * 
	 * @param rand
	 * @return
	 */
	private static String generateGender(Random rand) {
		return (rand.nextInt(100) >= 50) ? "F" : "M";
	}

	/**
	 * 
	 * @param rand
	 * @param name
	 * @return
	 */
	private static String generateEmail(final Random rand, final String name) {
		final String[] parts = name.split(" ");
		return String.format("%s.%s%d@anonymous.com", parts[0], parts[1], rand.nextInt(99999));
	}

	/**
	 * 
	 * @param rand
	 * @return
	 */
	private static String generateRG(Random rand) {
		return String.format(
				"%02d%03d%03d-%d"
				, rand.nextInt(100)
				, rand.nextInt(1000)
				, rand.nextInt(100)
				, rand.nextInt(10));
	}

	/**
	 * 
	 * @param rand
	 * @return
	 */
	private static String generateCPF(final Random rand) {
		String cpf;
		while(CPFUtils.validate(cpf = CPFUtils.generate(rand)));
		return cpf;
	}
}
