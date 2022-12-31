class Car {
	final int carNumber;
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

	@Override
	public boolean equals(Object o) {
		Car that = (Car) o;
		return this.carNumber == that.carNumber && this.orientation.equals(that.orientation) 
				&& this.length == that.length && this.x == that.x && this.y == that.y;
	}
}