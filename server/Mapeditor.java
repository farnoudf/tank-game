/**
 * @author Farnoud Faghihi Berenjgani
 */
package server;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Mapeditor extends JPanel implements MouseListener,
		MouseMotionListener, KeyListener, Runnable {

	private static final long serialVersionUID = 5021356192884946524L;
	public static ImageIcon wall, road, mytank, firing, enemytank1, enemytank2,
			enemytank3, enemytank4;
	public static int[][] map = new int[21][13];	// declaring the map array
	Shoot[] myshoot = new Shoot[100]; // array shoot to represent player's bullets
	Enemytank[] enemyTank = new Enemytank[4];		// four enemy tanks
	
	// interface panels while playing the game or editing the map
	JPanel p1 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JPanel p7 = new JPanel();
	
	// instruction lables to help the player
	JLabel l1 = new JLabel("Press space to start the game");
	JLabel l2 = new JLabel("Press space to fire");

	// variable to check the mouse pointer is within the window
	public static boolean mousefinder = false;
	
	// variable for keyboard events to check if the game has started
	public static boolean startgame = false;
	
	// variable that identifies if the bullet is for the player or the AI
	public static boolean myshot = false;
	
	
	// variables to set limits on where the mouse can be effective within the map editor
	private int k = -100, l = -100;
	
	// variables to verify at least one enemy tank is remaining in the map
	private int x1 = 0, x2 = 0, x3 = 0, x4 = 0;
	
	// variables used for the position of tanks on the map (each (m, n) pair for one tank)
	public int m = 300, n = 360, m1 = 0, n1 = 0, m2 = 0,
			n2 = 120, m3 = 600, n3 = 0, m4 = 600, n4 = 120;
	
	public int z = 0; // variable to iterate through the player's shoot (bullet) array
	
	// varialbes used for the direction of the tanks
	public static short dir = 1, dir1 = 2, dir2 = 2, dir3 = 0, dir4 = 0;

	public boolean getstartgame() {
		return startgame;
	}

	public int[][] getmap() {
		return map;
	}

	public void setmap(int[][] m) {
		map = m;
	}

	public Mapeditor() {
		// initializing the imageIcons
		wall = new ImageIcon("wall1.png");
		road = new ImageIcon("road.png");
		mytank = new ImageIcon("5.png");
		firing = new ImageIcon("fire.png");
		enemytank1 = new ImageIcon("1r.png");
		enemytank2 = new ImageIcon("2r.png");
		enemytank3 = new ImageIcon("3l.png");
		enemytank4 = new ImageIcon("4l.png");

		// initializing the map
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 13; j++) {
				map[i][j] = -1;
			}
		}
		map[10][12] = 2;
		map[0][0] = 3;
		map[0][4] = 4;
		map[20][0] = 5;
		map[20][4] = 6;

		// placing the panels related to the interface
		p1.setSize(630, 35);
		p1.setLocation(5, 400);
		p1.setBackground(Color.cyan);
		p1.setVisible(true);

		p3.setSize(5, 480);
		p3.setLocation(0, 0);
		p3.setBackground(Color.lightGray);
		p3.setVisible(true);

		p4.setSize(5, 480);
		p4.setLocation(635, 0);
		p4.setBackground(Color.lightGray);
		p4.setVisible(true);

		p5.setSize(630, 5);
		p5.setLocation(5, 0);
		p5.setBackground(Color.lightGray);
		p5.setVisible(true);

		p6.setSize(630, 5);
		p6.setLocation(5, 395);
		p6.setBackground(Color.lightGray);
		p6.setVisible(true);

		p7.setSize(630, 5);
		p7.setLocation(5, 435);
		p7.setBackground(Color.lightGray);
		p7.setVisible(true);

		p1.setFocusable(false);
		p3.setFocusable(false);
		p4.setFocusable(false);
		p5.setFocusable(false);
		p6.setFocusable(false);
		p7.setFocusable(false);

		// shrink the main panel to make room for the interface panels
		setSize(630, 390);
		setLocation(5, 5);

		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);

		enableEvents(AWTEvent.KEY_EVENT_MASK);
	}
	
	public void runthread(){
		new Thread(this).start();
	}

	// function that updates the map depending on the map array
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 13; j++) {
				if (map[i][j] == 1) {
					g.drawImage(wall.getImage(), i * 30, j * 30, 30, 30, null);
				} else if (map[i][j] == -1) {
					g.drawImage(road.getImage(), i * 30, j * 30, 30, 30, null);
				} else if (map[i][j] == 2) {
					g.drawImage(mytank.getImage(), i * 30, j * 30, 30, 30, null);
				} else if (map[i][j] == 0) {
					g.drawImage(firing.getImage(), i * 30, j * 30, 30, 30, null);
				} else if (map[i][j] == 3) {
					g.drawImage(enemytank1.getImage(), i * 30, j * 30, 30, 30,
							null);
				} else if (map[i][j] == 4) {
					g.drawImage(enemytank2.getImage(), i * 30, j * 30, 30, 30,
							null);
				} else if (map[i][j] == 5) {
					g.drawImage(enemytank3.getImage(), i * 30, j * 30, 30, 30,
							null);
				} else if (map[i][j] == 6) {
					g.drawImage(enemytank4.getImage(), i * 30, j * 30, 30, 30,
							null);
				}
			}
	}

	// mouse events for the editor to edit the map
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() < 630 && e.getX() > 0 && e.getY() < 390 && e.getY() > 0) {
			if (map[(int) e.getX() / 30][(int) e.getY() / 30] == 1
					|| map[(int) e.getX() / 30][(int) e.getY() / 30] == -1)
				map[(int) e.getX() / 30][(int) e.getY() / 30] = -map[(int) e
						.getX() / 30][(int) e.getY() / 30];
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mousefinder = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mousefinder = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		k = l = -100;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if ((((int) e.getX() / 30) * 30 != ((int) k / 30) * 30 || ((int) e
				.getY() / 30) * 30 != ((int) l / 30) * 30) && mousefinder) {
			if (map[(int) e.getX() / 30][(int) e.getY() / 30] == 1
					|| map[(int) e.getX() / 30][(int) e.getY() / 30] == -1)
				map[(int) e.getX() / 30][(int) e.getY() / 30] = -map[(int) e
						.getX() / 30][(int) e.getY() / 30];
			k = e.getX();
			l = e.getY();
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	// keyboard event to control the tank
	@Override
	public void keyPressed(KeyEvent e) {
		// in case of up arrow key move the tank up
		if (e.getKeyCode() == KeyEvent.VK_UP && startgame && n <= 360
				&& n >= 30) {
			if (dir != 1) {
				mytank = new ImageIcon("5.png");
				dir = 1;
			} else if (map[m / 30][(n / 30) - 1] == -1)
				while (true) {
					map[m / 30][n / 30] = -1;
					n--;
					if ((n % 30) == 1) {
						n--;
						map[m / 30][n / 30] = 2;
						break;
					}
				}
		}

		// in case of down arrow key move the tank down
		if (e.getKeyCode() == KeyEvent.VK_DOWN && startgame && n <= 330
				&& n >= 0) {
			if (dir != 3) {
				mytank = new ImageIcon("5d.png");
				dir = 3;
			} else if (map[m / 30][(n / 30) + 1] == -1)
				while (true) {
					map[m / 30][n / 30] = -1;
					n++;
					if ((n % 30) == 29) {
						n++;
						map[m / 30][n / 30] = 2;
						break;
					}
				}
		}

		// in case of left arrow key move the tank left
		if (e.getKeyCode() == KeyEvent.VK_LEFT && startgame && m <= 600
				&& m >= 30) {
			if (dir != 0) {
				mytank = new ImageIcon("5l.png");
				dir = 0;
			} else if (map[(m / 30) - 1][n / 30] == -1)
				while (true) {
					map[m / 30][n / 30] = -1;
					m--;
					if ((m % 30) == 1) {
						m--;
						map[m / 30][n / 30] = 2;
						break;
					}
				}
		}

		// in case of right arrow key move the tank right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && startgame && m <= 570
				&& m >= 0) {
			if (dir != 2) {
				mytank = new ImageIcon("5r.png");
				dir = 2;
			} else if (map[(m / 30) + 1][n / 30] == -1){
				map[m / 30][n / 30] = -1;
					m+=30;
						map[m / 30][n / 30] = 2;
			}
		}

		// in case of space shoot a bullet
		if (e.getKeyCode() == KeyEvent.VK_SPACE && startgame) {
			myshot = true;
			myshoot[z] = new Shoot(myshot, m, n, dir);
			z++;
			z %= 100;
		}
	}

	// the first space key starts the game
	@Override
	public void keyReleased(KeyEvent e) {
		if (!startgame)
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				mytank = new ImageIcon("5.png");
				startgame = true;
				l1.setVisible(false);
				p1.remove(l1);
				p1.setBackground(Color.darkGray);
				l2.setForeground(Color.white);
				p1.add(l2);
				enemyTank[0] = new Enemytank(m1, n1, dir1, 3);
				enemyTank[1] = new Enemytank(m2, n2, dir2, 4);
				enemyTank[2] = new Enemytank(m3, n3, dir3, 5);
				enemyTank[3] = new Enemytank(m4, n4, dir4, 6);

			}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// update the map
			paintImmediately(0, 0, 630, 390);
			
			// check to see if the player is still alive
			if (map[m / 30][n / 30] != 2) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				paintImmediately(0, 0, 630, 390);
				JOptionPane.showMessageDialog(this, "Game over");
				break;
			}
			
			// check to see if the player has won
			for(int i=0;i<21;i++)
				for(int j=0;j<13;j++){
					if(map[i][j]!=3)
						x1++;
					if(map[i][j]!=4)
						x2++;
					if(map[i][j]!=5)
						x3++;
					if(map[i][j]!=6)
						x4++;
					
				}
			// and prompt the user with a win message!
			if(x1==13*21 && x2==13*21 && x3==21*13 && x4==21*13){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				paintImmediately(0, 0, 630, 390);
				JOptionPane.showMessageDialog(this, "You win");
				break;
			}
			
			x1=x2=x3=x4=0;
		}
		x1=x2=x3=x4=0;
		
		// when the game finishes set the values to default
		l1.setVisible(true);
		p1.add(l1);
		p1.setBackground(Color.cyan);
		p1.remove(l2);
		startgame=false;
		new Mapeditor();
		addMouseListener(this);
		addMouseMotionListener(this);
	
		p1.remove(l1);
		m=300;
		n=360;
		z=m1=n1=m2=n3=0;
		n2=n4=120;
		m3=m4=600;
		dir=1;
		dir1=dir2=2;
		dir3=dir4=0;
		setVisible(false);
		p1.setVisible(false);
		Server.panel.setVisible(true);
	}
}