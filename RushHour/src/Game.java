import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Game {
	final int size;
	final int nbVehicles;
	final Car[] cars;
	final int[][] grid;

	public Game(String path) throws IOException, OverlappingException {
		BufferedReader br = new BufferedReader(new FileReader(path));

		this.size = Integer.parseInt(br.readLine());
		this.nbVehicles = Integer.parseInt(br.readLine());

		this.cars = new Car[nbVehicles];
		for (int i = 0; i < nbVehicles; i++) {
			this.cars[i] = new Car(br.readLine());
		}
		br.close();

		this.grid = gridFromCars(this.cars);
	}
	
	public Game(int size, int nbVehicles, Car[] cars, int carNumber, int x, int y) throws OverlappingException {
		this.size = size;
		this.nbVehicles = nbVehicles;

		this.cars = new Car[nbVehicles];
		for (int i=0; i<nbVehicles; i++) {
			this.cars[i] = cars[i].clone(); //We need to make a deepcopy
		}
		this.cars[carNumber - 1].x = x;
		this.cars[carNumber - 1].y = y;
		
		this.grid = gridFromCars(this.cars);
	}

	public int[][] gridFromCars(Car[] cars) throws OverlappingException {
		int[][] g = new int[this.size][this.size];
		for (Car car : cars) {

			if (car.orientation.equals("v")) {
				for (int y = car.y; y < car.y + car.length; y++) {
					if (g[y - 1][car.x - 1] != 0)
						throw new OverlappingException();
					g[y - 1][car.x - 1] = car.carNumber;
				}
			} else {
				for (int x = car.x; x < car.x + car.length; x++) {
					if (g[car.y - 1][x - 1] != 0)
						throw new OverlappingException();
					g[car.y - 1][x - 1] = car.carNumber;
				}
			}
		}
		return g;
	}
	
	public void draw() {
		for (int i = 0; i < this.size; i++)
			System.out.print("----");
		System.out.println("--");

		for (int[] line : this.grid) {
			for (int elem : line)
				System.out.print(" | " + elem);
			System.out.println(" | ");
			for (int i = 0; i < line.length; i++)
				System.out.print("----");
			System.out.println("--");
		}
	}

	public LinkedList<Game> possibleGrids() throws OverlappingException {
		LinkedList<Game> games = new LinkedList<Game>();
		for (Car c : this.cars) {
			if (c.orientation.equals("v")) {

				// We test all the squares above the car
				int y = c.y - 1;
				while (y >= 1 && grid[y - 1][c.x - 1] == 0) {
					games.add(new Game(this.size, this.nbVehicles, this.cars, c.carNumber, c.x, y));
					y--;
				}

				// We test all the squares below the car
				y = c.y + c.length;
				while (y <= this.size && grid[y - 1][c.x - 1] == 0) {
					games.add(new Game(this.size, this.nbVehicles, this.cars, c.carNumber, c.x, y - c.length + 1));
					y++;
				}

			} else {
				// We test all the squares to the left of the car
				int x = c.x - 1;
				while (x >= 1 && grid[c.y - 1][x - 1] == 0) {
					games.add(new Game(this.size, this.nbVehicles, this.cars, c.carNumber, x, c.y));
					x--;
				}

				// We test all the squares to the right of the car
				x = c.x + c.length;
				while (x <= this.size && grid[c.y - 1][x - 1] == 0) {
					games.add(new Game(this.size, this.nbVehicles, this.cars, c.carNumber, x - c.length + 1, c.y));
					x++;
				}
			}

		}
		return games;
	}
	
	public boolean isFinished() {
		return this.cars[0].x + this.cars[0].length - 1 == this.size;
	}
	
	public int hashCode() {
		int sum = 0;
		for (Car c : this.cars) {
			sum += c.x * (5*c.x + 17*c.y) + 72*c.y;
		}
		return sum;
	}
	
	public boolean equals(Object o) {
		Game that = (Game)o;
		//These equalities imply the equality of the field 'cars'.
		return this.size == that.size && this.nbVehicles == that.nbVehicles && Arrays.deepEquals(this.grid, that.grid);
	}
}
