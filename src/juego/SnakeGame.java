package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import vista.FrameJuego;

public class SnakeGame {

    public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {  
                FrameJuego frame = new FrameJuego();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(new Dimension(450, 550));
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setTitle("Snake Game");
                frame.getContentPane().setBackground(Color.BLACK);
            }
        });
    }
    
}
