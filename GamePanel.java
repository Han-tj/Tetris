import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;


class GamePanel extends JPanel {
    private static final int ROWS = 20;
    private static final int COLS = 10;
    private static final int CELL_SIZE = 30;
    private Tetromino currentTetromino;

    public GamePanel() {
        setPreferredSize(new Dimension(COLS * CELL_SIZE, ROWS * CELL_SIZE));
        currentTetromino = new Tetromino(5, 0);
        startGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        // 绘制方块
        g.setColor(Color.RED);
        g.fillRect(currentTetromino.getX() * CELL_SIZE,
                currentTetromino.getY() * CELL_SIZE,
                CELL_SIZE, CELL_SIZE);
    }

    private void startGame() {
        new Thread(() -> {
            while (true) {
                currentTetromino.moveDown();
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
