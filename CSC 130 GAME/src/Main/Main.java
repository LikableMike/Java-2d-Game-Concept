package Main;

import java.awt.Color;
import java.util.ArrayList;
import logic.Control;
import timer.stopWatchX;
import java.util.Queue;
import java.util.StringTokenizer;

import Data.BoundingBox;
import Data.Entity;
import Data.HashMap;
import Data.Vector2D;
import java.util.LinkedList;
import Data.spriteInfo;
import FileIO.EZFileRead;


public class Main{
	// Fields (Static) below...

	public static Vector2D currentVec = new Vector2D(100,600);
	public static boolean isImageDrawn = false;
	public static stopWatchX timer = new stopWatchX(60);
	
	//Animation Frames for each direction
	public static ArrayList<spriteInfo> Right = new ArrayList<>();
	public static ArrayList<spriteInfo> Left = new ArrayList<>();
	public static ArrayList<spriteInfo> Up = new ArrayList<>();
	public static ArrayList<spriteInfo> Down = new ArrayList<>();
	
	public static ArrayList<Entity> walls = new ArrayList<>();
	public static HashMap map = new HashMap();
	public static int index = 0;
	public static int counter = 1;
	public static boolean move = false;
	public static Entity player = new Entity(Right, Left, Up, Down, currentVec);
	public static Entity Chest = new Entity(new spriteInfo("Chest"), new Vector2D(896, 476));
	public static Entity Rock = new Entity(new spriteInfo("Rock"), new Vector2D(1000, 200));
	public static boolean Debug = false;
	public static ArrayList<BoundingBox> Collisions = new ArrayList<>();
	public static ArrayList<BoundingBox> Interactables = new ArrayList<>();
	public static String trigger = " ";
	public static String interact = " ";
	public static boolean clicked = false;
	static boolean welcome = true;
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	//Sets things up before the game loops starts
	public static void start(){		
		EZFileRead ezr = new EZFileRead("Dialog.txt");
		String raw = "";
		String key = "";
		String value = "";
			
		player.BoundingBox.adjLeft(5);
		player.BoundingBox.adjRight(15);
		player.BoundingBox.adjTop(60);
		player.BoundingBox.adjBott(10);
		Rock.BoundingBox.adjLeft(20);
		Rock.BoundingBox.adjRight(20);
		Rock.BoundingBox.adjTop(45);
		Rock.BoundingBox.adjBott(10);
		Chest.BoundingBox.adjLeft(20);
		Chest.BoundingBox.adjRight(20);
		Chest.BoundingBox.adjTop(30);
		Chest.BoundingBox.adjBott(10);
		
		
		for(int i = 0; i < 5; i++) {
			raw = ezr.getNextLine();
			StringTokenizer st = new StringTokenizer(raw, "*");
			key = st.nextToken();
			value = st.nextToken();
			map.put(key, value);
		}
		
		for(int i = 0; i < 6; i++) {
			Right.add(new spriteInfo("Right" + String.valueOf(i + 1)));
		}
		for(int i = 0; i < 6; i++) {
			Left.add(new spriteInfo("Left" + String.valueOf(i + 1)));
		}
		for(int i = 0; i < 6; i++) {
			Up.add(new spriteInfo("Up" + String.valueOf(i + 1)));
		}
		for(int i = 0; i < 6; i++) {
			Down.add(new spriteInfo("Down" + String.valueOf(i + 1)));
		}
		
		spriteInfo wallLeft = new spriteInfo("Left");
		spriteInfo wallRight = new spriteInfo("Right");
		spriteInfo wallTop = new spriteInfo("Top");
		spriteInfo wallBottom = new spriteInfo("Bottom");
	
		walls.add(new Entity(wallLeft, new Vector2D(0, 0)));
		walls.add(new Entity(wallRight, new Vector2D(1870, 0)));
		walls.add(new Entity(wallTop, new Vector2D(0, 0)));
		walls.add(new Entity(wallBottom, new Vector2D(0, 1030)));
		walls.get(2).BoundingBox.adjBott(78);
		walls.get(0).BoundingBox.adjRight(80);
		
		Collisions.add(Rock.BoundingBox);
		Collisions.add(Chest.BoundingBox);
		Collisions.add(walls.get(0).BoundingBox);
		Collisions.add(walls.get(1).BoundingBox);
		Collisions.add(walls.get(2).BoundingBox);
		Collisions.add(walls.get(3).BoundingBox);
		Interactables.add(Rock.InteractArea);
		Interactables.add(Chest.InteractArea);
		
	}
	
