import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Draw extends Canvas {
	private JFrame frame = null;
	private Canvas canvas = null;
	private BufferedImage map = null;

	BufferStrategy bufferStrategy;
	private final int tile_X = 8, tile_Y = 8, tile_R = 100, tile_C = 100;
	private final int tile_Total = tile_R * tile_C;
	private final int map_Size = tile_X * tile_Y * tile_Total;
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	
	public static int view_x = 0, view_y = 0;
	
	private JLabel ground;

	public Draw() {
		//Load the first map
		try {
			map = ImageIO.read(new File("GameTest/TILED/MAPS/Hometown.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// Makes a new window, with the name " Basic game ".
		frame = new JFrame("Basic Game");
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		ground = new JLabel("Apple");
		ground.setFont(new Font("Serif", Font.PLAIN, 36));
		ground.setForeground(Color.MAGENTA);

//		frame.add(ground);

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		// this will make the frame not re-sizable
		frame.setResizable(false);
		frame.setVisible(true);
		// this will add the canvas to our frame
		panel.add(canvas);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		// This will make sure the canvas has focus, so that it can take input
		// from mouse/keyboard
		canvas.requestFocus();
		// this will set the background to black
		//canvas.setBackground(Color.black);
		// This will add our button handler to our program
		canvas.addKeyListener(new ButtonHandler());
		frame.add(ground);
		
	}

	@Override
	public Dimension getPreferredSize() {
		return (map == null) ? new Dimension(this.WIDTH, this.HEIGHT) : new Dimension(map.getWidth(), map.getHeight());
	}
	
	//DRAWS THE VIEW
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (map != null) {
			int px = Instances.player.getX(), py = Instances.player.getY();
			System.out.println("Player: " + px + " " + py);
			System.out.println("Draw: " + Draw.WIDTH + " " + Draw.HEIGHT);
			int mw = map.getWidth(), mh = map.getHeight();
			System.out.println("Map: " + mw + " " + mh);
			
			//Set x view draw from
			if (px <= Draw.WIDTH/2) {
				//Left side of screen
				Draw.view_x = 0;
			} else if (px >= mw - Draw.WIDTH/2) {
				//Right side of screen
				Draw.view_x = mw - Draw.WIDTH;
			} else {
				//Center of screen
				Draw.view_x = px - Draw.WIDTH;
			}
			//Set y view draw from
			if (py <= Draw.HEIGHT/2) {
				//Top of screen
				Draw.view_y = 0;
			} else if (py >= mh - Draw.HEIGHT/2) {
				//Bottom of screen
				Draw.view_y = mh - Draw.HEIGHT;
			} else {
				//Center of screen
				Draw.view_y = py - Draw.HEIGHT/2;
			}
			//DRAW MAP STARTING AT X AND Y COORDINATE ON CANVAS
			System.out.println("XY: " + view_x + " " + view_y);
			g.drawImage(map, view_x, view_y, this);
		}
	}
	
	//DRAWS EVERYTHING
	public void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		paint(g);
		draw_player_in_view(g);
		g.dispose();
		bufferStrategy.show();
	}
	
	//DRAWS THE PLAYER
	protected void draw_player_in_view(Graphics2D g) {
		g.setColor(Color.pink);
		int mw = map.getWidth(), mh = map.getHeight();
		int px = Instances.player.getX(), py = Instances.player.getY();
		int x = 0, y = 0;
		if (Draw.view_x == 0) {
			//Left side of screen
			x = px;
		} else if (Draw.view_x == mw - Draw.view_x) {
			//Right side of screen
			x = px;
		} else {
			//Center of screen
			x = view_x + this.WIDTH/2;
		}
		//Set y view draw from
		if (Draw.view_y == 0) {
			//Top of screen
			y = py;
		} else if (py > mh - this.HEIGHT/2) {
			//Bottom of screen
			y = py;
		} else {
			//Center of screen
			y = view_y + this.HEIGHT/2;
		}
		g.fillRect(x, y, 16, 16);
	}
}