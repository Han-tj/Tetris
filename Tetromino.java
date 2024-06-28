public class Tetromino {
    private int x, y; // 方块的位置
    boolean[][] shape;

    public Tetromino(int x, int y, boolean[][] shape) {
        this.x = x;
        this.y = y;
        this.shape = shape;
    }

    public void moveDown() {
        y++;
    }

    // 获取器和设置器方法...
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean isAtBottom() {
        return y >= GamePanel.ROWS - 1;
    }

    public boolean canMoveDown(boolean[][] board) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col]) {
                    int boardRow = y + row + 1;
                    int boardCol = x + col;
                    if (boardRow >= GamePanel.ROWS || board[boardRow][boardCol]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void moveLeft(boolean[][] board) {
        if (canMove(-1, board)) {
            x--;
        }
    }

    public void moveRight(boolean[][] board) {
        if (canMove(1, board)) {
            x++;
        }
    }

    private boolean canMove(int deltaX, boolean[][] board) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col]) {
                    int newCol = x + col + deltaX;
                    if (newCol < 0 || newCol >= GamePanel.COLS || board[y + row][newCol]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
