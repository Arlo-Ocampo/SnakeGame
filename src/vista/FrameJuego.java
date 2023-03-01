
package vista;

import controlador.ControladorVista;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import modelo.Modelo;


public class FrameJuego extends JFrame{
    
    private Modelo modelo;
    private ControladorVista controladorVista;
    private Vista vista;
    private PanelSuperior panelSuperior;
    private PanelInferior panelInferior;
    

    public FrameJuego() {
        
        vista = new Vista();
        add(vista, BorderLayout.CENTER);
        
        modelo = new Modelo();
       
        panelSuperior = new PanelSuperior();
        add(panelSuperior, BorderLayout.NORTH);
        
        panelInferior = new PanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
        
        controladorVista = new ControladorVista(modelo, vista, panelSuperior, panelInferior);
    }
}