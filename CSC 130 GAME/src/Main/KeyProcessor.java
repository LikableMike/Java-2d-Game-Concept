/* This will handle the "Hot Key" system. */

package Main;


import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(60);
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')				return;
		boolean allow = true;
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
		
		case 'p':
			Main.Debug = true;
			break;
			
		case 'o':
			Main.Debug = false;
		//Right
		case 'd':
			Main.player.setDirection("Right");
			if(!(Main.player.BoundingBox.getX2() > Main.Collisions.get(3).getX1())) {
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
							if(Main.player.BoundingBox.getX1() + 15 > Main.Collisions.get(i).getX2() || 
								Main.player.BoundingBox.getY2() - 15 < Main.Collisions.get(i).getY1() ||
								Main.player.BoundingBox.getY1() + 15 > Main.Collisions.get(i).getY2())
								allow = true;
					}
				}
				if(allow) {
					Main.index++;
					Main.player.MoveX(15);
					Main.trigger = "'D' is being triggered.";
				}
			}else
				Main.index = 0;
			break;
		
		//Left
		case 'a':
			Main.player.setDirection("Left");
			if(!(Main.player.BoundingBox.getX1() < Main.Collisions.get(2).getX2())) {
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
							if(Main.player.BoundingBox.getX2() - 15 < Main.Collisions.get(i).getX1() || 
								Main.player.BoundingBox.getY2() - 15 < Main.Collisions.get(i).getY1() ||
								Main.player.BoundingBox.getY1() + 15 > Main.Collisions.get(i).getY2())
								allow = true;
					}
				}
				if(allow) {
					Main.index++;
					Main.player.MoveX(-15);
					Main.trigger = "'A' is being triggered.";
				}
			}else
				Main.index = 0;
			break;
			
		//Up
		case 'w':
			Main.player.setDirection("Up");
			if(!(Main.player.BoundingBox.getY1() < Main.Collisions.get(4).getY2())) {
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
							if(Main.player.BoundingBox.getY2() - 15 < Main.Collisions.get(i).getY1() ||
								Main.player.BoundingBox.getX1() + 15 > Main.Collisions.get(i).getX2() ||
								Main.player.BoundingBox.getX2() - 15 < Main.Collisions.get(i).getX1())
								allow = true;
					}
				}
				if(allow) {
					Main.index++;
					Main.player.MoveY(-15);
					Main.trigger = "'W' is being triggered.";
				}
			}else
				Main.index = 0;
			break;
		
		//Down
		case 's':
			Main.player.setDirection("Down");
			if(!(Main.player.BoundingBox.getY2() > Main.Collisions.get(5).getY1())) {
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
							if(Main.player.BoundingBox.getY1() + 15 > Main.Collisions.get(i).getY2() ||
								Main.player.BoundingBox.getX1() + 15 > Main.Collisions.get(i).getX2() ||
								Main.player.BoundingBox.getX2() - 15 < Main.Collisions.get(i).getX1())
								
								allow = true;
					}
				}
				if(allow) {
					Main.index++;
					Main.player.MoveY(15);
					Main.trigger = "'S' is being triggered.";
				}
			}else
				Main.index = 0;
			break;
			
			//Up-Right
		case 'D':
			Main.player.setDirection("Up");
			Main.index++;
			if(!(Main.player.BoundingBox.getX2() > Main.Collisions.get(3).getX1())) 
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
					}
				}
			else 
				allow = false;
			if(allow) {
				Main.player.MoveX(11);
			}
			if(!(Main.player.BoundingBox.getY1() < Main.Collisions.get(4).getY2()))
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
					}
				
				}
			else 
				allow = false;
			if(allow) {
				Main.player.MoveY(-10);
			}
			Main.trigger = "'D' and 'W' is being triggered.";
			break;
			//UP-LEFT
		case 'W':
			Main.player.setDirection("Up");
			Main.index++;
			if(!(Main.player.BoundingBox.getY1() < Main.Collisions.get(4).getY2())) 
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
					}
				}
			else 
				allow = false;
			if(allow) {
				Main.player.MoveY(-10);
			}
			if(!(Main.player.BoundingBox.getX1() < Main.Collisions.get(2).getX2()))
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
					}
				}
			else 
				allow = false;
			if(allow) {
				Main.player.MoveX(-11);
			}
			Main.trigger = "'W' and 'A' is being triggered.";
			break;
			//DOWN-LEFT
		case 'A':
			Main.player.setDirection("Down");
			Main.index++;
			if(!(Main.player.BoundingBox.getY2() > Main.Collisions.get(5).getY1()))
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
					}
				}
			else 
				allow = false;
			if(allow) {
				Main.player.MoveY(10);
			}
			if(!(Main.player.BoundingBox.getX1() < Main.Collisions.get(2).getX2()))
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
					}
				}
			else 
				allow = false;
			if(allow) {
				Main.player.MoveX(-11);
			}
			Main.trigger = "'A' and 'S' is being triggered.";
			break;
			//DOWN-RIGHT
		case 'S':
			Main.player.setDirection("Down");
			Main.index++;
			if(!(Main.player.BoundingBox.getY2() > Main.Collisions.get(5).getY1()))
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
					}
				}
			else 
				allow = false;
			if(allow) {
				Main.player.MoveY(10);
			}
			if(!(Main.player.BoundingBox.getX2() > Main.Collisions.get(3).getX1()))
				for(int i = 0; i < Main.Collisions.size(); i++) {
					if(!(Main.player.BoundingBox.getX2() < Main.Collisions.get(i).getX1() ||
						Main.player.BoundingBox.getX1() > Main.Collisions.get(i).getX2() ||
						Main.player.BoundingBox.getY2() < Main.Collisions.get(i).getY1() ||
						Main.player.BoundingBox.getY1() > Main.Collisions.get(i).getY2())) {
							allow = false;
					}
				}
			else 
				allow = false;
			
			if(allow) {
				Main.player.MoveX(11);
			}
			Main.trigger = "'D' and 'S' is being triggered.";
			break;
			
		case '$':
			Main.trigger = "Space-Bar is being triggered.";
			Main.clicked = true;
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;

		case ' ':
			Main.trigger = " ";
			Main.move = false;
		}
	}
}