public class GameBoard {
    private static final int ROWS = 20;
    private static final int COLS = 10;
    private boolean[][] board = new boolean[ROWS][COLS];

    // 初始化面板
    public GameBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = false;
            }
        }
    }

    // 其他方法...
}
