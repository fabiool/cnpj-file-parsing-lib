package eu.fabiool.cnpj.file.parsing.lib.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CPFUtils {

	public static void main(String[] args) {
		
		final int count = Integer.parseInt(args[0]);

		Random rand = new Random();

		Set<String> cpfs = new HashSet<>();

		while (cpfs.size() < count) {

			String cpf = CPFUtils.generate(rand);

			if (!CPFUtils.validate(cpf) && !cpfs.contains(cpf)) {

				cpfs.add(cpf);

				final String row = String.format("%d;%s", cpfs.size(), cpf);

				System.out.println(row);

			}
		}
		
		cpfs.clear();
	}

	private CPFUtils() {
		// utility class
	}

	public static String generate(Random rand) {
		return String.format("%03d%03d%03d%02d", 
				rand.nextInt(1000), 
				rand.nextInt(1000), 
				rand.nextInt(1000),
				rand.nextInt(100));
	}

	/**
	 * 
	 * @param cpf
	 * @return
	 */
	public static boolean validate(final String cpf) {

		if ((null == cpf) || "".equals(cpf) || (cpf.length() != 11)) {
			return false;
		}

		int weight = cpf.length() - 1;
		int acc = 0;

		for (int i = 0; i < cpf.length() - 2; i++) {

			final String digit = cpf.substring(i, i + 1);

			acc += Integer.parseInt(digit) * weight;

			weight--;
		}

		int digitOne = Integer.parseInt(cpf.substring(cpf.length() - 2, cpf.length() - 1));

		if (((acc * 10) % 11) != digitOne) {
			return false;
		}

		weight = cpf.length();
		acc = 0;

		for (int i = 0; i < cpf.length() - 1; i++) {

			final String digit = cpf.substring(i, i + 1);

			acc += Integer.parseInt(digit) * weight;

			weight--;
		}

		int digitTwo = Integer.parseInt(cpf.substring(cpf.length() - 1, cpf.length()));

		if (((acc * 10) % 11) != digitTwo) {
			return false;
		}

		return true;
	}
}
