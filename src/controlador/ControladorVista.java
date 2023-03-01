
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import logica.Celda;
import modelo.Modelo;
import vista.PanelInferior;
import vista.PanelSuperior;
import vista.Vista;

public class ControladorVista {
    
    private final Modelo snakeModelo;
    private final Vista vista;
    private final PanelSuperior panelSuperior;
    private final PanelInferior panelInferior;
    
    private Timer timer = null;
    
    public ControladorVista(Modelo snakeModelo, Vista snakeView, PanelSuperior panelSuperior, PanelInferior panelInferior){
        this.snakeModelo = snakeModelo;
        this.vista = snakeView;
        this.panelSuperior = panelSuperior;
        this.panelInferior = panelInferior;
        
        actualizarVista();
        
        listenersPaneles();
    }
    
    private void actualizarVista(){  
        int posXinicial = 20;
        int posYinicial = 20;
        double anchoCelda = 20;
        
        int numFilas = snakeModelo.getAlturaCeldas();
        int numColumnas = snakeModelo.getAnchoCeldas();
        
        Celda[][] Celdas = new Celda[numFilas][numColumnas];
        
        for (int i = 0; i < Celdas.length; i++) {
                double actualPosY = (i * anchoCelda)+posYinicial;
            for (int j = 0; j < Celdas[i].length; j++) {
                double actualPosX = (j * anchoCelda)+posXinicial;
                
                int tipoCeldaActual = snakeModelo.getTipoCelda(i, j);
                
                Celdas[i][j] = new Celda(tipoCeldaActual);
                Celdas[i][j].setCirculo(actualPosX, actualPosY, anchoCelda, anchoCelda);
            }
        }
        vista.setCeldas(Celdas);
        vista.repaint();
        }
    
    private void darPaso(){
        
        snakeModelo.pasoSiguiente();
        panelInferior.setPuntajeLabel(snakeModelo.getPuntajeActual());
        panelInferior.setVidasLabel(snakeModelo.getVidasActuales());
        
        if (!snakeModelo.getModoJugando()) {
            detenerTimer();
            panelSuperior.actualizarPanelparaJugar(false);
        }
        
        if(snakeModelo.getVidasActuales() == 0)
        {
            panelSuperior.actualizarPanelGameOver();
            javax.swing.JLabel label = new javax.swing.JLabel("Game Over");
            label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
            JOptionPane.showMessageDialog(null, label, null, JOptionPane.WARNING_MESSAGE);
        }
            

    }
    
    
    private void listenersPaneles(){
        panelSuperior.agregarBotonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snakeModelo.setModoJugando(!snakeModelo.getModoJugando());
                detenerTimer();
                
                if (snakeModelo.getModoJugando()) {
                    snakeModelo.setFlechaTecla("right");
                    inicioJuego();
                    panelSuperior.actualizarPanelparaJugar(true);
                }
                else{panelSuperior.actualizarPanelparaJugar(false);}            
            }
        });
        
        panelSuperior.agregarBotonNuevoJuego(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            javax.swing.JLabel label = new javax.swing.JLabel("New Game");
            label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
            JOptionPane.showMessageDialog(null, label,"Are you sure?", JOptionPane.INFORMATION_MESSAGE);
            
                    snakeModelo.setReiniciar(true);
                    snakeModelo.pasoSiguiente();
                    panelInferior.setPuntajeLabel(snakeModelo.getPuntajeActual());
                    panelInferior.setVidasLabel(snakeModelo.getVidasActuales());
                    actualizarVista();
                   snakeModelo.setReiniciar(false);
                   snakeModelo.setModoJugando(false);
                    snakeModelo.cambioPosManzana();
                    panelSuperior.actualizarPanelInicioJuego();
            }
        });
        
        
        vista.agregarListenerFlechas(KeyEvent.VK_UP, "up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snakeModelo.setFlechaTecla("up");   
            }
        });
        
        vista.agregarListenerFlechas(KeyEvent.VK_DOWN, "down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snakeModelo.setFlechaTecla("down");   
            }
        });
        
        vista.agregarListenerFlechas(KeyEvent.VK_RIGHT, "right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snakeModelo.setFlechaTecla("right");   
            }
        });
             
        vista.agregarListenerFlechas(KeyEvent.VK_LEFT, "left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snakeModelo.setFlechaTecla("left");   
            }
        });
        
    }
    
    private void detenerTimer(){
        if(timer != null){timer.stop();}}
    
    private void inicioJuego(){
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                inicioHilo();
            }
        });
        timer.start();
    }
    
    private void inicioHilo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
            synchronized(snakeModelo){
                
                if(snakeModelo.getVidasActuales() == 0){
                    snakeModelo.eliminarManzanas();
                }
                else{
                darPaso();}
                
                actualizarVista();}   
            }
        }).start();
    }
    
}