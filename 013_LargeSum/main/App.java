package main;

import java.util.List;

import main.adder.LargeNumberAdder;
import main.adder.PartializedLongAdder;
import main.reader.InputReader;

/**
 * @author Depiphron
 */
public class App {

	private static final String INPUT_PATH = "resources/input13.txt";
	private static final int FIRST_PART_SIZE = 10;
	private final InputReader reader = new InputReader();
	private final LargeNumberAdder adder = new PartializedLongAdder();
	
	public static void main(String[] args) {
		new App().run();
	}
	
	public void run() {
		List<String> lines = reader.readLines(INPUT_PATH);
		String sum = adder.sum(lines);
		System.out.println(sum.substring(0, FIRST_PART_SIZE));
	}
}