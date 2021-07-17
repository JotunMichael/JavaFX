package com.mime.minefront.input;

import java.awt.Robot;

import com.mime.minefront.Display;
import com.mime.minefront.RunGame;
import com.mime.minefront.RunnableTest;
import com.mime.minefront.ThreadCheck;
import com.mime.minefront.ThreadTest;
import com.mime.minefront.graphics.Render3D;
import com.mime.minefront.gui.Pause;

public class Controller {

	public static double x, y, z, rotation, xa, za, rotationa, rotationUpa, rotationUp=1
			, distanceTravel=0.0, distanceTravelX=0.0, distanceTravelY=0.0;
	//public BigDecimal y = new BigDecimal("0.0");
	public static boolean turnLeftM = false, turnRightM = false, jumped=false
			,jumpAgain=true, turnUpM=false, turnDownM=false, not_paused = true;
	public static long timeJ, timeJ1;
	Robot robot;
	public static boolean walk = false;
	public static boolean crouchWalk = false;
	public static boolean runWalk = false;
	public static boolean jumpedStop = false;
	public static Pause Pause_Menu;
	ThreadCheck threadCheck = new ThreadCheck();;
	boolean jumpIf=false;
	boolean[] key;
	InputHandler input = new InputHandler();
	//Display display = new Display();
	
//	public static void wait(int ms)
//	{
//	    try
//	    {
//	        Thread.sleep(ms);
//	    }
//	    catch(InterruptedException ex)
//	    {
//	        Thread.currentThread().interrupt();
//	    }
//	}
	
