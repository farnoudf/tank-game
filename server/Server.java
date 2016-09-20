/**
 * @author Farnoud Faghihi Berenjgani
 */
package server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Server extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3586069719435333375L;
	Mapeditor me = new Mapeditor();
	int[][] mapp = new int[21][13];		// declaring the map array
	public static JPanel panel = new JPanel();
	public static int dificulty = 0;	// variable to hold the difficulty level
	private boolean inEditor = false;	// variable to check if we are in editor
	
	public Server() {
		super("Tank Game");		// giving title to the window
		
		// initializing the map
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 13; j++) {
				mapp[i][j] = -1;
			}
		
		setLayout(null);

		setSize(640, 480);		// setting the size of the window
		setResizable(false);	// fixing the size of the window
		setLocationRelativeTo(null); // making the window to appear in the center
		setDefaultCloseOperation(EXIT_ON_CLOSE); // exit when the window is closed

		
		panel.setSize(640, 480);	// setting the size of the panel
		panel.setLocation(0, 0);	// placing it on the window 
		panel.setBackground(Color.white);
		panel.setLayout(null);
		add(panel);					// adding it to the window

		// making an exit button
		JButton b = new JButton("Exit");
		b.setSize(100, 25);
		b.setLocation(getWidth() - 240, getHeight() - 90);
		
		// making a play button
		JButton bb = new JButton("Play");
		bb.setSize(100, 25);
		bb.setLocation(140, getHeight() - 90);
		
		// adding an action listener for 'exit'
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		// adding an action listener for 'play'
		bb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// making the map visible and adding it to the panel
				me.setVisible(true);
				me.p1.setVisible(true);
				Server.this.setFocusable(false);
				add(me);
				
				// setting up the interface for playing
				add(me.p1);
				add(me.p3);
				add(me.p4);
				add(me.p5);
				add(me.p6);
				add(me.p7);
				
				// add an instruction label to the bottom label
				me.p1.add(me.l1);
				
				// changing the focus to the new panel
				me.requestFocus(true);
				me.setFocusable(true);
				
				// disabling the mouse while playing the game
				me.removeMouseListener(me);
				me.removeMouseMotionListener(me);
				
				repaint();
				me.runthread();
				
				// hide the previous panel
				panel.setVisible(false);
			}
		});
		
		// adding the buttons to the panel
		panel.add(bb);
		panel.add(b);

		// making a slider to choose the difficulty of the game
		final JSlider s = new JSlider(JSlider.HORIZONTAL, 1, 3, 1);
		s.setPaintTicks(true);
		s.setSnapToTicks(true);
		s.setMajorTickSpacing(1);
		s.setSize(340, 25);
		s.setLocation(150, 340);
		panel.add(s);
		s.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
					dificulty=s.getValue();
			}
		});
		
		final JLabel l = new JLabel("Name:");
		l.setLocation(getWidth() / 8, 30);
		l.setSize(100, 50);
		panel.add(l);

		// making a textField for the user to enter name
		JTextField tf = new JTextField();
		tf.setSize(360, 30);
		tf.setLocation(140, 41);
		panel.add(tf);
		tf.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				l.setForeground(Color.black);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				l.setForeground(Color.red);
			}
		});

		// adding labels to the slider for clarity
		JLabel l1 = new JLabel("Easy");
		l1.setLocation(150, 350);
		l1.setSize(100, 50);
		panel.add(l1);
		JLabel l2 = new JLabel("Medium");
		l2.setLocation(295, 350);
		l2.setSize(100, 50);
		panel.add(l2);
		JLabel l3 = new JLabel("Hard");
		l3.setLocation(460, 350);
		l3.setSize(100, 50);
		panel.add(l3);
		
		// adding the background picture
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("pic1.png"));
		label.setLocation(0, 10);
		label.setSize(640, 408);
		panel.add(label);
		label.setVisible(true);

		// making a menubar and adding menu items to it
		JMenuBar menubar = new JMenuBar();
		JMenu game, mapeditor, play;
		JMenuItem pause, save, load, exit, easy, medium, hard, edit, /*back,*/ n;
		game = new JMenu("Game ");
		play = new JMenu("Play");
		//back = new JMenuItem("Menu  ");
		n = new JMenuItem("New");
		pause = new JMenuItem("Pause");
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		exit = new JMenuItem("Exit");
		easy = new JMenuItem("Easy");
		medium = new JMenuItem("Meduim   ");
		hard = new JMenuItem("Hard");
		mapeditor = new JMenu("Map Editor");
		edit = new JMenuItem("Editor");
		mapeditor.add(edit);
		//mapeditor.add(back);
		mapeditor.addSeparator();
		mapeditor.add(n);
		play.add(easy);
		play.add(medium);
		play.add(hard);
		game.add(play);
		game.add(pause);
		game.addSeparator();
		game.add(save);
		game.add(load);
		game.addSeparator();
		game.add(exit);
		menubar.add(game);
		menubar.add(mapeditor);
		setJMenuBar(menubar);
		pause.setForeground(Color.gray);
		save.setForeground(Color.gray);
		load.setForeground(Color.gray);
		
		// playing the game in case of choosing any difficulty level from the menu
		easy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel.isVisible() || inEditor) {
					inEditor = false;
					dificulty=0;
					me.setVisible(true);
					me.p1.setVisible(true);
					add(me);
					add(me.p1);
					add(me.p3);
					add(me.p4);
					add(me.p5);
					add(me.p6);
					add(me.p7);
					
					me.p1.add(me.l1);
					me.requestFocus(true);
					me.setFocusable(true);
					me.removeMouseListener(me);
					me.removeMouseMotionListener(me);
					repaint();
					me.runthread();
					panel.setVisible(false);
				}
			}
		});
		
		medium.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel.isVisible() || inEditor) {
					inEditor = false;
					dificulty=1;
					me.setVisible(true);
					me.p1.setVisible(true);
					add(me);
					add(me.p1);
					add(me.p3);
					add(me.p4);
					add(me.p5);
					add(me.p6);
					add(me.p7);
				
					me.p1.add(me.l1);
					me.requestFocus(true);
					me.setFocusable(true);
					me.removeMouseListener(me);
					me.removeMouseMotionListener(me);
					repaint();
					me.runthread();
					panel.setVisible(false);
				}
			}
		});
		
		hard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel.isVisible() || inEditor) {
					inEditor = false;
					dificulty=2;
					me.setVisible(true);
					me.p1.setVisible(true);
					add(me);
					add(me.p1);
					add(me.p3);
					add(me.p4);
					add(me.p5);
					add(me.p6);
					add(me.p7);
				
					me.p1.add(me.l1);
					me.requestFocus(true);
					me.setFocusable(true);
					me.removeMouseListener(me);
					me.removeMouseMotionListener(me);
					repaint();
					me.runthread();
					panel.setVisible(false);
				}
			}
		});

		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// editor takes us to the map editor
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel.isVisible()) {
					inEditor = true;
					panel.setVisible(false);
					me.setVisible(true);
					me.p1.setVisible(true);
					Server.this.setFocusable(false);
					add(me);
					add(me.p1);
					add(me.p3);
					add(me.p4);
					add(me.p5);
					add(me.p6);
					add(me.p7);
					repaint();
				}
			}
		});
		
		/*
		// menu takes us back to the main panel
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!panel.isVisible()) {
					me.setVisible(false);
					me.p1.setVisible(false);
					panel.setVisible(true);
					repaint();
				}
			}
		});
		*/
		
		// new menu item resets the map to default
		n.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!panel.isVisible()) {
					mapp = me.getmap();
					for (int i = 0; i < 21; i++) {
						System.out.println();
						for (int j = 0; j < 13; j++) {
							mapp[i][j] = -1;
						}
					}
					mapp[10][12] = 2;
					mapp[0][0] = 3;
					mapp[0][4] = 4;
					mapp[20][0] = 5;
					mapp[20][4] = 6;
					me.setmap(mapp);
					repaint();
				}
			}
		});
		setVisible(true);
	}

}
