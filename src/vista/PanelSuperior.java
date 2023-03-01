/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelSuperior extends JPanel{

    private final JButton botonJugar;
    private final JButton botonNuevoJuego;

    public PanelSuperior() {
        setBackground(Color.BLACK);
 
        
        botonJugar = new JButton("Jugar");
        botonJugar.setBackground(Color.WHITE);
        botonJugar.setPreferredSize(new Dimension(100, 30));
        add(botonJugar);
        
        botonNuevoJuego = new JButton("Nuevo Juego");
        botonNuevoJuego.setBackground(Color.WHITE);
        botonNuevoJuego.setPreferredSize(new Dimension(100, 30));
        add(botonNuevoJuego);
        
    }
    
   
    public void agregarBotonListener(ActionListener accionDeJuego){
        botonJugar.addActionListener(accionDeJuego);
    }
    
    public void agregarBotonNuevoJuego(ActionListener accionNuevoJuego){
        botonNuevoJuego.addActionListener(accionNuevoJuego);
    }
    
    public void actualizarPanelparaJugar(boolean estaJugando){
        if(estaJugando){
            botonNuevoJuego.setEnabled(false);
            botonJugar.setText("Pausa");
        }
        else{
            botonNuevoJuego.setEnabled(true);
            botonJugar.setText("Jugar");
        }
    }
    
    public void actualizarPanelGameOver(){
        botonNuevoJuego.setEnabled(true);
        botonJugar.setEnabled(false);
    }
    
    public void actualizarPanelInicioJuego(){
        botonNuevoJuego.setEnabled(true);
        botonJugar.setEnabled(true);
    }
}
