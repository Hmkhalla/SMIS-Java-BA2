package Model;

public abstract class Playable extends People {
	
	protected int bladder;
    protected int social;
    protected int fun;
    
    public Playable(int x, int y, int maxBomb, String gender, int age, String name){
    	super(x, y, maxBomb, gender, age, name);
    	bladder = 100;
    	social = 100;
    	fun = 100;
    }
    
    public double getBladder() {
    	return bladder/100.0;
    }
    
    public double getSocial() {
    	return social/100.0;
    }
    
    public double getFun() {
    	return fun/100.0;
    }
    
	public void toBladder() {
		if (bladder > 10)
			bladder -= 10;
	}
	public void loneliness() {
		if (social > 10)
			social -= 10;
	}public void bore() {
		if (fun > 10)
			fun -= 10;
	}
}
