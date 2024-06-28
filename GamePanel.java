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
        // 创建一个新的L形方块
        currentTetromino = new Tetromino(COLS / 2, 0, Shapes.L_SHAPE);
        startGame();
    }

    // 在 paintComponent 中添加代码绘制整个面板
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 绘制已经沉积的方块
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col]) {
                    g.setColor(Color.BLUE); // 可以选择不同的颜色
                    g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        // 绘制当前下落的方块
        if (currentTetromino != null) {
            g.setColor(Color.RED);
            for (int row = 0; row < currentTetromino.shape.length; row++) {
                for (int col = 0; col < currentTetromino.shape[row].length; col++) {
                    if (currentTetromino.shape[row][col]) {
                        g.fillRect((currentTetromino.getX() + col) * CELL_SIZE,
                                (currentTetromino.getY() + row) * CELL_SIZE,
                                CELL_SIZE, CELL_SIZE);
                    }
                }
            }
        }
    }

    private void updateGame() {
        if (currentTetromino.canMoveDown(board)) {
            // 如果可以继续下移，则移动方块
            currentTetromino.moveDown();
        } else {
            // 碰撞发生，固定方块并创建新的方块
            board[currentTetromino.getY()][currentTetromino.getX()] = true;
            currentTetromino = new Tetromino(COLS / 2, 0, Shapes.L_SHAPE);
        }
        if (!currentTetromino.canMoveDown(board)) {
            // 固定当前形状到面板
            for (int row = 0; row < currentTetromino.shape.length; row++) {
                for (int col = 0; col < currentTetromino.shape[row].length; col++) {
                    if (currentTetromino.shape[row][col]) {
                        board[currentTetromino.getY() + row][currentTetromino.getX() + col] = true;
                    }
                }
            }
            // 创建新的L形方块
            currentTetromino = new Tetromino(COLS / 2, 0, Shapes.L_SHAPE);
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
