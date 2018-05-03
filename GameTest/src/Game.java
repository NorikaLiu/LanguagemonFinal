public class Game implements Runnable {
	public void run() {
		//Initialize things
		Instances instances = new Instances();
		Draw drawing = new Draw();
		
		while (true) {
			Instances.player.update();
			drawing.render();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}