package main.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class InputReader {
	
	public List<String> readLines(String input) {
		try (BufferedReader in = new BufferedReader(new FileReader(input))) {
			return Stream.generate(() -> readLineSafely(in))
					.takeWhile(line -> line != null)
					.toList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return List.of();
	}

	private String readLineSafely(BufferedReader in) {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}