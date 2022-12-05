package main;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;

public class CountingSundays {

	public static void main(String[] args) {
		long result = Stream.iterate(LocalDate.of(1901, 1, 1), date -> date.plusDays(1))
			.takeWhile(date -> date.isBefore(LocalDate.of(2001, 1, 1)))
			.filter(date -> date.getDayOfMonth() == 1 && date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
			.count();
		System.out.println(result);
	}
}