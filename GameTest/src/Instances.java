
public class Instances {
	static int tile_X = 8, tile_Y = 8;
	public static Player player;
	public static Wall walls;
	
	public Instances() {
		player = new Player(tile_X, tile_Y);
		walls = new Wall(tile_X, tile_Y);
	}
}
