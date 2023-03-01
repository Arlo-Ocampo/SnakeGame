
package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import logica.Celda;


public class Vista extends JComponent {
    
    private Celda[][] juegoSnake;
    
    public Vista(){ }

    public void agregarListenerFlechas(int keyCode, String Name, Action action){
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0), Name);
        getActionMap().put(Name, action);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        for (int i = 0; i<juegoSnake.length; i++){
            for (int j = 0; j < juegoSnake[i].length; j++) {
                if (juegoSnake[i][j].celdaSnake()) {
                    g2d.setColor(Color.GREEN);
                } else if (juegoSnake[i][j].celdaManzana()) {
                    g2d.setColor(Color.RED);
                }
                else{
                    g2d.setColor(Color.BLACK);
                }
                g2d.fill(juegoSnake[i][j].getCirculo());
            }
        }
        
        g2d.setColor(Color.RED);
        //+20 is just to put some space from the border
        g2d.drawRect(0+20, 0+20, 400, 400);
    }
    
    public void setCeldas(Celda[][] celdasTotales){
        this.juegoSnake = celdasTotales;
    }
    
    public Celda[][] getCeldas(){return juegoSnake;}
            
}