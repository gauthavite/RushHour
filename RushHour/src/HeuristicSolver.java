import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HeuristicSolver {
	
	public static int h(Game game, int i) {
		if (i==1)
			return h1(game);
		if (i==2)
			return h2(game);
		if (i==3)
			return h3(game);
		return 0; //When the heuristic is equal to 0, the get a standard BFS
	}
	
	public static int h1(Game game) {
		int h = 0;
		Car redCar = game.cars[0];
		for (int x = redCar.x + redCar.length; x < game.size; x++) {
			if (game.grid[redCar.y - 1][redCar.x] != 0)
				h++;
		}
		return h;
	}
	
	// This second heuristic is similar to the first one 
	// but we also add the distance between the exit and the red car
	public static int h2(Game game) {
		int h = 0;
		Car redCar = game.cars[0];
		for (int x = redCar.x + redCar.length; x < game.size; x++) {
			if (game.grid[redCar.y - 1][redCar.x] != 0)
				h++;
		}
		return h + game.size - (redCar.x + redCar.length - 1);
	}
	
	// This new heuristic is similar to the second one but 
	// if there is a car on the way to the exit all the cars on the column are counted. 
	// If there is the same car on two different columns, it is only counted one time. 
	public static int h3(Game game) {

		Car redCar = game.cars[0];
		LinkedList<Integer> carsOnColumn = new LinkedList<Integer>(); 

		for (int x = redCar.x + redCar.length; x < game.size; x++) {
			if (game.grid[redCar.y - 1][redCar.x] != 0) {
				for (int y=0; y<game.size; y++) {
					if (y != 0 && !carsOnColumn.contains(y))
						carsOnColumn.add(y);
				}
			}
		}
		return carsOnColumn.size() + game.size - (redCar.x + redCar.length - 1);
	}


	public static int search(Game source, int i) throws OverlappingException {
		HashMap<Game, Integer> visited = new HashMap<Game, Integer>();

		visited.put(source, 0);

		// We need to provide a Comparator to the Priority Queue
		class HeuristicComparator implements Comparator<Game> {
			public int compare(Game x, Game y) {
				if (x.equals(y))
					return 0;
				if (h(x, i) + visited.get(x) > h(y, i) + visited.get(y))
					return 1;
				else
					return -1;
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

	public static void searchDraw(Game source, int i) throws OverlappingException {
		HashMap<Game, Integer> visited = new HashMap<Game, Integer>();
		HashMap<Game, Game> pointer = new HashMap<Game, Game>();

		visited.put(source, 0);

		// We need to provide a Comparator to the Priority Queue
		class HeuristicComparator implements Comparator<Game> {
			public int compare(Game x, Game y) {
				if (x.equals(y))
					return 0;
				if (h(x, i) + visited.get(x) > h(y, i) + visited.get(y))
					return 1;
				else
					return -1;
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
				System.out.println("Move n°" + i + " is");
			path.removeLast().draw();
			i++;
		}
	}
}
