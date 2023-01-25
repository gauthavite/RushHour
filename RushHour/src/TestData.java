import java.io.IOException;

public class TestData {
	public static void main(String[] args) throws IOException, OverlappingException {

		//loop for execution time
		long[][] resTime = new long[40][4];

		System.out.println("\nSum up times (ms)\n");
		System.out.println("     File   | BFS | h 1 | h 2 | car |");
		System.out.println("--------------------------------------");

		for (int i = 0; i < 0; i++) {
			Game game;
			if (i < 9) {
				game = new Game("GameP0" + (i + 1) + ".txt");
				System.out.print("GameP0" + (i + 1) + ".txt");
			} else {
				game = new Game("GameP" + (i + 1) + ".txt");
				System.out.print("GameP" + (i + 1) + ".txt");
			}

			resTime[i][3] = game.nbVehicles;

			// BFS
			long start = System.currentTimeMillis();
			BreadthFirst.bfs(game);
			long end = System.currentTimeMillis();
			resTime[i][0] = (end - start);

			// First Heuristic
			start = System.currentTimeMillis();
			HeuristicSolver.search(game, 1);
			end = System.currentTimeMillis();
			resTime[i][1] = (end - start);

			// Second Heuristic
			start = System.currentTimeMillis();
			HeuristicSolver.search(game, 2);
			end = System.currentTimeMillis();
			resTime[i][2] = (end - start);

			for (long elem : resTime[i]) {
				System.out.print(" | ");
				System.out.print(String.format("%3d%n", elem).replace(System.lineSeparator(), ""));
			}
			System.out.println(" | ");
			System.out.println("---------------------------------------------");

		}
		
		
		//loop for distance and number of vertex visited
		long[][] resVisit = new long[40][4];

		System.out.println("\nNumber of vertex visited\n");
		System.out.println("     File   |  BFS  |  h 1  |  h 2  |  h 3  |");
		System.out.println("---------------------------------------------");

		for (int i = 0; i < 6; i++) {
			Game game;
			if (i < 6) {
				game = new Game("RushHour" + (i + 1) + ".txt");
				System.out.print("GameP0" + (i + 1) + ".txt");
			} else {
				game = new Game("GameP" + (i + 1) + ".txt");
				System.out.print("GameP" + (i + 1) + ".txt");
			}

			int output = 0; // 0 to display the distance found, 1 for the number of vertex visited during the search
			
			// BFS
			resVisit[i][0] = BreadthFirst.bfsBis(game)[output];

			// First Heuristic
			resVisit[i][1] = HeuristicSolver.searchBis(game, 1)[output];

			// Second Heuristic
			resVisit[i][2] = HeuristicSolver.searchBis(game, 2)[output];
			
			// Third Heuristic
			resVisit[i][3] = HeuristicSolver.searchBis(game, 3)[output];
			
			for (long elem : resVisit[i]) {
				System.out.print(" | ");
				System.out.print(String.format("%5d%n", elem).replace(System.lineSeparator(), ""));
			}
			System.out.println(" | ");
			System.out.println("---------------------------------------------");

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

		for (int i = 0; i < 0; i++) {
			performance[(int) (resTime[i][3] - 1)][0] += resTime[i][0] - resTime[i][1];
			performance[(int) (resTime[i][3] - 1)][1] += resTime[i][0] - resTime[i][2];
		}
		
//		for (int i = 0; i < 14; i++) {
//			System.out.println("CAR n" + (i+1) + "  h1:" + performance[i][0] + " vs h2 :" + performance[i][1]);
//		}
	}
}