/**
 * @author Farnoud Faghihi Berenjgani
 */
package server;

public class Shoot implements Runnable {
	// index vars for the position of bullets on map
	private int x, y;
	
	private short direction;	// direction of the bullet
	private boolean mshot;		// whether it's player's shot or not

	// constructor initializes the variables
	public Shoot(boolean ms, int mm, int nn, short d) {
		new Thread(this).start();
		mshot = ms;
		direction = d;
		x = mm / 30;
		y = nn / 30;
	}

	// runs the bullet thread
	@Override
	public void run() {
		if (direction == 1 && y >= 1) {
			y--;
			while (y >= 0) {
				if (Mapeditor.map[x][y] == 1)
					return;
				if (Mapeditor.map[x][y] >= 3) {
					if (mshot)
						Mapeditor.map[x][y] = -1;
					return;
				}
				Mapeditor.map[x][y] = 0;
				y--;
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Mapeditor.map[x][y + 1] = -1;
			}
			Mapeditor.map[x][y + 1] = -1;
			mshot=false;
		} else if (direction == 3 && y <= 11) {
			y++;
			while (y <= 12) {
				if (Mapeditor.map[x][y] == 1)
					return;
				if (Mapeditor.map[x][y] >= 3) {
					if (mshot)
						Mapeditor.map[x][y] = -1;
					return;
				}
				Mapeditor.map[x][y] = 0;
				y++;
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Mapeditor.map[x][y - 1] = -1;
			}
			Mapeditor.map[x][y - 1] = -1;
			mshot=false;
		} else if (direction == 0 && x >= 1) {
			x--;
			while (x >= 0) {
				if (Mapeditor.map[x][y] == 1)
					return;
				if (Mapeditor.map[x][y] >= 3) {
					if (mshot)
						Mapeditor.map[x][y] = -1;
					return;
				}
				Mapeditor.map[x][y] = 0;
				x--;
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Mapeditor.map[x + 1][y] = -1;
			}
			Mapeditor.map[x + 1][y] = -1;
			mshot=false;
		} else if (direction == 2 && x <= 19) {
			x++;
			while (x <= 20) {
				if (Mapeditor.map[x][y] == 1)
					return;
				if (Mapeditor.map[x][y] >= 3) {
					if (mshot)
						Mapeditor.map[x][y] = -1;
					return;
				}
				Mapeditor.map[x][y] = 0;
				x++;
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Mapeditor.map[x - 1][y] = -1;
			}
			Mapeditor.map[x - 1][y] = -1;
			mshot=false;
		}
	}

}
