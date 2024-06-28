import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        currentTetromino.moveLeft(board);
                        break;
                    case KeyEvent.VK_RIGHT:
                        currentTetromino.moveRight(board);
                        break;
                    case KeyEvent.VK_UP:
                        currentTetromino.rotate(board);
                        break;
                    case KeyEvent.VK_DOWN:
                        currentTetromino.moveDownQuickly(board);
                        break;
                }
                repaint();
            }
        });
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
    // 当前方块到达底部，固定方块
    if (currentTetromino.isAtBottom()) {
        fixTetromino();
    } else if (!currentTetromino.canMoveDown(board)) {
        // 当前方块无法继续下移，固定方块
        fixTetromino();
    } else {
        // 移动方块
        currentTetromino.moveDown();
    }

    // 检查游戏是否结束
    if (isGameOver()) {
        // 游戏结束逻辑
        JOptionPane.showMessageDialog(this, "游戏结束！");
        System.exit(0); // 或者设置一个标志来停止游戏循环
    }
}

    // 将当前的Tetromino固定到board上，并创建一个新的Tetromino
    private void fixTetromino() {
        for (int row = 0; row < currentTetromino.shape.length; row++) {
            for (int col = 0; col < currentTetromino.shape[row].length; col++) {
                if (currentTetromino.shape[row][col]) {
                    board[currentTetromino.getY() + row][currentTetromino.getX() + col] = true;
                }
            }
        }
        currentTetromino = new Tetromino(COLS / 2, 0, Shapes.L_SHAPE);
        checkAndClearRows(); // 检查并清除完整的行
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

    private boolean isGameOver() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col]) {
                return true; // 如果顶行有方块，游戏结束
            }
        }
        return false;
    }
    private void checkAndClearRows() {
        for (int row = 0; row < ROWS; row++) {
            if (isRowFull(row)) {
                clearRow(row);
            }
        }
    }

    private boolean isRowFull(int row) {
        for (int col = 0; col < COLS; col++) {
            if (!board[row][col]) {
                return false;
            }
        }
        return true;
    }

    private void clearRow(int row) {
        for (int r = row; r > 0; r--) {
            for (int col = 0; col < COLS; col++) {
                board[r][col] = board[r - 1][col];
            }
        }
        // 清除顶行
        for (int col = 0; col < COLS; col++) {
            board[0][col] = false;
        }
    }
}