	public void tick(boolean forward,boolean back,boolean left
				,boolean right,boolean turnLeft,boolean turnRight
				,boolean jump, boolean crouch, boolean sprint, boolean regenH, boolean esc) {
		double rotationSpeed = 0.1;//myWay 0.1
		double rotationSpeed1 = 0.002 * Display.MouseSpeed;//theCherno ... calc and Y for correct but wont work good for X Y only for X
		double walkSpeed = 0.35;
		double jumpHeight = 0.4;
		double crouchHeight = 0.35;
		double xMove = 0;
		double zMove = 0;
		
		//if this NOT comment then Key left right wont work obviously need another var or literal int val
		if(Display.mouseSpeed < 3 && Display.mouseSpeed > 0 
				&& Display.mouseSpeed != 0)rotationSpeed *= 0.05;
		if(Display.mouseSpeed >= 3)rotationSpeed *= 0.5;
		if(Display.mouseSpeed < 0 && Display.mouseSpeed >= -3
				&& Display.mouseSpeed != 0)rotationSpeed *= 0.05;
		if(Display.mouseSpeed < -3)rotationSpeed *= 0.5;
		if(Display.mouseSpeed == 0)rotationSpeed *= 0.0;
		//System.out.println(Display.mouseSpeed);
//		boolean test = -10 < -1; //yeah don't laugh :|
//		System.out.println(test);
		
		if (esc ) {//&& !InputHandler.KeyPressedButton
			if(InputHandler.KeyPressedButton.compareAndSet(true, false)) {
			//System.out.println("pause game");
			//Pause pause = new Pause();
			//InputHandler.KeyPressedButton=true;
			//new Pause();
			//Display.g.drawString("PAUSED", 250, 150);
			not_paused=false;
			//display.renderMenu();
			RunGame.getGameInstance().render();/************/ //this method for terminate use thread
			//Pause_Menu= new Pause();
			new Pause();//here is ok but above Graphics error because RunGame.getGameInstance().render() called afterwards
			}
			//if(Pause.getPauseInstance()!=null) {Pause.getPauseInstance().stopPauseMenu(); Pause.pausedThreadTry2=null;}

			//Pause.pausedThread = new Pause();
			//Pause.pausedThreadTry2 = new Pause();
//			synchronized(this) {
//			try {
//				RunGame.getGameInstance().wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
			//RunGame.getGameInstance().paused();
		}
		
		if (regenH) {
			//Thread thread = new Thread(new ThreadTest());
			//thread.start();
			//ThreadTest tt1 = new ThreadTest();
			//System.out.println(tt1.getName());
			//tt1.start();
			//System.out.println("Thread is "+Pause.statusThread.isAlive());
			//System.out.println(input.key);
			////RunnableTest r = new RunnableTest(input.key);
			////r.start();
			//try {r.join();} catch (InterruptedException e) {e.printStackTrace();}

//			//r.start();
//			Thread thread = new Thread(r);
//			thread.start();
//			try {thread.join();} catch (InterruptedException e) {e.printStackTrace();}
			//new Thread(r).start();
			
			//jumpIf = r.startedT(input.key);//r.key[0];
			 //jumpIf = r.keyp;//r.key[0];
			////jumpIf = r.keyp1;
			////jump=jumpIf;
			////System.out.println(r.keyp1);
			//System.out.println(r.startedT());
			
			//Thread threadCh = new Thread(new ThreadCheck().startedT(key));//threadCheck
//			Thread threadCh = new Thread(new ThreadCheck());//threadCheck
//			threadCh.start();
//			jumpIf = threadCheck.startedT(input.key);
//			ThreadCheck th = new ThreadCheck();
//			th.start();
//			jumpIf = th.startedT(input.key);
			//System.out.println(jumpIf);
		}
		
		if(forward && !Render3D.wallHit) {
			zMove++;
			walk = true;
			//distanceTravel++;
		}
		
		if(back ) {
			zMove--;
			walk = true;
			//distanceTravel--;
		}
		
		if(left) {
			xMove--;
			walk = true;
		}
		
		//if(right && !Render3D.wallHit && !Render3D.wallHitInverse) {
		if(right) {
			xMove++;
			walk = true;
		}
		
		if(turnLeft) {
//			if (InputHandler.MouseButton == 3) {
//				
//			} else {
//				rotationa -= rotationSpeed;
//			}
			rotationa -= 0.044;
		}
		
		if(turnRight) {
//		if (InputHandler.MouseButton == 3) {
//				//Debug only hold
//			} else {
//				rotationa += rotationSpeed;//rotationSpeed
//			}
		rotationa += 0.044;
		//System.out.println("right");
		}
		
		if(turnLeftM) {
			rotationa -= rotationSpeed;
		}
		
		if(turnRightM) {
			rotationa += rotationSpeed;
		}
		
		if(turnUpM) {
			rotationUpa += rotationSpeed;
		}
		
		if(turnDownM) {
			rotationUpa -= rotationSpeed;
		}
		
		if(crouch) {
			y -=crouchHeight;
			sprint = false;
			crouchWalk = true;
			walkSpeed = 0.1;
		}
		
		if(!crouch) {
			crouchWalk = false;
		}
		
		
		if (sprint) {
			walkSpeed = 1;
			walk = true;
			runWalk = true;
		}
		
		if (!sprint) {
			runWalk=false;
		}
		
		if (!forward && !back && !left &&  !right) {
			walk = false;
			//Render3D.wallHit=false;
		}
		
		
//		if(jump) {y += jumpHeight; RunnableTest.keyp1=false;
//		} else if (y <= 3.9 && y >=0.01){if(timeJ % 20 > 10 ) y -= jumpHeight;} 
//		else {y=0;}
		//if(jump) {y += jumpHeight; RunnableTest.keyp1=false;}
		//jump good but a little sudden
//		if(jump ) {//&& timeJ > 0
//			//=y += jumpHeight;
//			for (int i =0; i <= 2; i++)y+=jumpHeight;
//			jumpedStop = true;
//			//timeJ=0;
//		}
		if(!jump) {
			jumpedStop = false;
		}
		//System.out.println(timeJ);
		//one jump but fast
		if(jump) {
//			y += jumpHeight;//why till 3.5 when hold?

			if (jumped == false) {
				 //like counter FPS but not very precise in update "wise" like real FPS on update method per tick
				//if(timeJ % 100 == 0 )
				for(double i =0.0; i <= 3.5; i++) {//i+=0.001 //not 4 very fast and bad
//					for(double ii = 0.0; ii <= 1000000.0; ii+=0.1) {}
					//y += (int) Math.floor(i);
					y+=i;
					}
			jumped = true;
			}
		}/* else if (y <= 3.9 && y >=0.01){
			if(timeJ % 20 > 10 ) y -= jumpHeight;
		} else {
			y=0;
			jumped = false;
		}*/
//		
//		//smooth landing
//		if(jump) {
//			y += jumpHeight;
//		} else if (y <= 3.9 && y >=0.01){
//			if(timeJ % 20 > 10 ) y -= jumpHeight;
//		} else {
//			y=0;
//		}
		
//		//no smooth landing
//		if(jump) {
//			y += jumpHeight;
//		} else {
//			y=0;
//		}
		
//		if (jump && jumpAgain) {
//			if(jumped == false) {
//			y += jumpHeight;}
//			if(timeJ % 20 > 10 ) jumped = true;//not necessary
//			if (y >= 3.5) {
//				jumpAgain=false;
//				try {
//				robot = new Robot();
//			} catch (AWTException e) {
//				e.printStackTrace();
//			} robot.keyRelease(KeyEvent.VK_SPACE);
//			jumpAgain=true;
//			jump=false;
//			}
//		} else if (y <= 3.9 && y >=0.01){
//			if(timeJ % 20 > 10 ) y -= jumpHeight;
//		} else {
//			y=0;
//			jumpAgain = true;
//		}
		
		//System.out.println(System.currentTimeMillis()-timeJ);
		//if (jump && System.currentTimeMillis()-timeJ >1000) {
		//if (timeJ1 >2000) 
//		if (jump && jumpAgain || y <=3.4 && jumpAgain) {
//			if(jumped == false) {
//			y += jumpHeight;}
//			if(timeJ % 20 > 10 ) jumped = true;//not necessary
//			if (y >= 3.5) {
//				jumpAgain=false;
//				try {
//				robot = new Robot();
//			} catch (AWTException e) {
//				e.printStackTrace();
//			} robot.keyRelease(KeyEvent.VK_SPACE);
//			jumpAgain=true;
//			jump=false;
//			}
//		} else if (y <= 3.9 && y >=0.01){
//			if(timeJ % 20 > 10 ) y -= jumpHeight;
//		} else {
//			y=0;
//			jumpAgain = true;
//		}
		//System.out.println(y);
		//System.out.println(timeJ);
		//System.out.println(y);
//		if (jump) {
//			if (jumped == false) {//!jumped
//			y += jumpHeight;
//			jumped = true;
//			}
//		}else {
//			if (y >= 3.14) {
//			y -= jumpHeight;
//			}
//			if (y<0.1) {
//				y=0;
//				jumped = false;
//			}
//		}
		
		
		//distanceTravel = xa + za * 0.01;
		//distanceTravel=xMove * Math.sin(rotation);	
		distanceTravelX = x;
		distanceTravelY = z;
		
		//move left right
		xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
		//move forward back
		za += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;
		//System.out.println(rotation);
		
		y *=0.9;//max height can reach
		x += xa;
		z += za;
		xa *= 0.1;
		za *= 0.1;
		rotation += rotationa;
		rotationa *= 0.5;
//		if(Display.mouseSpeed < 5)rotationa *= 0.1;
//		if(Display.mouseSpeed > 5)rotationa *= 0.5;
		rotationUp += rotationUpa;
		rotationUpa *= 0.2;
		
		
	}
}
