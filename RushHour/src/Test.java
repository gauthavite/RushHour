import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException, OverlappingException {
//		Game game = new Game("RushHour1.txt");
//
//		long start = System.currentTimeMillis();
//		//BreadthFirst.bfsDraw(game);
//		HeuristicSolver.search(game);
//		long end = System.currentTimeMillis();
//		System.out.println("Solution found in " + (end - start) + " ms");

		long[][] res = new long[6][3];
		for (int i = 0; i < 6; i++) {
			Game game = new Game("RushHour" + (i + 1) + ".txt");

			// BFS
			long start = System.currentTimeMillis();
			BreadthFirst.bfs(game);
			long end = System.currentTimeMillis();
			res[i][0] = end - start;

			// First Heuristic
			start = System.currentTimeMillis();
			HeuristicSolver.search(game);
			end = System.currentTimeMillis();
			res[i][1] = end - start;

			// Second Heuristic
			start = System.currentTimeMillis();
			HeuristicSolver2.search(game);
			end = System.currentTimeMillis();
			res[i][2] = end - start;
		}
		System.out.println("\n_____SumUp_____\n");
		System.out.println(" | BFS | h 1 | h 2 | ");

		System.out.println("--------------------");

		for (long[] line : res) {
			for (long elem : line)
				System.out.print(" | " + elem);
			System.out.println(" | ");
			System.out.println("--------------------");
		}
	}
}
