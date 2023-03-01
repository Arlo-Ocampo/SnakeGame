
package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import logica.Celda;


public class Modelo {
    
    static final int AREA_TOTAL_JUEGO = 20;
    
    private Celda[][] cuadriculaCeldas = new Celda[AREA_TOTAL_JUEGO][AREA_TOTAL_JUEGO];
    private int puntajeActual = 0;
    private int vidasActuales = 3;
    //false means game is paused
    private boolean estaJugando = false;
    private int intervaloTiempo = 1500;
    //this is a redundant variabele because it is cordinadasSerpiente.size()
    //but it is good to have this as a seperate variable
    private int cuerpoSerpiente;
    //used Point as it is easy to store x and y cordinates than a 2D array
    private ArrayList<Point> cordinadasSerpiente;
    private Point posicionManzana;
    private Flecha flechaActual = Flecha.RIGHT;
    private Flecha flechaAnterior = Flecha.LEFT;
    //boolean to see if user wants to reiniciar game
    private boolean reiniciar = false;
    
    public Modelo(){
        for (Celda[] cuadriculaCelda1 : cuadriculaCeldas) {
            for (int j = 0; j < cuadriculaCelda1.length; j++) {
                cuadriculaCelda1[j] = new Celda();
            }
        }
        
        cambioPosManzana();
        
        cordinadasSerpiente = new ArrayList<>();
        
        actualizarCuerpoSerpiente();
    }
    
    //changes the Position of the fruit by randomly assigning cordinates
    public void cambioPosManzana(){
        int x, y;
        x = new Random().nextInt(AREA_TOTAL_JUEGO-1);
        y = new Random().nextInt(AREA_TOTAL_JUEGO-1);
        
        //to prevent overlapping of snake's coordinates and the fruits position
        if(cuerpoSerpiente>0){
        while (cordinadasSerpiente.contains(new Point(x, y))) {            
            x = new Random().nextInt(AREA_TOTAL_JUEGO-1);
            y = new Random().nextInt(AREA_TOTAL_JUEGO-1);
        }}
        
        this.posicionManzana = new Point(x, y);
    }
    
    //remove Fruit from the grid
    public void eliminarManzanas(){
        //technically supposed to be eaqual to null but that won't work in my code
        //assigned negative coordinates
        this.posicionManzana = new Point(-1, -1);
    }
    
    public void actualizarCuerpoSerpiente(){
        this.cuerpoSerpiente = cordinadasSerpiente.size();
    }

    //add new snakePart, x and y are the cordinates of the new part
    public void agregarCuerpoSerpiente(int x, int y){
        this.cordinadasSerpiente.add(new Point(x, y));
    }
    
    //alter the coordinates of the snake parts at the necessary index
    public void cambiarCordinadasSerpiente(int position, int newX, int newY){
        this.cordinadasSerpiente.remove(position);
        this.cordinadasSerpiente.add(position, new Point(newX, newY));
    }
    
    public Celda[][] getAnchoCelda(){
        return cuadriculaCeldas;
    }
    
    public int getTipoCelda(int x, int y){
        return cuadriculaCeldas[x][y].getTipoCelda();
    }
    
    void setTipoCelda(int x, int y, int type){
        cuadriculaCeldas[x][y].setTipoCelda(y);
    }
    
    public void setReiniciar(boolean reiniciar){this.reiniciar = reiniciar;}
    
    public void setPuntaje(int puntaje){ this.puntajeActual = puntaje;}
    public int getPuntajeActual() {return puntajeActual;}
    
    public void setVidas(int labelVidas){ this.vidasActuales = labelVidas;  }
    public int getVidasActuales() {return vidasActuales; }
    
    public void setFlechaTecla(String key){
        //other part of the if check to make sure snake only moves forward and not backward
        
        if (key.toLowerCase().equals("up") && !flechaAnterior.equals(Flecha.DOWN)) {
            this.flechaActual = Flecha.UP;
        }
        else if (key.toLowerCase().equals("down") && !flechaAnterior.equals(Flecha.UP)) {
            this.flechaActual = Flecha.DOWN;
        }
        else if (key.toLowerCase().equals("right") && !flechaAnterior.equals(Flecha.LEFT)){
            this.flechaActual = Flecha.RIGHT;
        }
        else if (key.toLowerCase().equals("left") && !flechaAnterior.equals(Flecha.RIGHT)){
            this.flechaActual = Flecha.LEFT;
        }  
    }
    //gets the user input
    public String getTeclaFlecha(){
        return this.flechaActual.toString();
    }
    
    public int aumentarPuntaje(){
        puntajeActual +=1;
        return puntajeActual;
    }
    
    public int restarVidas(){
        vidasActuales -=1;
        return vidasActuales;
    }
    
    public int getAlturaCeldas(){return cuadriculaCeldas.length;}
    public int getAnchoCeldas(){return cuadriculaCeldas[0].length;}
    
