import java.io.IOException;

public class TestData {
	public static void main(String[] args) throws IOException, OverlappingException {

		long[][] resTime = new long[40][4];

		System.out.println("\nSum up times (ms)\n");
		System.out.println("     File   | BFS | h 1 | h 2 | car |");

		System.out.println("-------------------------------------");

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
			System.out.println("-------------------------------------");

		}
		
		long[][] resVisit = new long[40][3];

		System.out.println("\nNumber of vertex visited\n");
		System.out.println("     File   |  BFS  |  h 1  |  h 2  |");

		System.out.println("-------------------------------------");

		for (int i = 0; i < 6; i++) {
			Game game;
			if (i < 9) {
				game = new Game("RushHour" + (i + 1) + ".txt");
				System.out.print("GameP0" + (i + 1) + ".txt");
			} else {
				game = new Game("GameP" + (i + 1) + ".txt");
				System.out.print("GameP" + (i + 1) + ".txt");
			}

			int coord = 0;
			
			// BFS
			resVisit[i][0] = BreadthFirst.bfs(game)[coord];

			// First Heuristic
			resVisit[i][1] = HeuristicSolver.search(game, 1)[coord];

			// Second Heuristic
			resVisit[i][2] = HeuristicSolver.search(game, 2)[coord];

			for (long elem : resVisit[i]) {
				System.out.print(" | ");
				System.out.print(String.format("%5d%n", elem).replace(System.lineSeparator(), ""));
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

		for (int i = 0; i < 0; i++) {
			performance[(int) (resTime[i][3] - 1)][0] += resTime[i][0] - resTime[i][1];
			performance[(int) (resTime[i][3] - 1)][1] += resTime[i][0] - resTime[i][2];
		}
		
		for (int i = 0; i < 14; i++) {
			System.out.println("CAR n" + (i+1) + "  h1:" + performance[i][0] + " vs h2 :" + performance[i][1]);
		}
	}
}