package Model;

import View.Window;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Playable> players = new ArrayList<Playable>();
    private Playable active_player = null;

    private Window window;
    private int size;
    // private int bombTimer = 3000;
    private int numberOfBreakableBlocks = 40;

    public Game(Window window) {
        this.window = window;
        size = window.getMapSize();
        // Creating one Player at position (1,1)
        Playable p = new Adult(10, 10, 3, "m", 19, "Hassan");
        objects.add(p);
        players.add(p);
        window.setPlayer(p);
        active_player = p;

        // Map building
        for (int i = 0; i < size; i++) {
            objects.add(new BlockUnbreakable(i, 0));
            objects.add(new BlockUnbreakable(0, i));
            objects.add(new BlockUnbreakable(i, size - 1));
            objects.add(new BlockUnbreakable(size - 1, i));
        }
        Random rand = new Random();
        for (int i = 0; i < numberOfBreakableBlocks; i++) {
            int x = rand.nextInt(size-4) + 2;
            int y = rand.nextInt(size-4) + 2;
            int lifepoints = rand.nextInt(5) + 1;
            Food block = new Food(x, y, lifepoints);
            block.attachDeletable(this);
            objects.add(block);
        }
        Bed block = new Bed(8, 8);
        block.attachDeletable(this);
        objects.add(block);
        
        window.setGameObjects(this.getGameObjects());
        notifyView();
    }


    public void movePlayer(int x, int y) {
        int nextX = active_player.getPosX() + x;
        int nextY = active_player.getPosY() + y;

        boolean obstacle = false;
        for (GameObject object : objects) {
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
            }
            if (obstacle == true) {
                break;
            }
        }
        active_player.rotate(x, y);
        if (obstacle == false) {
            active_player.move(x, y);
        }
        notifyView();
    }

    public void tirePlayer() {
    	active_player.tire();
    	notifyView();
    }
    
    public void hungryPlayer() {
    	active_player.hungry();
    	notifyView();
    }
    public void action() {
        Activable aimedObject = null;
		for(GameObject object : objects){
			if(object.isAtPosition(active_player.getFrontX(),active_player.getFrontY())){
			    if(object instanceof Activable){
			    	aimedObject = (Activable) object;
			    	if (object instanceof ToEat){
			    		active_player.eat(((ToEat) object).getHungerPoints());
			    		active_player.tire();
			    	}
			    	if (object instanceof ToEnergy){
			    		active_player.sleep(((ToEnergy) object).getSleepPoints());
			    		active_player.hungry();
			    	}
			    }
			}
		}
		if(aimedObject != null){
            notifyView();
		}
        
    }

    private void notifyView() {
        window.update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

    @Override
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
        objects.remove(ps);
        if (loot != null) {
            objects.addAll(loot);
        }
        notifyView();
    }


    public void playerPos() {
        System.out.println(active_player.getPosX() + ":" + active_player.getPosY());
        
    }

	public void stop() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}


	public void sendPlayer(int x, int y) {
		Thread t = new Thread(new AStarThread(this, active_player, x,  y));
		t.start();
	}


}