import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException, OverlappingException {
		Game game = new Game("RushHour1.txt");

		BreadthFirst.bfsDraw(game);
	}
}
