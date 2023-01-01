import java.io.IOException;

public class VerifyOptimality {
	public static void verify() throws IOException, OverlappingException {
		for (int i = 0; i < 6; i++) {
			Game game = new Game("RushHour" + (i + 1) + ".txt");
			
			int bfs = BreadthFirst.bfs(game);
			int h1 = HeuristicSolver.search(game, 1);
			int h2 = HeuristicSolver.search(game, 2);
			int h3 = HeuristicSolver.search(game, 3);

			if (bfs != h1)
				System.out.println("Le BFS renvoie " + bfs + " alors que l'heuristique h1 renvoie " + h1
						+ " pour le fichier RushHour" + (i + 1) + ".txt");
			if (bfs != h2)
				System.out.println("Le BFS renvoie " + bfs + " alors que l'heuristique h2 renvoie " + h2
						+ " pour le fichier RushHour" + (i + 1) + ".txt");
			if (bfs != h3)
				System.out.println("Le BFS renvoie " + bfs + " alors que l'heuristique h3 renvoie " + h3
						+ " pour le fichier RushHour" + (i + 1) + ".txt");
		}
	}
}
