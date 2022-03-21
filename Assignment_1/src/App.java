import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The app is the main class for this application.
 * It is a Jframe that contains the board (JPanel)
 * and the buttons for the functionality of the
 * program
 */
public class App extends JFrame {

    private JTextField inputField;
    private Panel board;
    private final int size = 400;
    private int n = 4; //default tile size

    public App() {
        setupGui();
    }

    /**
     * Set up the gui
     * add the board (JPanel) and the buttons to
     * this frame
     */
    private void setupGui(){

        this.setSize(size + 50, size + 100);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tromino");

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton start = new JButton("Start");
        JButton random = new JButton("Random");
        this.inputField = new JTextField(10);
        this.inputField.setText("4");

        //use a Gridbaglayout to centre the internal panel
        JPanel centre = new JPanel(new GridBagLayout());
        //The default board
        board = new Panel(n, n, size / n, 3, 2);
        board.setPreferredSize(new Dimension(size, size));
        centre.add(board);
        this.add(centre, BorderLayout.CENTER);

        //Create a new random board
        random.addActionListener((e) -> {
            try {
                n = Integer.parseInt(inputField.getText());

                if(((n&(n-1)) == 0)){
                    JPanel centre1 = new JPanel(new GridBagLayout());
                    board = new Panel(n, n, size / n, (int)(Math.random() * n), (int)(Math.random() * n));
                    board.setPreferredSize(new Dimension(400, 400));
                    centre1.add(board);

                    this.add(centre1, BorderLayout.CENTER);
                    this.setVisible(true);
                } else {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException nfe){
                System.out.println("Invalid input: " + nfe);
            }
        });

        //Start the algorithm
        start.addActionListener((e) -> {
            if(this.board != null){
                this.board.run();
            }
        });

        inputPanel.add(new JLabel("Size"));
        inputPanel.add(this.inputField);
        inputPanel.add(random);
        inputPanel.add(start);

        this.add(inputPanel, BorderLayout.PAGE_START);
        this.setVisible(true);
    }

    public static void main(String[] args){
        new App();
    }
}
