package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by GDhaliwal on 2018-07-26.
 */

public class Main extends JFrame {
    static int frameX = 800;
    static int frameY = 830;
    static JFrame frame = new JFrame("Chrome Chess Board");
    static Game game = new Game();

    public static void main(String[] args){

        // window frame
        frame.setSize(frameX,frameY);
        frame.setBackground(Color.lightGray);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(game);
    }
}
