import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HeuristicSolver3 {
	//  This new heuristic similar to the first one but 
	//  if there is a car on the way to the exit all the cars on the column are counted. 
	public static int h(Game game) {

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

	public static void search(Game source) throws OverlappingException {
		HashMap<Game, Integer> visited = new HashMap<Game, Integer>();
		HashMap<Game, Game> pointer = new HashMap<Game, Game>();

		visited.put(source, 0);

		// We need to provide a Comparator to the Priority Queue
		class Compare implements Comparator<Game> {
			public int compare(Game x, Game y) {
				if (x.equals(y))
					return 0;
				if (HeuristicSolver.h(x) + visited.get(x) > HeuristicSolver.h(y) + visited.get(y))
					return 1;
				else
					return -1;
			}
		}
		PriorityQueue<Game> q = new PriorityQueue<Game>(1, new Compare());

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
