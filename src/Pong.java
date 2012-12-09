
// Simple Pong game made in Java.
// Author: Ahmed Atieh
import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;

public class Pong implements KeyListener {
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private final int DELTA = 8;
	private final int PADDLE_WIDTH = 32;
	private final int PADDLE_HEIGHT = 128;
	private final int PUCK_RADIUS = 32;
	
	Graphics g;
	
	private int x1 = 20; // location of player A's paddle.
	private int y1 = 240;
	private int x2 = 600; // location of player B's paddle.
	private int y2 = 240;
	
	private double x = 60.0; // location of ball
	private double y = 140.0;
	private double vx = 2.0; // velocity of ball
    private double vy = 1.0;
	
    public Pong() { 
    	JFrame f = new JFrame();
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setTitle("Pong 1.0");
    	f.setResizable(false);
    	Canvas c = new Canvas();
    	c.setSize(640, 480);
    	f.add(c);
    	f.pack();
    	f.setVisible(true);
    	g = c.getGraphics();
    	f.addKeyListener(this);
    	draw();
    }

	public void keyPressed(KeyEvent e) { 
		if (e.getKeyCode() == KeyEvent.VK_UP)y2 = y2 - DELTA;
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)y2 = y2 + DELTA;
		else if (e.getKeyChar() == 'i')y1 = y1 - DELTA;
		else if (e.getKeyChar() == 'k')y1 = y1 + DELTA;
	}

	// These two are required by the compiler, but will not be used in the game.
	public void keyReleased(KeyEvent e) { }
	public void keyTyped(KeyEvent e) { }

	public void draw() { 
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.red);
		g.fillRect(x1, y1, PADDLE_WIDTH, PADDLE_HEIGHT);
		g.setColor(Color.green);
		g.fillRect(x2, y2, PADDLE_WIDTH, PADDLE_HEIGHT);
		g.setColor(Color.yellow);
		g.fillOval( (int)x, (int)y, PUCK_RADIUS, PUCK_RADIUS);
	}

	public boolean detectCollision() { 
		// Test for collision with the first paddle.
		if (y + vy > y1 &&
				y + vy < y1 + PADDLE_HEIGHT &&
				x + vx < x1 + PADDLE_WIDTH &&
				x + vx > x1)
		{
			return true;
		}
		
		// Test for collision with second paddle.
		else if (y + vy > y2 &&
				y + vy < y2 + PADDLE_HEIGHT &&
				x + vx + PUCK_RADIUS > x2 &&
				x + vx + PUCK_RADIUS < x2 + PADDLE_WIDTH)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void play() { 
		while (true){
			if (x + vx < 0 || x + vx > WIDTH || detectCollision()) vx = -vx;
			if (y + vy  < 0 || y + vy > HEIGHT) vy = -vy;
			
			x = x + vx;
			y = y + vy;
			draw();
			try {
				Thread.sleep(30);
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
	}

    public static void main(String args[]) {
    	Pong p = new Pong();
    	p.play();
	}

}
