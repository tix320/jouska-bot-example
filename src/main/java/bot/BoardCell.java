
package bot;

class BoardCell {

	private final Point point;

	private final int player;

	private final int points;

	public BoardCell(Point point, int player, int points) {
		this.point = point;
		this.player = player;
		this.points = points;
	}

	public Point getPoint() {
		return point;
	}

	public int getPlayer() {
		return player;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return player + "-" + points;
	}
}
