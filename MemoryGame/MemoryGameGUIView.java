import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

/**
 * A graphical user interface (GUI) implementation of the Memory Game view.
 * Displays the game board using Swing components and handles user input via buttons.
 */
public class MemoryGameGUIView extends JFrame implements MemoryGameView, ActionListener {
    private JPanel boardPanel;
    private JTextArea promptArea;
    private String latestCommand = "";

    /**
     * Constructs the GUI view for the Memory Game, setting up the frame,
     * layout, buttons, and board.
     */
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

    /**
     * Initializes the tile buttons on the board with default "?" text.
     */
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

    /**
     * Updates the visual board display based on the current state of the tiles.
     *
     * @param tiles the list of Tile objects representing the game state
     */
    @Override
    public void displayBoard(List<Tile> tiles) {
        for (int i = 0; i < tiles.size(); i++) {
            JButton button = (JButton) boardPanel.getComponent(i);
            String symbol = String.valueOf(tiles.get(i).getSymbol());
            button.setText(tiles.get(i).isFlipped() ? symbol : "?");
        }
    }

    /**
     * Retrieves the latest command entered by the user via button press.
     *
     * @return the latest command as a String (tile index, 'r', or 'q')
     */
    @Override
    public String prompt() {
        String command = latestCommand;
        latestCommand = ""; // Clear after reading
        return command;
    }

    /**
     * Displays a success message indicating the game is completed,
     * including the final score and elapsed time.
     *
     * @param playerScore    the final score achieved
     * @param secondsElapsed the total time taken in seconds
     */
    @Override
    public void successGameOver(int playerScore, int secondsElapsed) {
        JOptionPane.showMessageDialog(this, "Congratulations! You found all the matches.\nYour final score: " + playerScore + "\nTime elapsed: " + secondsElapsed + " seconds");
    }

    /**
     * Closes the GUI window and releases resources.
     */
    @Override
    public void close() {
        this.dispose();
    }

    /**
     * Displays a general message in the prompt area of the GUI.
     *
     * @param message the message to display
     */
    @Override
    public void displayMessage(String message) {
        promptArea.setText(message + "\n");
    }

    /**
     * Handles all button events and sets the latest command
     * based on the button pressed.
     *
     * @param e the ActionEvent triggered by a button press
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        latestCommand = e.getActionCommand();
        promptArea.setText("Command: " + latestCommand); // For debugging
    }
}
