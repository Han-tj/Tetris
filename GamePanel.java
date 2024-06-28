import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;


class GamePanel extends JPanel {
    public static final int ROWS = 20;
    public static final int COLS = 10;
    private static final int CELL_SIZE = 30;
    private Tetromino currentTetromino;

    private boolean[][] board = new boolean[ROWS][COLS];

    public GamePanel() {
        setPreferredSize(new Dimension(COLS * CELL_SIZE, ROWS * CELL_SIZE));
        currentTetromino = new Tetromino(5, 0);
        startGame();
    }

    // 在 paintComponent 中添加代码绘制整个面板
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景和固定的方块
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col]) {
                    g.setColor(Color.BLUE); // 已固定的方块颜色
                    g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
        // 绘制正在下落的方块
        g.setColor(Color.RED);
        g.fillRect(currentTetromino.getX() * CELL_SIZE,
                currentTetromino.getY() * CELL_SIZE,
                CELL_SIZE, CELL_SIZE);
    }

    private void updateGame() {
        if (currentTetromino.isAtBottom()) {
            // 将方块加入到面板中
            board[currentTetromino.getY()][currentTetromino.getX()] = true;
            // 创建新的方块
            currentTetromino = new Tetromino(COLS / 2, 0);
        } else {
            // 移动方块
            currentTetromino.moveDown();
        }
    }

    private void startGame() {
        new Thread(() -> {
            while (true) {
                updateGame();
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
