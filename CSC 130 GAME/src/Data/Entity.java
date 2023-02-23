package Data;

import java.util.ArrayList;

public class Entity {
	public ArrayList<spriteInfo> Right;
	public ArrayList<spriteInfo> Left;
	public ArrayList<spriteInfo> Up;
	public ArrayList<spriteInfo> Down;
	public spriteInfo Still;
	public Vector2D currentVec;
	public BoundingBox BoundingBox;
	public BoundingBox InteractArea;
	public String Direction;
	
	public Entity(ArrayList<spriteInfo> Right, ArrayList<spriteInfo> Left, ArrayList<spriteInfo> Up, ArrayList<spriteInfo> Down, Vector2D currentVec) {
		this.Right = Right;
		this.Left = Left;
		this.Up = Up;
		this.Down = Down;
		this.currentVec = currentVec;	
		this.BoundingBox = new BoundingBox(currentVec.getX(),currentVec.getX() + 128,currentVec.getY(),currentVec.getY() + 128);
		this.Direction = "Right";
		this.Still = null;
		this.InteractArea = null;
	}
	
	public Entity(spriteInfo Still, Vector2D currentVec) {
		this.Right = null;
		this.Left = null;
		this.Up = null;
		this.Down = null;
		this.currentVec = currentVec;
		this.Direction = null;
		this.Still = Still;
		this.BoundingBox = new BoundingBox(currentVec.getX(),currentVec.getX() + 128,currentVec.getY(),currentVec.getY() + 128);
		this.InteractArea = new BoundingBox(this.BoundingBox.X1 - 30, this.BoundingBox.X2 + 30, this.BoundingBox.Y1 - 30, this.BoundingBox.Y2 + 30);
	}
	
	public void setDirection(String Dir) {
		this.Direction = Dir;
	}
	
	public String getDirection() {
		return this.Direction;
	}
	public void MoveY(double Y) {
		int y = (int)Y;
		this.currentVec.adjustY(y);
		this.BoundingBox.Y1 += y;
		this.BoundingBox.Y2 += y;
	}
	
	public void MoveX(double X) {
		int x = (int)X;
		this.currentVec.adjustX(x);
		this.BoundingBox.X1 += x;
		this.BoundingBox.X2 += x;
	}
	public void MoveDiag(int X, int Y) {
		int x = (int)X;
		int y = (int)Y;
		this.currentVec.adjustY(x);
		this.BoundingBox.X1 += x;
		this.BoundingBox.X2 += x;
		this.currentVec.adjustY(y);
		this.BoundingBox.Y1 += y;
		this.BoundingBox.Y2 += y;
	}
}
