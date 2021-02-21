package exercise1;
import java.util.Collections;
import java.util.Arrays;

public class Mazegenerator {
	private int x;
	private int y;
	private int[][] maze;
	public Mazegenerator(int x,int y) {
		this.x = x;
		this.y = y;
		maze = new int[this.x][this.y];
		createrandomMaze(0,0);
	}
	public void displayMaze() {
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
			}
			System.out.println("+");
			for (int j = 0; j < x; j++) {
				System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
			}
			System.out.println("|");
		}
		for (int j = 0; j < x; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}
	
	private void createrandomMaze(int cx, int cy) {
		DIRECTION[] dirs = DIRECTION.values();
		Collections.shuffle(Arrays.asList(dirs));
		for (DIRECTION dir : dirs) {
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y) && (maze[nx][ny] == 0)) {
				maze[cx][cy] |= dir.bit;
				maze[nx][ny] |= dir.opposite.bit;
				createrandomMaze(nx, ny);
			}
		}
	}
	
	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}
 
	private enum DIRECTION {
		NORTH(1, 0, -1), SOUTH(2, 0, 1), EAST(4, 1, 0), WEST(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIRECTION opposite;
		static {
			NORTH.opposite = SOUTH;
			SOUTH.opposite = NORTH;
			EAST.opposite = WEST;
			WEST.opposite = EAST;
		}
 
		private DIRECTION(int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};
	public static void main(String[] args) {
		//? = if else
		int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 10;
		int y = args.length == 2 ? (Integer.parseInt(args[1])) : 10;
		Mazegenerator maze = new Mazegenerator(x, y);
		maze.displayMaze();
	}
 	
}
