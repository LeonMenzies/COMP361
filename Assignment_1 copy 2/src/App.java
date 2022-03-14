import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    private JTextField inputField;
    private Board board;
    private final int size = 400;
    private int n = 8; //default tile size

    public App() {
        setupGui();
    }

    private void setupGui(){

        this.setSize(size, size + 65);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tromino");

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton start = new JButton("Start");
        JButton random = new JButton("Random");
        this.inputField = new JTextField(10);
        this.inputField.setText("8");

        //Create a new board
        random.addActionListener((e) -> {
            try {
                n = Integer.parseInt(inputField.getText());

                if(((n&(n-1)) == 0)){
                    board = new Board(n, n, size / n);
                    this.add(board, BorderLayout.CENTER);
                    this.setVisible(true);
                } else {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException nfe){
                System.out.println("Invalid input: " + nfe);
            }
        });

        //Set a random missing square
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
