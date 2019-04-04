package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Model.Playable;

public class Status extends JPanel {
	private Playable p;
	private int BAR_LENGTH = 60;
	private int BAR_WIDTH = 20;
	private int AVATAR_SIZE = 100;

    public Status() {
        this.setPreferredSize(new Dimension(450, 600));
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
    }
    
	public void paint(Graphics g) {
		super.paintComponent(g);
		// draw avatar
		g.drawString(p.getName() + " : " + p.getAge(), 150, 20);
        g.setColor(Color.BLUE);
        g.fillRect(150, 25, AVATAR_SIZE, AVATAR_SIZE);

		// bars 
        // Energy 
        g.setColor(Color.BLACK);
        g.drawString("Energy", 0, 200);
        g.setColor(Color.RED);
        g.fillRect(0, 200, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        int length_ok = (int) Math.round(BAR_LENGTH*p.getEnergy());
        g.fillRect(0, 200, length_ok, BAR_WIDTH);
        
        g.setColor(Color.BLACK);
        g.drawString("Hygiene", 0, 250);
        g.setColor(Color.RED);
        g.fillRect(0, 250, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        length_ok = (int) Math.round(BAR_LENGTH*p.getHygiene());
        g.fillRect(0, 250, length_ok, BAR_WIDTH);
        
        g.setColor(Color.BLACK);
        g.drawString("Bladder", 100, 200);
        g.setColor(Color.RED);
        g.fillRect(100, 200, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        length_ok = (int) Math.round(BAR_LENGTH*p.getBladder());
        g.fillRect(100, 200, length_ok, BAR_WIDTH);
        
        g.setColor(Color.BLACK);
        g.drawString("Social", 100, 250);
        g.setColor(Color.RED);
        g.fillRect(100, 250, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        length_ok = (int) Math.round(BAR_LENGTH*p.getSocial());
        g.fillRect(100, 250, length_ok, BAR_WIDTH);
        
        g.setColor(Color.BLACK);
        g.drawString("Fun", 200, 200);
        g.setColor(Color.RED);
        g.fillRect(200, 200, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        length_ok = (int) Math.round(BAR_LENGTH*p.getFun());
        g.fillRect(200, 200, length_ok, BAR_WIDTH);
        
        g.setColor(Color.BLACK);
        g.drawString("Hunger", 200, 250);
        g.setColor(Color.RED);
        g.fillRect(200, 250, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        length_ok = (int) Math.round(BAR_LENGTH*p.getHunger());
        g.fillRect(200, 250, length_ok, BAR_WIDTH);
    }

    public void redraw() {
        this.repaint();
    }

	public void setPlayer(Playable p2) {
		this.p = p2;
	}
}
