package Model;

import java.util.ArrayList;

public class Food extends Block implements Deletable, Activable, ToEat {

    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
    private int lifepoints = 0;
    public Food(int X, int Y, int lifepoints) {
        super(X, Y, 1);
        this.lifepoints = lifepoints; 
    }
    
    public void activate(){
        
    }


    public void crush(){
        notifyDeletableObserver();
    }
    // //////////////////////////////////////////////////////////////////////////////////////

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
	public int getHungerPoints() {
		if (lifepoints == 1){
            crush();
        }
        else {
            lifepoints--;
            this.color = lifepoints + 2; // pour éviter de retourner au gris
        }
		return 5;
	}

}
