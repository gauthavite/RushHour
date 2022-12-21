import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException, OverlappingException {

		/*
		 * for (int i = 0; i < 6; i++) { Game game = new Game("RushHour" + (i + 1) +
		 * ".txt");
		 * 
		 * // BFS long start = System.currentTimeMillis(); for (int iter = 0; iter < 10;
		 * iter++) BreadthFirst.bfs(game); long end = System.currentTimeMillis();
		 * res[i][0] = (end - start) / 10;
		 * 
		 * // First Heuristic start = System.currentTimeMillis(); for (int iter = 0;
		 * iter < 10; iter++) HeuristicSolver.search(game, 1); end =
		 * System.currentTimeMillis(); res[i][1] = (end - start) / 10;
		 * 
		 * // Second Heuristic start = System.currentTimeMillis(); for (int iter = 0;
		 * iter < 10; iter++) HeuristicSolver.search(game, 2); end =
		 * System.currentTimeMillis(); res[i][2] = (end - start) / 10;
		 * 
		 * }
		 */

		long[][] res = new long[40][4];

		System.out.println("\nSum up times (ms)\n");
		System.out.println("     File   | BFS | h 1 | h 2 | car |");

		System.out.println("-------------------------------------");

		for (int i = 0; i < 40; i++) {
			Game game;
			if (i < 9) {
				game = new Game("GameP0" + (i + 1) + ".txt");
				System.out.print("GameP0" + (i + 1) + ".txt");
			} else {
				game = new Game("GameP" + (i + 1) + ".txt");
				System.out.print("GameP" + (i + 1) + ".txt");
			}

			res[i][3] = game.nbVehicles;

			// BFS
			long start = System.currentTimeMillis();
			BreadthFirst.bfs(game);
			long end = System.currentTimeMillis();
			res[i][0] = (end - start);

			// First Heuristic
			start = System.currentTimeMillis();
			HeuristicSolver.search(game, 1);
			end = System.currentTimeMillis();
			res[i][1] = (end - start);

			// Second Heuristic
			start = System.currentTimeMillis();
			HeuristicSolver.search(game, 2);
			end = System.currentTimeMillis();
			res[i][2] = (end - start);

			for (long elem : res[i]) {
				System.out.print(" | ");
				System.out.print(String.format("%3d%n", elem).replace(System.lineSeparator(), ""));
			}
			System.out.println(" | ");
			System.out.println("-------------------------------------");

		}

//		System.out.println("\nSum up times (ms)\n");
//		System.out.println("     File     | BFS | h 1 | h 2 | car |");
//
//		System.out.println("---------------------------------------");
//
//		for (int i = 0; i < res.length; i++) {
//			System.out.print("RushHour" + (i + 1) + ".txt");
//
//			for (long elem : res[i]) {
//				System.out.print(" | ");
//				System.out.print(String.format("%3d%n", elem).replace(System.lineSeparator(), ""));
//			}
//		}

		long[][] performance = new long[14][2];

		for (int i = 0; i < 40; i++) {
			performance[(int) (res[i][3] - 1)][0] += res[i][0] - res[i][1];
			performance[(int) (res[i][3] - 1)][1] += res[i][0] - res[i][2];
		}
		
		for (int i = 0; i < 14; i++) {
			System.out.println("CAR n" + (i+1) + "  h1:" + performance[i][0] + " vs h2 :" + performance[i][1]);
		}
	}
}
