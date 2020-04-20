package eu.fabiool.cnpj.file.parsing.lib.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListsLoader implements FileVisitor<Path> {

	/**
	 * 
	 */
	private final HashMap<Integer, List<String>> lists = new HashMap<>();

	/**
	 * 
	 */
	private final Pattern namePattern;

	/**
	 * 
	 * @param namePattern
	 * @return
	 */
	public static final ListsLoader newInstance(final Pattern namePattern) {
		return new ListsLoader(namePattern);
	}

	/**
	 * 
	 * @param namePattern
	 */
	private ListsLoader(final Pattern namePattern) {
		this.namePattern = namePattern;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

		final Matcher m = namePattern.matcher(file.getFileName().toString());

		if (m.matches()) {
			
			Integer key = null;
			
			if (m.groupCount() > 0) {
			
				key = Integer.parseInt(m.group(1));
			
			} else {

				key = new Integer(0);
			}
			
			lists.put(key, loadFile(file));
		}

		return FileVisitResult.CONTINUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		return FileVisitResult.TERMINATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private List<String> loadFile(final Path file) throws IOException {

		final List<String> contents = new ArrayList<>();

		try (final FileReader r = new FileReader(file.toFile()); 
				final BufferedReader br = new BufferedReader(r)) {
			String st;
			while ((st = br.readLine()) != null) {
				contents.add(st);
			}
		}

		return contents;
	}

	/**
	 * 
	 * @return
	 */
	public HashMap<Integer, List<String>> getLists() {
		return lists;
	}
}