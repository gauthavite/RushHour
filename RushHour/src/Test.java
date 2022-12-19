import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException, OverlappingException {
		Game game = new Game("RushHour1.txt");

		long start = System.currentTimeMillis();
		//BreadthFirst.bfsDraw(game);
		HeuristicSolver.search(game);
		long end = System.currentTimeMillis();
		System.out.println("Solution found in " + (end - start) + " ms");
	}
}
