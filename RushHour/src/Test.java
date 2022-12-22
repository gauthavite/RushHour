import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException, OverlappingException {

//		for (int i = 0; i < 6; i++) {
//			Game game = new Game("RushHour" + (i + 1) + ".txt");
//			System.out.println(BreadthFirst.bfs(game));
//			System.out.println(HeuristicSolver.search(game, 2));
//		}
		
		Game game = new Game("RushHour3.txt");
		System.out.println(HeuristicSolver.search(game, 2));
		
//		long[][] res = new long[6][3];
//		for (int i = 0; i < 6; i++) {
//			Game game = new Game("RushHour" + (i + 1) + ".txt");
//
//			// BFS
//			long start = System.currentTimeMillis();
//			for (int iter = 0; iter < 10; iter++)
//				BreadthFirst.bfs(game);
//			long end = System.currentTimeMillis();
//			res[i][0] = (end - start) / 10;
//
//
//			// First Heuristic
//			start = System.currentTimeMillis();
//			for (int iter = 0; iter < 10; iter++)
//				HeuristicSolver.search(game, 1);
//			end = System.currentTimeMillis();
//			res[i][1] = (end - start) / 10;
//
//			// Second Heuristic
//			start = System.currentTimeMillis();
//			for (int iter = 0; iter < 10; iter++)
//				HeuristicSolver.search(game, 2);
//			end = System.currentTimeMillis();
//			res[i][2] = (end - start) / 10;
//
//		}
//		System.out.println("\nSum up times (ms)\n");
//		System.out.println("     File     | BFS | h 1 | h 2 |");
//
//		System.out.println("---------------------------------");
//
//		for (int i = 0; i < res.length; i++) {
//			System.out.print("RushHour" + (i + 1) + ".txt");
//			
//			for (long elem : res[i]) {
//				System.out.print(" | ");
//				System.out.print(String.format("%3d%n", elem).replace(System.lineSeparator(), ""));
//			}
//			System.out.println(" | ");
//			System.out.println("---------------------------------");
//		}
	}
}
