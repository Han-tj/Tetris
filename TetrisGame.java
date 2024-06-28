import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class TetrisGame {
    private GameBoard board;
    private Tetromino currentTetromino;

    public TetrisGame() {
        board = new GameBoard();
        currentTetromino = new Tetromino(GamePanel.COLS / 2, 0, Shapes.L_SHAPE);
    }

    public void startGame() {
        while (true) {
            // 更新游戏状态
            updateGame();

            // 暂停一段时间
            try {
                Thread.sleep(10); // 暂停一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGame() {
        // 让方块下落
        currentTetromino.moveDown();

        // 渲染游戏面板...
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 600);
        frame.add(new GamePanel());
        frame.setVisible(true);
    }
}
