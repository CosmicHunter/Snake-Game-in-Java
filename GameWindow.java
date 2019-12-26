
import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private MainPanel panel;
    public GameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10,10,905,700);
        this.setBackground(Color.BLACK);
        panel = new MainPanel();
        this.add(panel);
    }

    public static void main(String [] args){
         GameWindow window = new GameWindow();
         window.setResizable(false);
         window.setVisible(true);

    }
}
