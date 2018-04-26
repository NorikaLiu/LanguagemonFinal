import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw extends Canvas {
	private JFrame frame = null;
	private Canvas canvas = null;
	private BufferedImage map = null;

	BufferStrategy bufferStrategy;
	private final int tile_X = 8, tile_Y = 8, tile_R = 100, tile_C = 100;
	private final int tile_Total = tile_R * tile_C;
	private final int map_Size = tile_X * tile_Y * tile_Total;
	
	private final int WIDTH = 480;
	private final int HEIGHT = 320;

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

	}

	@Override
	public Dimension getPreferredSize() {
		return (map == null) ? new Dimension(this.WIDTH, this.HEIGHT) : new Dimension(map.getWidth(), map.getHeight());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (map != null) {
			int px = Instances.player.getX(), py = Instances.player.getY();
			int mx = map.getWidth(), my = map.getHeight();
			int x = 0, y = 0;
			//FIGURE OUT HOW TO SET THE SCREEN AND WHERE TO DRAW THE MAP
			if (mx + px <= 0) {
				x = 0;
			} else if (px + this.WIDTH/2 >= mx) {
				x = mx - this.WIDTH;
			} else x = px - this.WIDTH/2;
			if (my - py - this.HEIGHT < 0) {
				y = 0;
			}
			//DRAW MAP STARTING AT X AND Y COORDINATE ON CANVAS
			g.drawImage(map, x, y, this);
		}
	}

	public void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		paint(g);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	protected void render(Graphics2D g) {
		g.setColor(Color.pink);
		g.fillRect(Instances.player.getX(), Instances.player.getY(), 15, 15);
	}
}