	//Loops while the game is running
	public static void update(Control ctrl) {
		interact = " ";
		
		if(index > 5)
			index = 0;
		if(index < 0)
			index = 5;
		
		//Displays Level
		ctrl.addSpriteToFrontBuffer(0, 0, "Ground");
		ctrl.addSpriteToFrontBuffer(Rock.currentVec.getX(), Rock.currentVec.getY(), Rock.Still.getTag());
		ctrl.addSpriteToFrontBuffer(Chest.currentVec.getX(), Chest.currentVec.getY(), Chest.Still.getTag());
		ctrl.addSpriteToFrontBuffer(0, 0, walls.get(0).Still.getTag());
		ctrl.addSpriteToFrontBuffer(0, 0, walls.get(2).Still.getTag());
		ctrl.addSpriteToFrontBuffer(1870, 0, walls.get(1).Still.getTag());
		ctrl.addSpriteToFrontBuffer(0, 1030, walls.get(3).Still.getTag());
		
		//Displays welcome messages for a certain amount of time
		if(welcome)
			ctrl.drawString(player.currentVec.getX()- 50, player.currentVec.getY(), map.getValue("string1"), Color.CYAN);
		
		//Iterates through all interactable objects in the scene and determines if the character is close enough to interact with them
		for(int i = 0; i < Interactables.size(); i++) {
		if(!(player.BoundingBox.getX2() < Interactables.get(i).getX1() ||
				player.BoundingBox.getX1() > Interactables.get(i).getX2() ||
				player.BoundingBox.getY2() < Interactables.get(i).getY1() ||
				player.BoundingBox.getY1() > Interactables.get(i).getY2())) {	
				
			ctrl.drawString(Interactables.get(i).getX1() - 10, Interactables.get(i).getY1() + 30, "Press Space to interact", Color.CYAN);

				if(i == 0) {
					interact = "Rock";
				}
				else if(i == 1) {
					interact = "Chest";
				}
				
				if(clicked) {
					interactCheck(interact, ctrl);
				}
			}

		}
		
		if(interact == " ")
			clicked = false;
		
		//Displays character with their current direction
		switch(player.getDirection()) {
		case("Right"):
			ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), player.Right.get(index).getTag());		
			break;
		
		case("Left"):
			ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), player.Left.get(index).getTag());		
			break;
			
		case("Up"):
			ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), player.Up.get(index).getTag());		
			break;
			
		case("Down"):
			ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), player.Down.get(index).getTag());		
			break;
		}
		
		//Displays Data to screen while running
		if(Debug == true) {	
			ctrl.drawString(750, 400, trigger, Color.WHITE);
			ctrl.drawString(100, 250, "BB: " + String.valueOf(player.BoundingBox.getLeft()), Color.WHITE);
			ctrl.drawString(100, 200, "Timer: " + String.valueOf(timer.getElapsed()), Color.WHITE);
			ctrl.drawString(100, 100, "X: " + String.valueOf(player.currentVec.getX() + "\nY: " + String.valueOf(player.currentVec.getY())),Color.WHITE );
		}
				
		

		if(timer.isTimeUp()) {
				timer.resetWatch();		
				counter++;
				if(counter > 60)
					welcome = false;;			
		}
								
	}

//Checks what object the player is interacting with
static void interactCheck(String obj, Control ctrl) {
	switch(obj) {
	case("Rock"):
		rockInspect(ctrl);
		break;
		
	case("Chest"):
		chestInspect(ctrl);
		break;
		
	default:
		break;	
		
	}
	
}
static void chestInspect(Control ctrl) {
	ctrl.drawString(600, 800, Main.map.getValue("string2") , Color.CYAN);
}

static void rockInspect(Control ctrl) {
	ctrl.drawString(750, 800, Main.map.getValue("string3") , Color.CYAN);
}


}
