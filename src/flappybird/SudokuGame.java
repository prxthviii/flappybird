package flappybird;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGame {
    private static final int GRID_SIZE = 5; // 5x5 Sudoku
    private static final int SUBGRID_SIZE = 2; // 2x2 subgrids
    private JFrame frame;
    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];
    private int[][] solution = {
            {1, 2, 3, 4, 5},
            {3, 4, 5, 1, 2},
            {5, 1, 2, 3, 4},
            {4, 5, 1, 2, 3},
            {2, 3, 4, 5, 1}
    }; // Predefined solution

    private int[][] puzzle = {
            {1, 0, 3, 0, 5},
            {0, 4, 0, 1, 0},
            {5, 0, 0, 3, 0},
            {0, 0, 1, 0, 3},
            {2, 0, 0, 5, 0}
    }; // Initial puzzle with some cells empty

    public SudokuGame() {
        frame = new JFrame("5x5 Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 18));
                if (puzzle[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(puzzle[row][col]));
                    cells[row][col].setEditable(false);
                    cells[row][col].setBackground(Color.LIGHT_GRAY);
                }
                panel.add(cells[row][col]);
            }
        }

        JButton validateButton = new JButton("Validate");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSolutionCorrect()) {
                    JOptionPane.showMessageDialog(frame, "Congratulations! The solution is correct!");
                } else {
                    JOptionPane.showMessageDialog(frame, "The solution is incorrect. Try again.");
                }
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(validateButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private boolean isSolutionCorrect() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                String text = cells[row][col].getText();
                if (text.isEmpty() || Integer.parseInt(text) != solution[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGame::new);
    }
}
