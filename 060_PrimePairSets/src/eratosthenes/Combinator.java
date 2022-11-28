package eratosthenes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Combinator {

	private final List<List<Integer>> indexGroups = new ArrayList<>();
	private final int groupSize;
	private final int comboSize;
	
	/**
	 * 
	 * @param groupSize: size of the group of numbers
	 * @param comboSize: amount of numbers that can create one combination
	 */
	public Combinator(int groupSize, int comboSize) {
		this.groupSize = groupSize;
		this.comboSize = comboSize;
	}
	
	/**
	 * @return indexGroups: all the index-combinations that can be created from the indices of the group
	 */
	public List<List<Integer>> getCombinations() {
		List<Integer> first = IntStream.range(0, comboSize).boxed().toList();
		saveAllRemainingCombinations(first, 0);
		return indexGroups;
	}

	private void saveAllRemainingCombinations(List<Integer> prevCombo, int activeIndex) {
		if (activeIndex > 0 && prevCombo.get(activeIndex) <= prevCombo.get(activeIndex - 1)) {
			prevCombo.set(activeIndex, prevCombo.get(activeIndex - 1) + 1);
		}
		for (int i = prevCombo.get(activeIndex); i <= groupSize - comboSize + activeIndex; ++i) {
			List<Integer> currentCombo = new ArrayList<>(prevCombo);
			currentCombo.set(activeIndex, i);
			if (activeIndex == comboSize - 1) {
				indexGroups.add(currentCombo);
			} else {				
				saveAllRemainingCombinations(currentCombo, activeIndex + 1);
			}
		}
	}
	
	/**
	 * Example: 4-sized combinations of a group of 8
	 * 0 1 2 3
	 * 0 1 2 4
	 * 	...
	 * 0 1 2 7
	 * 0 1 3 4
	 * ...
	 * 0 1 3 7
	 * ...
	 * 0 1 6 7
	 * 0 2 3 4
	 * ...
	 * 0 5 6 7
	 * 1 5 6 7
	 * ...
	 * 3 4 5 6
	 * 3 4 5 7
	 * 3 4 6 7
	 * 3 5 6 7
	 * 4 5 6 7
	 * @param args
	 */
	public static void main(String[] args) {
		Combinator combinator = new Combinator(8, 4);
		List<List<Integer>> combinations = combinator.getCombinations();
		combinations.forEach(System.out::println);
	}
}