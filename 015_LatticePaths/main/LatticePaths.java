package main;

public class LatticePaths {

	private static final int SIZE = 20;

	public static void main(String[] args) {
		new LatticePaths().run();
	}

	private void run () {
		System.out.println(calculateRoutes(SIZE, SIZE));
		System.out.println(calculateRoutesByMath(SIZE, SIZE));
	}

	private long calculateRoutes(int xSize, int ySize) {
		long[][] routeStore = getRouteStore(xSize, ySize);
		for (int x = 0; x < routeStore.length; ++x) {
			for (int y = 0; y < routeStore[x].length; ++y) {
				routeStore[x][y] = calculateRoutesForCoordinate(new Coordinate(x, y), routeStore);
			}
		}
		return routeStore[xSize][ySize];
	}

	private long[][] getRouteStore(int xSize, int ySize) {
		return new long[xSize + 1][ySize + 1];
	}

	private long calculateRoutesForCoordinate(Coordinate coord, long[][] routeStore) {
		long routesFromBelow = getRoutes(coord.subtractY(1), routeStore);
		long routesFromRight = getRoutes(coord.subtractX(1), routeStore);
		return sumRoutes(routesFromBelow, routesFromRight);
	}

	private long getRoutes(Coordinate coord, long[][] routeStore) {
		if (coord.isValid()) {
			return routeStore[coord.x][coord.y];
		}
		return 0;
	}

	private long sumRoutes(long routesFromBelow, long routesFromRight) {
		long sum = routesFromBelow + routesFromRight;
		return sum > 0 ? sum : 1;
	}

	/**
	 * Taking the x * y sized grid, the number of different routes
	 * are the related binomial coefficient C(x, y) of (a + b)^(x + y).
	 * The related member in the aggregated sum is C(x, y) * a^x * b^y,
	 * where C = (x + y)! / (x! * y!).
	 */
	private long calculateRoutesByMath(int xSize, int ySize) {
		int k = xSize;
		int n = xSize + ySize;
		return combineKFromN(k, n);
	}

	/**
	 * 9 -> 5
	 *
	 * 9 8 7 6 5 4 3 2 1
	 * 5 4 3 2 1
	 * 4 3 2 1
	 * 9 8 7 6 / 4 3 2 1
	 * @param k group size of the combination
	 * @param n amount of the different numbers to choose from
	 * @return the number of different combinations of K chosen from N
	 */
	private long combineKFromN(int k, int n) {
		long result = 1;
		int counterPart = 1;
		k = Integer.max(k, n - k);
		for (int i = n; i > k; --i) {
			result *= i;
			result /= counterPart++;
		}
		return result;
	}
	
	private record Coordinate(int x, int y) {

		private boolean isValid() {
			return x >= 0 && y >= 0;
		}

		private Coordinate subtractY(int yDiff) {
			return new Coordinate(x, y - yDiff);
		}

		private Coordinate subtractX(int xDiff) {
			return new Coordinate(x - xDiff, y);
		}
	}
}