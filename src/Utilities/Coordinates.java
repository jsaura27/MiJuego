package Utilities;

public class Coordinates {

    //Coordinates
    public int x = 0;
    public int y = 0;

    //Constructor
    public Coordinates(char x, char y) {
        this.x = ((int) x) - ((int) 'a');
        this.y = ((int) y) - ((int) '1');
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
        x = 0;
        y = 0;
    }
    
    public char getX() {
    	return ((char) (((int) 'a') + x));
    }
    
    public char getY() {
    	return ((char) (((int) '1') + y));
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
    

    
}