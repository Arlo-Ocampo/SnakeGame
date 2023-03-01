
package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class PanelInferior extends JPanel {
         
    private final JLabel labelPuntaje;
    private final JLabel labelVidas;

    public PanelInferior() {
        setBackground(Color.BLACK);
        setLayout(new GridLayout(0, 2));
        
        labelPuntaje = new JLabel();
        labelPuntaje.setForeground(Color.white);
        setPuntajeLabel(0);
        labelPuntaje.setFont(new Font("Monospaced", Font.PLAIN, 18));
        labelPuntaje.setPreferredSize(new Dimension(100, 30));
        add(labelPuntaje);
        
        labelVidas = new JLabel("", SwingConstants.RIGHT);
        labelVidas.setForeground(Color.white);
        setVidasLabel(3);
        labelVidas.setFont(new Font("Monospaced", Font.PLAIN, 18));
        labelVidas.setPreferredSize(new Dimension(100, 30));
        add(labelVidas);
        
    }
    
    public void setPuntajeLabel(int puntaje){
    labelPuntaje.setText(" Puntaje: "+ puntaje);
    }
    
    public void setVidasLabel(int vidas){
    labelVidas.setText("Vidas: "+ vidas+" ");
    }
}
