class Car {
	final int carNumber; // potentially useless
	final String orientation;
	final int length;
	int x, y;

	public Car(String line) {
		String[] elements = line.split(" ");

		this.carNumber = Integer.parseInt(elements[0]);
		this.orientation = elements[1];
		this.length = Integer.parseInt(elements[2]);
		this.x = Integer.parseInt(elements[3]);
		this.y = Integer.parseInt(elements[4]);
	}
	
	public Car clone() {
		return new Car(this.carNumber + " " + this.orientation + " " + this.length + " " + this.x + " " + this.y);
	}
}