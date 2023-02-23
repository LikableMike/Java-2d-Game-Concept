package Data;

public class HashMap {
	private final int M = 10;
	private HashChain[] map;
	private int n;
	
	public HashMap() {
		map = new HashChain[M];
		for(int i = 0; i < M; i++) {
			map[i] = new HashChain();
			
		}
		n = 0;
	}
	
	private int hash(String key) {
		final int R = 31;
		int h = 0;
		for(int i = 0; i < key.length(); i++) {
			h = ((R * h) + key.charAt(i)) % M;
		}
		return h;
	}
	
	public String getValue(String key) {
		int e = hash(key);
		return map[e].getValue(key);
	}
	
	public void put(String key, String value) {
		int e = hash(key);
		if(map[e].addNode(key,value)) {
			n++;
		}
	}
	
	public void delete(String key) {
		int e = hash(key);
		if(map[e].removeNode(key)) {
			n--;
		}
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	public void clear() {
		for(int i = 0; i < M; i++) {
			map[i].clear();
		}
		n = 0;
	}
	
	public String toString() {
		String ret = "";
		
		for(int i = 0; i < M; i++) {
			ret += map[i].toString() + "/n";
		}
		return ret;
	}
}
