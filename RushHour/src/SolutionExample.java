import java.io.IOException;

public class SolutionExample {
	public static void main(String[] args) throws IOException, OverlappingException {
		Game game = new Game("RushHour1.txt");
		HeuristicSolver.searchDraw(game, 1);
	}
}
