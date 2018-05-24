public class Player {
	// variables which we will use
	private int x = Draw.HEIGHT, y = Draw.WIDTH, tile_X, tile_Y;
	private boolean left = false, right = false, up = false, down = false, space = false;
	private int walk_speed = 300, run_speed = 100;

	public Player(int tile_X, int tile_Y) {
		this.tile_X = tile_X;
		this.tile_Y = tile_Y;
		this.x = 100;
		this.y = 100;
		Draw.view_x = x - Draw.WIDTH/2;
		Draw.view_y = y - Draw.HEIGHT/2;
	}

	// These 4 functions are able to set the direction
	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setRun(boolean space) {
		this.space = space;
	}

	// This function will return X as an int.
	public int getX() {
		return x;
	}

	// And this function will return Y as an int.
	public int getY() {
		return y;
	}

	public void update() {
		move();
	}

	// This function will move the player according to its direction and speed
	public void move() {
		int sleep = (space) ? this.run_speed : this.walk_speed;
		if (left) {
			x -= tile_X;
		} else if (right) {
			x += tile_X;
		} else if (up) {
			y -= tile_Y;
		} else if (down) {
			y += tile_Y;
		}
		wait(sleep);
	}

	public void wait(int length) {
		long time = System.currentTimeMillis();
		long finish = time + length;
		while (true) {
			time = System.currentTimeMillis();
			if (time >= finish)
				break;
		}

	}
}

