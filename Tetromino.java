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
}