    public void setModoJugando(boolean estaJugando) {this.estaJugando = estaJugando;} 
    public boolean getModoJugando() {return estaJugando;}
    
    public int getTimeInterval(){return intervaloTiempo;}
    public void setTimeInterval(int time){this.intervaloTiempo = time;}
    
    public void muerteSerpiente(){
    //labelVidas is decremeted and snakecordinates are reiniciar to intial
        restarVidas();
        this.cuerpoSerpiente = 0;
        cordinadasSerpiente.removeAll(cordinadasSerpiente);
        this.flechaAnterior = Flecha.LEFT;
        this.flechaActual = Flecha.RIGHT;
    }
    
    //reseting all the variables
    public void ReiniciarJuego(){
        muerteSerpiente();
        setPuntaje(0);
        setVidas(3);
        eliminarManzanas();
        this.flechaAnterior = Flecha.LEFT;
        this.flechaActual = Flecha.RIGHT;
    }
    
    public void pasoSiguiente(){
        Celda nextVersion[][] = new Celda[AREA_TOTAL_JUEGO][AREA_TOTAL_JUEGO];
        
        if(reiniciar){
            ReiniciarJuego();
        }
        
        else{
        if (cuerpoSerpiente == 0) {
            agregarCuerpoSerpiente(0, 2);
            actualizarCuerpoSerpiente();
        }
        else if (cuerpoSerpiente < 4) {
            agregarCuerpoSerpiente(0, 2);
            actualizarCuerpoSerpiente();
            actualizarPosSerpiente();
            
        }
        else{actualizarPosSerpiente();}
        
        //if snake eats fruit
        if(cordinadasSerpiente.get(0).x == posicionManzana.x && cordinadasSerpiente.get(0).y == posicionManzana.y)
        { 
            comer();
        }
   
        //if snake touches the boundary
        if(cordinadasSerpiente.get(0).x < 0 || cordinadasSerpiente.get(0).x > AREA_TOTAL_JUEGO-1 ||cordinadasSerpiente.get(0).y <0 ||cordinadasSerpiente.get(0).y > AREA_TOTAL_JUEGO-1)
        {
            muerteSerpiente();
            estaJugando = false;
        }
        
        //if snake eats itself
        for (int i = 1; i < cordinadasSerpiente.size()-1; i++) {
                if (cordinadasSerpiente.get(0).equals(cordinadasSerpiente.get(i))) {
                    muerteSerpiente();
                    estaJugando = false;
                    break;
                }
            }
                }
        
        for (int y = 0; y <cuadriculaCeldas.length; y++) {
            for (int x = 0; x < cuadriculaCeldas[y].length; x++) {
                if (cordinadasSerpiente.contains(new Point(x, y))) {
                    nextVersion[y][x] = new Celda(1);
                }
                else if (posicionManzana.equals(new Point(x, y))) {
                    nextVersion[y][x] = new Celda(2);
                }
                else {nextVersion[y][x] = new Celda(0);}
            }
        }
        
        cuadriculaCeldas = nextVersion;
    }
    
    public void actualizarPosSerpiente(){
        
        for (int i = cuerpoSerpiente-1; i > 0; i--) {
            cambiarCordinadasSerpiente(i, cordinadasSerpiente.get(i-1).x, cordinadasSerpiente.get(i-1).y);
        }
        switch (flechaActual) {
            case DOWN:
                cambiarCordinadasSerpiente(0, cordinadasSerpiente.get(0).x, (cordinadasSerpiente.get(0).y)+1);
                break;
            case UP:
                cambiarCordinadasSerpiente(0, cordinadasSerpiente.get(0).x, (cordinadasSerpiente.get(0).y)-1);
                break;
            case RIGHT:
                cambiarCordinadasSerpiente(0, (cordinadasSerpiente.get(0).x)+1, (cordinadasSerpiente.get(0).y));
                break;
            case LEFT:
                cambiarCordinadasSerpiente(0, (cordinadasSerpiente.get(0).x)-1, (cordinadasSerpiente.get(0).y));
                break;
            default:
                break;
        }
       
        flechaAnterior = flechaActual;
//        System.out.println(cuerpoSerpiente);
//        System.out.println(flechaActual.toString());
//        for (int i = 0; i < cordinadasSerpiente.size(); i++) {
//            System.out.println(cordinadasSerpiente.get(i).x +" "+cordinadasSerpiente.get(i).y+" ,");
//        }
        
    }
    
    public void comer(){
        agregarCuerpoSerpiente(cordinadasSerpiente.get(cuerpoSerpiente-1).x, cordinadasSerpiente.get(cuerpoSerpiente-1).y);
        cambioPosManzana();
        aumentarPuntaje();
        actualizarCuerpoSerpiente();
    }
    
}
//Flecha controls
enum Flecha {
    UP, DOWN, RIGHT, LEFT
}
