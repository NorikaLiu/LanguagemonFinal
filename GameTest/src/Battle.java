import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;

public class Battle {
	
	private boolean press;
	
	final String title = "Test Window";
    final int width = 1200;
    final int height = width / 16 * 9;

	public Battle()
	{

	    //Creating the frame.
	    JFrame frame = new JFrame(title);

	    frame.setSize(width, height);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setVisible(true);

	    //Creating the canvas.
	    Canvas canvas = new Canvas();

	    canvas.setSize(width, height);
	    canvas.setBackground(Color.WHITE);
	    canvas.setVisible(true);
	    canvas.setFocusable(false);


	    //Putting it all together.
	    frame.add(canvas);

	    canvas.createBufferStrategy(3);

	    boolean running = true;

	    BufferStrategy bufferStrategy;
	    Graphics graphics;

	    while (running) {
	        bufferStrategy = canvas.getBufferStrategy();
	        graphics = bufferStrategy.getDrawGraphics();
	        graphics.clearRect(0, 0, width, height);

	        graphics.setColor(Color.BLACK);
	        graphics.drawString("Your first battle begins!", width/2, height/2);
	        graphics.drawString("Press space to begin", width/2, height-height/4);
	        bufferStrategy.show();
	    graphics.dispose();
	    }
	    
	    canvas.addKeyListener(new ButtonHandler());
	}
	public void keyPressed(KeyEvent SPACE) {
		final String title = "Test Window";
	    final int width = 1200;
	    final int height = width / 16 * 9;

	    //Creating the frame.
	    JFrame frame = new JFrame(title);

	    frame.setSize(width, height);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setVisible(true);

	    //Creating the canvas.
	    Canvas canvas = new Canvas();

	    canvas.setSize(width, height);
	    canvas.setBackground(Color.WHITE);
	    canvas.setVisible(true);
	    canvas.setFocusable(false);


	    //Putting it all together.
	    frame.add(canvas);

	    canvas.createBufferStrategy(3);

	    boolean running = true;

	    BufferStrategy bufferStrategy;
	    Graphics graphics;
 
		while (running) {
		        bufferStrategy = canvas.getBufferStrategy();
		        graphics = bufferStrategy.getDrawGraphics();
		        graphics.clearRect(0, 0, width, height);

		        graphics.setColor(Color.BLACK);
		        graphics.drawString("Select the option that is correct ", width/2, height/2);
		        graphics.drawString(" Ringo", width/2, height-height/4);
		        bufferStrategy.show();
		    graphics.dispose();
		    }
		}
			
		public void changeBack(boolean press) {
			press = true;
		}
		
				
		public void change() {
			if (press) {
				
				
			}
		}
		
		public static void main (String[] args) {
			Battle b = new Battle();
			b.change();
		}
	
}