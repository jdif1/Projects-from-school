/*
This is the main method for the program. A JFrame containing the JOGL JPanel is created and displayed.
The window is centered in the middle of the screen.
 */

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("JOGL");
        JOGL panel = new JOGL();
        window.setContentPane(panel);
        window.setResizable(false);
        window.pack();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screen.width - window.getWidth()) / 2, (screen.height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
