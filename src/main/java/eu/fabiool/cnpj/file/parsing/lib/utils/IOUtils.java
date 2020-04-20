package eu.fabiool.cnpj.file.parsing.lib.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class IOUtils {
	
	/**
	 * 
	 */
	private final static String FILENAME_REGEX = "COMPONENT_(\\d\\d).txt";

	/**
	 * 
	 * @param start
	 * @param visitor
	 * @return
	 * @throws IOException 
	 */
	public static HashMap<Integer, List<String>> loadLists(Path start) throws IOException {

		final ListsLoader listsLoader = ListsLoader.newInstance(Pattern.compile(FILENAME_REGEX));
		
		Files.walkFileTree(start, listsLoader);
		
		return listsLoader.getLists();
	}

}
