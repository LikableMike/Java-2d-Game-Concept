package Data;

public class spriteInfo {
	int x;
	int y;
	String tag;
	
	//Constructor
	
		public spriteInfo(Vector2D v2d, String tag) {
			this.x = v2d.getX();
			this.y = v2d.getY();
			this.tag = tag;
		}
		
		//Custom override constructor
		public spriteInfo(String tag) {
			this.x = 0;
			this.y = 0;
			this.tag = tag;
		}
		
		public String getTag() {
			return this.tag;
		}
		
		public Vector2D getCoords() {
			Vector2D coords = new Vector2D(this.x, this.y);
			return coords;
		}
		
		public void setTag(String newTag) {
			this.tag = newTag;
		}
		
		public void setCoords(int x, int y) {
			 this.x = x;
			 this.y = y;
		}
		
		public String toString() {
			String message = "[";
			message += (String.valueOf(this.x) + ", " + String.valueOf(this.y) + ", " + this.tag + "]");
			return message;
		}

}
