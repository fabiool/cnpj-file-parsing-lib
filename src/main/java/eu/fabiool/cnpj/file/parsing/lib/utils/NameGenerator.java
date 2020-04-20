package eu.fabiool.cnpj.file.parsing.lib.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NameGenerator {
	
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
	public NameGenerator(HashMap<Integer, List<String>> seeds) {
		this.seeds = seeds;
	}

	/**
	 * 
	 * @param seedStore
	 * @return
	 * @throws IOException
	 */
	public static NameGenerator newInstance(final Path seedStore) throws IOException {
		return new NameGenerator(IOUtils.loadLists(seedStore)); 
	}

	/**
	 * 
	 * @param rand
	 * @return
	 */
	public String generate(final Random rand) {
		
		final int c = getComponents(rand);
		
		final String[] name = new String[c];
		
		for(int i = 0; i < c; i++) {
			
			int index = rand.nextInt(seeds.get(i+1).size());
			name[i] = seeds.get(i+1).get(index);
		}
	
		return Arrays.asList(name).stream().collect(Collectors.joining(BLANK_SPACE));
	}

	/**
	 * 
	 * @param rand
	 * @return
	 */
	private int getComponents(Random rand) {
		return rand.nextInt(3) + 2;  
	}

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		final Path seedStore = Paths.get(args[0]);
		
		int count = Integer.parseInt(args[1]);

		final NameGenerator g = NameGenerator.newInstance(seedStore);
		
		final Random rand = new Random(System.currentTimeMillis());

		while(count > 0) {
			
			System.out.println(g.generate(rand));
			
			count--;
		}
	}
}
