import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MemoryGameGUIView extends JFrame implements MemoryGameView, ActionListener {
    private JPanel boardPanel;
    private JTextArea promptArea;
    private String latestCommand = "";

    public MemoryGameGUIView() {
        setTitle("Memory Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel(new GridLayout(2, 6)); // Assuming 12 tiles (2 rows of 6)
        promptArea = new JTextArea(3, 40);

        initializeBoard();

        JScrollPane scrollPane = new JScrollPane(promptArea);

        JButton restartButton = new JButton("Restart");
        restartButton.setActionCommand("r");
        restartButton.addActionListener(this);

        JButton quitButton = new JButton("Quit");
        quitButton.setActionCommand("q");
        quitButton.addActionListener(this);

        JPanel controlPanel = new JPanel();
        controlPanel.add(restartButton);
        controlPanel.add(quitButton);

        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(controlPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void initializeBoard() {
        boardPanel.removeAll();

        for (int i = 0; i < 12; i++) { // Fixed to 12 tiles
            JButton tileButton = new JButton("?");
            tileButton.setActionCommand(String.valueOf(i));
            tileButton.addActionListener(this);
            boardPanel.add(tileButton);
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }


    @Override
    public void displayBoard(List<Tile> tiles) {
        for (int i = 0; i < tiles.size(); i++) {
            JButton button = (JButton) boardPanel.getComponent(i);
            String symbol = String.valueOf(tiles.get(i).getSymbol());
            button.setText(tiles.get(i).isFlipped() ? symbol : "?");
        }
    }

    @Override
    public String prompt() {
        String command = latestCommand;
        latestCommand = ""; // Clear after reading
        return command;
    }

    @Override
    public void successGameOver(int playerScore, int secondsElapsed) {
        JOptionPane.showMessageDialog(this, "Congratulations! You found all the matches.\nYour final score: " + playerScore + "\nTime elapsed: " + secondsElapsed + " seconds");
    }

    @Override
    public void close() {
        this.dispose();
    }

    @Override
    public void displayMessage(String message) {
        promptArea.setText(message + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        latestCommand = e.getActionCommand();
        promptArea.setText("Command: " + latestCommand); // For debugging
    }
}


