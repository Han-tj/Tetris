public class Tetromino {
    private int x, y; // 方块的位置

    public Tetromino(int startX, int startY) {
        x = startX;
        y = startY;
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
        int nextY = y + 1;
        if (nextY >= GamePanel.ROWS) return false; // 到达底部
        if (board[nextY][x]) return false; // 下面有方块
        return true;
    }
}
