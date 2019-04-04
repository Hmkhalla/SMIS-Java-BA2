package Model;

public abstract class People extends GameObject implements Directable {
	
	protected String name;
    protected int age;
    protected String gender;
    protected int hunger;
    protected int hygiene;
    protected int energy;
    int direction = EAST;  
    
    public People(int x, int y, int maxBomb, String gender, int age, String name) {
        super(x, y, 2);
        this.age = age;
        this.gender = gender;
        this.name = name;
        hunger = 100;
        hygiene = 100;
        energy = 100;
        
    }
    
    
    public void move(int X, int Y) {
        this.posX = this.posX + X;
        this.posY = this.posY + Y;
    }

    public void rotate(int x, int y) {
        if(x == 0 && y == -1)
            direction = NORTH;
        else if(x == 0 && y == 1)
            direction = SOUTH;
        else if(x == 1 && y == 0)
            direction = EAST;
        else if(x == -1 && y == 0)
            direction = WEST;
    }

   // //////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public int getDirection() {
    return direction;
    }

    public int getFrontX() {
        int delta = 0;
        if (direction % 2 == 0){
            delta += 1 - direction;
        }
        return this.posX + delta;
    }

    public int getFrontY() {
        int delta = 0;
        if (direction % 2 != 0){
            delta += direction - 2;
        }
        return this.posY + delta;
    }
    
    public double getEnergy() {
    	return energy/100.0;
    }
    
    public void sleep(int point){
    	energy += point;
    	if (energy>100) {energy = 100;}
    }
    
    public double getHunger() {
    	return hunger/100.0;
    }
    
    public void eat(int point){
    	hunger += point;
    	if (hunger>100) {hunger = 100;}
    }
    
    public double getHygiene() {
    	return hygiene/100.0;
    }
    
    public String getName() {
    	return name;
    }
    
    public int getAge() {
    	return age;
    }
    
	public void tire() {
		if (energy > 10)
			energy -= 10;
	}
	public void hungry() {
		if (hunger > 10)
			hunger -= 10;
	}public void dirty() {
		if (hygiene > 10)
			hygiene -= 10;
	}
}