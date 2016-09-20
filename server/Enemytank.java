/**
 * @author Farnoud Faghihi Berenjgani
 */
package server;

import javax.swing.ImageIcon;

public class Enemytank implements Runnable {
	private short GenerateRandomDirection;	// random direction var for AI
	private int mm,nn;		// index of the tank on the map array
	private int whichtank;	// enemy tank identifier
	private int w = 0;		// iterator variable for tank's bullets
	private short direc;	// current direction of the tank
	Shoot[] myshoot = new Shoot[100];	// tank's bullet array
	
	// constructor initializes the variables
	public Enemytank(int m,int n,short d,int number){
		new Thread(this).start();
		mm=m;
		nn=n;
		direc=d;
		whichtank=number;
	}

	// runs the AI thread
	@Override
	public void run() {
		while(Mapeditor.map[mm/30][nn/30]==whichtank){
			GenerateRandomDirection=(short)((Math.random()*4));
				for(int i=0;i<4 && Mapeditor.map[mm/30][nn/30]==whichtank;i++){
					if(GenerateRandomDirection==2 && mm<=570 && mm>=0){
						if(direc!=2){
							if(whichtank==3)
								Mapeditor.enemytank1=new ImageIcon("1r.png");
							else if(whichtank==4)
								Mapeditor.enemytank2=new ImageIcon("2r.png");
							else if(whichtank==5)
								Mapeditor.enemytank3=new ImageIcon("3r.png");
							else if(whichtank==6)
								Mapeditor.enemytank4=new ImageIcon("4r.png");
							direc=2;
							try {
								Thread.sleep(500-110*Server.dificulty);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else if(Mapeditor.map[(mm/30)+1][nn/30]==-1){
								Mapeditor.map[mm/30][nn/30]=-1;
								mm+=30;
								Mapeditor.map[mm/30][nn/30]=whichtank;
								try {
									Thread.sleep(500-110*Server.dificulty);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}	
						}
					}
					else if(GenerateRandomDirection==0 && mm<=600 && mm>=30){
						if(direc!=0){
							if(whichtank==3)
								Mapeditor.enemytank1=new ImageIcon("1l.png");
							else if(whichtank==4)
								Mapeditor.enemytank2=new ImageIcon("2l.png");
							else if(whichtank==5)
								Mapeditor.enemytank3=new ImageIcon("3l.png");
							else if(whichtank==6)
								Mapeditor.enemytank4=new ImageIcon("4l.png");
							direc=0;
							try {
								Thread.sleep(500-110*Server.dificulty);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else if(Mapeditor.map[(mm/30)-1][nn/30]==-1){
								Mapeditor.map[mm/30][nn/30]=-1;
								mm-=30;
								Mapeditor.map[mm/30][nn/30]=whichtank;
								try {
									Thread.sleep(500-110*Server.dificulty);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}	
						}
					}
					else if(GenerateRandomDirection==3 && nn<=330 && nn>=0){
						if(direc!=3){
							if(whichtank==3)
								Mapeditor.enemytank1=new ImageIcon("1d.png");
							else if(whichtank==4)
								Mapeditor.enemytank2=new ImageIcon("2d.png");
							else if(whichtank==5)
								Mapeditor.enemytank3=new ImageIcon("3d.png");
							else if(whichtank==6)
								Mapeditor.enemytank4=new ImageIcon("4d.png");
							direc=3;
							try {
								Thread.sleep(500-110*Server.dificulty);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else if(Mapeditor.map[mm/30][(nn/30)+1]==-1){
								Mapeditor.map[mm/30][nn/30]=-1;
								nn+=30;
								Mapeditor.map[mm/30][nn/30]=whichtank;
								try {
									Thread.sleep(500-110*Server.dificulty);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}	
						}
					}
					else if(GenerateRandomDirection==1 && nn<=360 && nn>=30){
						if(direc!=1){
							if(whichtank==3)
								Mapeditor.enemytank1=new ImageIcon("1.png");
							else if(whichtank==4)
								Mapeditor.enemytank2=new ImageIcon("2.png");
							else if(whichtank==5)
								Mapeditor.enemytank3=new ImageIcon("3.png");
							else if(whichtank==6)
								Mapeditor.enemytank4=new ImageIcon("4.png");
							direc=1;
							try {
								Thread.sleep(500-110*Server.dificulty);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else if(Mapeditor.map[mm/30][(nn/30)-1]==-1){
								Mapeditor.map[mm/30][nn/30]=-1;
								nn-=30;
								Mapeditor.map[mm/30][nn/30]=whichtank;
								try {
									Thread.sleep(500-110*Server.dificulty);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}	
						}
					}
					if(i==3 || i==1 && Mapeditor.map[mm/30][nn/30]==whichtank){
						myshoot[w] = new Shoot(false, mm, nn, direc);
						w++;
						w%=100;
					}
				}
				
		}
	}
}
