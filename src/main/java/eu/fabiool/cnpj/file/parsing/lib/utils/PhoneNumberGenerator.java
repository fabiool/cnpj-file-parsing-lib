package eu.fabiool.cnpj.file.parsing.lib.utils;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PhoneNumberGenerator {
	
	/**
	 * 
	 */
	private final static String FILENAME_REGEX = "telefones.txt";

	/**
	 * 
	 */
	private static final String BLANK_SPACE = " ";
	
	/**
	 * 
	 */
	private final HashMap<Integer, List<String>> seeds;

	/**
	 * 
	 * @param lists
	 */
	public PhoneNumberGenerator(HashMap<Integer, List<String>> seeds) {
		this.seeds = seeds;
	}

	/**
	 * 
	 * @param seedStore
	 * @return
	 * @throws IOException
	 */
	public static PhoneNumberGenerator newInstance(final Path seedStore) throws IOException {
		return new PhoneNumberGenerator(IOUtils.loadLists(seedStore, Pattern.compile(FILENAME_REGEX))); 
	}

	/**
	 * 
	 * @param rand
	 * @return
	 */
	public String generate(final Random rand) {
		int index = rand.nextInt(seeds.get(0).size());
		return seeds.get(0).get(index);
	}

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		final Path seedStore = Paths.get(args[0]);
		
		int count = Integer.parseInt(args[1]);

		final PhoneNumberGenerator g = PhoneNumberGenerator.newInstance(seedStore);
		
		final Random rand = new Random(System.currentTimeMillis());

		while(count > 0) {
			
			System.out.println(g.generate(rand));
			
			count--;
		}
	}
}
