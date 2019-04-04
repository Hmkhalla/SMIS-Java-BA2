package Model;

import java.util.ArrayList;

public class Bed extends Block implements Deletable, Activable, ToEnergy {
	private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
    private int sleepPoints;
    public Bed(int X, int Y) {
        super(X, Y, 6);
        this.sleepPoints = 7; 
    }
	@Override
	public void activate() {
		
	}

	@Override
	public void attachDeletable(DeletableObserver po) {
		observers.add(po);

	}

	@Override
	public void notifyDeletableObserver() {
        for (DeletableObserver o : observers) {
            o.delete(this, null);
        }
	}

	@Override
	public boolean isObstacle() {
		return true;
	}
	@Override
	public int getSleepPoints() {
		return sleepPoints;
	}

}
