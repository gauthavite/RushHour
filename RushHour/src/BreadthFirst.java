import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// An easy bruteforce solution is to use a BFS
public class BreadthFirst {
	public static int bfs(Game source) throws OverlappingException {
		HashMap<Game, Integer> visited = new HashMap<Game, Integer>();
		visited.put(source, 0);

		Queue<Game> q = new LinkedList<Game>();
		q.add(source);
		while (!q.isEmpty()) {
			Game g = q.poll();
			if (g.isFinished())
				return visited.get(g);
			int d = visited.get(g);

			for (Game next : g.possibleGrids()) {
				if (!visited.containsKey(next)) {
					q.add(next);
					visited.put(next, d + 1);
				}
			}
		}
		return -1;
	}

	public static void bfsDraw(Game source) throws OverlappingException {
		HashMap<Game, Integer> visited = new HashMap<Game, Integer>();
		HashMap<Game, Game> pointer = new HashMap<Game, Game>();

		visited.put(source, 0);

		Queue<Game> q = new LinkedList<Game>();
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
					q.add(next);
					visited.put(next, d + 1);
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
		while (path.size() > 0){
			if (i>0)
				System.out.println("Move n°" + i + " is");
			path.removeLast().draw();
			i++;
		}
	}

}
