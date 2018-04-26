
public class Wall {
	public boolean[][] walls = null;
	public int tile_X, tile_Y;

	public Wall(int tile_X, int tile_Y) {
		walls = new boolean[tile_X][tile_Y];
		this.tile_X = tile_X;
		this.tile_Y = tile_Y;
	}
}
