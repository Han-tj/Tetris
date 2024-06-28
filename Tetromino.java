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
}
