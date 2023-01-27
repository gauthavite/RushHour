import java.io.IOException;

public class OptimalityTest {
	public static void main(String[] args) throws IOException, OverlappingException {

		// loop for distance and number of vertex visited
		long[][] resVisit = new long[40][3];

		System.out.println("\nNumber of vertex visited\n");
		System.out.println("      File    |  BFS  |  h 1  |  h 2  |");
		System.out.println("---------------------------------------");

		for (int i = 0; i < 6; i++) {
			Game game = new Game("RushHour" + (i + 1) + ".txt");
			System.out.print("RushHour" + (i + 1) + ".txt");

			int output = 0; // 0 to display the distance found, 1 for the number of vertex visited during
							// the search

			// BFS
			resVisit[i][0] = BreadthFirst.bfsBis(game)[output];

			// First Heuristic
			resVisit[i][1] = HeuristicSolver.searchBis(game, 1)[output];

			// Second Heuristic
			resVisit[i][2] = HeuristicSolver.searchBis(game, 2)[output];

			for (long elem : resVisit[i]) {
				System.out.print(" | ");
				System.out.print(String.format("%5d%n", elem).replace(System.lineSeparator(), ""));
			}
			System.out.println(" | ");
			System.out.println("---------------------------------------");

		}

	}
}