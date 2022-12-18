import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		Game game = new Game("RushHour1.txt");
		
		System.out.println("The game is" + (game.initialize() ? " " : "not ") + "valid\n");
		
		game.draw();
	}
}
