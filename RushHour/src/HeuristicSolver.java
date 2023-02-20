import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HeuristicSolver {

	public static int h(Game game, int i) {
		if (i == 1)
			return h1(game);
		if (i == 2)
			return h2(game);
		return 0; // When the heuristic is equal to 0, we get a standard BFS
	}

	public static int h1(Game game) {
		int h = 0;
		Car redCar = game.cars[0];
		for (int x = redCar.x + redCar.length; x <= game.size; x++) {
			if (game.grid[redCar.y - 1][x - 1] != 0)
				h++;
		}
		return h;
	}

	// This new heuristic is similar to the first one but it adds the minimum of the
	// number of vehicles blocking the counted vehicles in h1.
	public static int h2(Game game) {
		Car redCar = game.cars[0];

		LinkedList<Integer> carsOnTheExitWay = new LinkedList<Integer>();

		for (int x = redCar.x + redCar.length; x <= game.size; x++) {
			if (game.grid[redCar.y - 1][x - 1] != 0) {
				carsOnTheExitWay.add(game.grid[redCar.y - 1][x - 1]);
			}
		}

		LinkedList<Integer> carsCountedForHeuristic = new LinkedList<Integer>();
		carsCountedForHeuristic.addAll(carsOnTheExitWay);
		// System.out.println(carsCountedForHeuristic);

		for (Integer carNumber : carsOnTheExitWay) {
			Car blockingCar = game.cars[carNumber - 1];
			LinkedList<Integer> carsCountedAbove = new LinkedList<Integer>();
			LinkedList<Integer> carsCountedBelow = new LinkedList<Integer>();
			//We count the number of cars below the blocking car
			for (int y = blockingCar.y + blockingCar.length; y < Math.min(game.size + 1,
					blockingCar.y + 2 * blockingCar.length); y++) { 
				if (game.grid[y - 1][blockingCar.x - 1] != 0
						&& !carsCountedForHeuristic.contains(game.grid[y - 1][blockingCar.x - 1]))
					carsCountedBelow.add(game.grid[y - 1][blockingCar.x - 1]);
			}
			//We count the number of cars above the blocking car
			for (int y = blockingCar.y - 1; y > Math.max(0, blockingCar.y - 1 - blockingCar.length); y--) { 
				if (game.grid[y - 1][blockingCar.x - 1] != 0
						&& !carsCountedForHeuristic.contains(game.grid[y - 1][blockingCar.x - 1]))
					carsCountedAbove.add(game.grid[y - 1][blockingCar.x - 1]);
			}

			int above = carsCountedAbove.size();
			int below = carsCountedBelow.size();

			if (blockingCar.length > redCar.y) // Then the car cannot move to the top because it is too big
				above = Integer.MAX_VALUE;

			if (below > above) {// There is more blocking cars below, thus we should move upward
				carsCountedForHeuristic.addAll(carsCountedAbove);
			} else {
				carsCountedForHeuristic.addAll(carsCountedBelow);
			}
		}
		return carsCountedForHeuristic.size();
	}

	public static int search(Game source, int i) throws OverlappingException {
		HashMap<Game, Integer> visited = new HashMap<Game, Integer>();

		visited.put(source, 0);

		// We need to provide a Comparator to the Priority Queue
		class HeuristicComparator implements Comparator<Game> {
			public int compare(Game x, Game y) {
				if (x.equals(y))
					return 0;
				if (h(x, i) + visited.get(x) < h(y, i) + visited.get(y))
					return -1;
				return 1;
			}
		}

		PriorityQueue<Game> q = new PriorityQueue<Game>(new HeuristicComparator());

		q.add(source);

		while (!q.isEmpty()) {
			Game g = q.poll();
			if (g.isFinished())
				return visited.get(g);

			int d = visited.get(g);

			for (Game next : g.possibleGrids()) {
				if (!visited.containsKey(next)) {
					visited.put(next, d + 1);
					q.add(next);
				}
			}
		}
		return -1;
	}

	//This function is used to return the number of visited node and the distance in a single array.
	//Its only purpose is to make performance tests easier.
	public static int[] searchBis(Game source, int i) throws OverlappingException {
		int[] res = new int[2];
		HashMap<Game, Integer> visited = new HashMap<Game, Integer>();

		visited.put(source, 0);

		// We need to provide a Comparator to the Priority Queue
		class HeuristicComparator implements Comparator<Game> {
			public int compare(Game x, Game y) {
				if (x.equals(y))
					return 0;
				if (h(x, i) + visited.get(x) < h(y, i) + visited.get(y))
					return -1;
				return 1;
			}
		}
		PriorityQueue<Game> q = new PriorityQueue<Game>(new HeuristicComparator());

		q.add(source);

		while (!q.isEmpty()) {
			Game g = q.poll();
			if (g.isFinished()) {
				res[0] = visited.get(g);
				res[1] = visited.size();
				return res;
			}

			int d = visited.get(g);

			for (Game next : g.possibleGrids()) {
				if (!visited.containsKey(next)) {
					visited.put(next, d + 1);
					q.add(next);
				}
			}
		}
		res[0] = -1;
		res[1] = -1;
		return res;
	}

	public static void searchDraw(Game source, int i) throws OverlappingException {
		HashMap<Game, Integer> visited = new HashMap<Game, Integer>();
		HashMap<Game, Game> pointer = new HashMap<Game, Game>();

		visited.put(source, 0);

		// We need to provide a Comparator to the Priority Queue
		class HeuristicComparator implements Comparator<Game> {
			public int compare(Game x, Game y) {
				if (x.equals(y))
					return 0;
				if (h(x, i) + visited.get(x) < h(y, i) + visited.get(y))
					return -1;
				return 1;
			}
		}
		PriorityQueue<Game> q = new PriorityQueue<Game>(new HeuristicComparator());

		q.add(source);
		pointer.put(source, null);

		while (!q.isEmpty()) {
			Game g = q.poll();
			if (g.isFinished()) {
				pathTo(g, pointer);
				return;
			}
			int d = visited.get(g);

			for (Game next : g.possibleGrids()) {
				if (!visited.containsKey(next)) {
					visited.put(next, d + 1);
					q.add(next);
					pointer.put(next, g);
				}
			}
		}
		System.out.println("The game has no solution.");
	}

	//prints the solution computed
	public static void pathTo(Game g, HashMap<Game, Game> pointer) {
		LinkedList<Game> path = new LinkedList<Game>();
		while (g != null) {
			path.addLast(g);
			g = pointer.get(g);
		}

		System.out.println("A solution to this game is the following :");
		int i = 0;
		while (path.size() > 0) {
			if (i > 0)
				System.out.println("Move " + i + " is");
			//System.out.println("The new heuristic h3 is equal to " + h2(path.getLast()));
			path.removeLast().draw();
			i++;
		}
	}
}
