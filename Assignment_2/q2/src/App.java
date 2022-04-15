import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App extends JFrame {

    private Panel panel;
    public App() {
        setupGui();
    }

    private void setupGui() {
        int size = 400;
        this.setSize(size + 50, size + 100);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Polygon");

        JButton find = new JButton("Find");
        panel = new Panel(new Polygon());

        this.add(panel, BorderLayout.CENTER);

        // Start the algorithm
        find.addActionListener((e) -> {
            if(this.panel != null){
                panel.compute();
            }
        });

        this.add(find, BorderLayout.PAGE_START);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
