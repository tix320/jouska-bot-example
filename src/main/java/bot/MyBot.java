package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Tigran Sargsyan on 09-May-20.
 */
public class MyBot {

	private static final String GAME_START_COMMAND = "START_GAME";
	private static final String GAME_END_COMMAND = "END_GAME";
	private static final String TURN_COMMAND = "TURN";

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintStream outputWriter = System.out;

		String line;
		int height = -1;
		int width = -1;
		while ((line = reader.readLine()) != null) {
			switch (line) {
				case GAME_START_COMMAND:
					log("Received GAME_START");
					String sizes = reader.readLine();
					String[] params = sizes.split(" ");
					height = Integer.parseInt(params[0]);
					width = Integer.parseInt(params[1]);
					break;
				case TURN_COMMAND:
					log("Received TURN_START");
					BoardCell[][] board = new BoardCell[height][width];
					for (int i = 0; i < height; i++) {
						String row = reader.readLine();
						String[] cells = row.split(" ");
						for (int j = 0; j < width; j++) {
							String cell = cells[j];
							String[] cellDescription = cell.split(",");
							int player = Integer.parseInt(cellDescription[0]);
							int points = Integer.parseInt(cellDescription[1]);
							board[i][j] = new BoardCell(new Point(i, j), player, points);
						}
					}

					List<Point> points = new ArrayList<>();
					for (BoardCell[] boardCells : board) {
						for (BoardCell boardCell : boardCells) {
							if (boardCell.getPlayer() == 1) {
								points.add(boardCell.getPoint());
							}
						}
					}

					log("Generating point...");

					Random random = new Random();
					int index = random.nextInt(points.size());
					Point point = points.get(index);
					outputWriter.println(point.toString());

					break;
				case GAME_END_COMMAND:
					height = -1;
					width = -1;
					break;
				default:
					System.err.println("Invalid command: " + line);
					break;
			}
		}
	}

	private static void log(String message) {
		System.err.println("INFO: " + message);
	}
}

