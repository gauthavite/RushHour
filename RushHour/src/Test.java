import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException, OverlappingException {
//		Game game = new Game("RushHour1.txt");
//
//		HeuristicSolver.searchDraw(game, 0);

		long[][] res = new long[6][4];
		for (int i = 0; i < 6; i++) {
			Game game = new Game("RushHour" + (i + 1) + ".txt");
			
			System.out.println("\nfile : " +(i+1));

			// BFS
			long start = System.currentTimeMillis();
			for (int iter = 0; iter < 10; iter++) {
				if (iter == 0) {
					System.out.println("BFS : d= " + BreadthFirst.bfs(game));
				} else {
					BreadthFirst.bfs(game);
				}
			}
			long end = System.currentTimeMillis();
			res[i][0] = (end - start) / 10;

			// First Heuristic
			start = System.currentTimeMillis();
			for (int iter = 0; iter < 10; iter++) {
				if (iter == 0) {
					System.out.println("h1 : d= " + HeuristicSolver.search(game, 1));
				} else {
					HeuristicSolver.search(game, 1);
				}
			}
			end = System.currentTimeMillis();
			res[i][1] = (end - start) / 10;

			// Second Heuristic
			start = System.currentTimeMillis();
			for (int iter = 0; iter < 10; iter++) {
				if (iter == 0) {
					System.out.println("h2 : d= " + HeuristicSolver.search(game, 2));
				} else {
					HeuristicSolver.search(game, 2);
				}
			}
			end = System.currentTimeMillis();
			res[i][2] = (end - start) / 10;

			// Third Heuristic
			start = System.currentTimeMillis();
			for (int iter = 0; iter < 10; iter++) {
				if (iter == 0) {
					System.out.println("h3 : d= " + HeuristicSolver.search(game, 3));
				} else {
					HeuristicSolver.search(game, 3);
				}
			}
			end = System.currentTimeMillis();
			res[i][3] = (end - start) / 10;

		}
		System.out.println("\nSum up times (ms)\n");
		System.out.println("     File     | BFS | h 1 | h 2 | h 3 |");

		System.out.println("---------------------------------------");

		for (int i = 0; i < res.length; i++) {
			System.out.print("RushHour" + (i + 1) + ".txt");

			for (long elem : res[i]) {
				System.out.print(" | ");
				System.out.print(String.format("%3d%n", elem).replace(System.lineSeparator(), ""));
			}
			System.out.println(" | ");
			System.out.println("---------------------------------------");
		}
	}
}
