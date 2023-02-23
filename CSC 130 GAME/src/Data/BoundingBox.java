package Data;

public class BoundingBox {
	int X1;
	int X2;
	int Y1;
	int Y2;
	
	public BoundingBox(int X1, int X2, int Y1, int Y2) {
		this.X1 = X1;
		this.X2 = X2;
		this.Y1 = Y1;
		this.Y2 = Y2;
	}
	
	public int getLeft() {
		return this.X1;
	}
	
	public int getRight() {
		return this.X2;
	}
	
	public int getTop() {
		return this.Y1;
	}
	
	public int getBottom() {
		return this.Y2;
	}
	
	public void adjLeft(int x) {
		this.X1 += x;
	}
	
	public void adjRight(int x) {
		this.X2 -= x;
	}
	
	public void adjTop(int x) {
		this.Y1 += x;
	}
	
	public void adjBott(int x) {
		this.Y2 -= x;
	}
	public void bump(Entity player, char dir, char Side) {
		
		double reverse = 1;
		double Bump = -.001;
		if(Side == 'L' || Side == 'U')
			reverse = -1;
		if(dir == 'X') {
			player.MoveX(-20 * reverse);
			for(int i = 0; i < 60; i++){
				
				player.MoveX((Bump * i * (i - 60) * reverse));
				
			}
		}
		else {
			player.MoveY(-20 * reverse);
			for(int i = 0; i < 60; i++){
				
				player.MoveY((Bump * i * (i - 60) * reverse));
				
			}
		}
	}
	
	public int getX1() {
		return this.X1;
	}
	
	public int getX2() {
		return this.X2;
	}
	
	public int getY1() {
		return this.Y1;
	}
	
	public int getY2() {
		return this.Y2;
	}
}
