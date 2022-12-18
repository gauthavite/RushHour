import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Game {
	int size;
	int nbVehicles;
	Car[] cars;
	int[][] grid;

	public Game(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));

		this.size = Integer.parseInt(br.readLine());
		this.nbVehicles = Integer.parseInt(br.readLine());

		this.cars = new Car[nbVehicles];
		for (int i = 0; i < nbVehicles; i++) {
			this.cars[i] = new Car(br.readLine());
		}
		br.close();
	}

	public boolean initialize() {	
		// Initialize the game and verify its validity
		this.grid = new int[this.size][this.size];
		for (Car car : this.cars) {
			
			if (car.orientation.equals("v")) {
				for (int y = car.y; y < car.y + car.length; y++) {
					if (this.grid[y - 1][car.x - 1] != 0)
						return false;
					this.grid[y- 1][car.x- 1] = car.carNumber;
				}
			}
			else {
				for (int x = car.x; x < car.x + car.length; x++) {
					if (this.grid[car.y - 1][x - 1] != 0)
						return false;
					this.grid[car.y - 1][x - 1] = car.carNumber;
				}
			}

		}
		return true;
	}
	public void draw() {
		for (int[] line : this.grid) {
			for (int elem : line) 
				System.out.print(" | " + elem);
			System.out.println(" | ");
			for (int i=0; i<line.length; i++) 
				System.out.print("----");
			System.out.println("--");
		}

	}
}
