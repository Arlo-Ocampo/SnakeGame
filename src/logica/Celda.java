
package logica;

import java.awt.geom.Ellipse2D;



 enum TipoCelda{
    VACIA, SNAKE, MANZANA}

public class Celda {
    private TipoCelda tipoCelda;
    private Ellipse2D.Double circulo = new Ellipse2D.Double();
    
    //default constructor
    public Celda() {this.tipoCelda = TipoCelda.VACIA;}
    
    //non default constructor
    //user specifies what the cell tipo is by passing an int which represents the following
    // 0 = none
    // 1 = snake
    // 2 = fruit
    public Celda(int tipo){
        
        setTipoCelda(tipo);
    }
    
    
    public void setTipoCelda(int i){
        switch (i) {
            case 0:
                this.tipoCelda = TipoCelda.VACIA;
                break;
            case 1:
                this.tipoCelda = TipoCelda.SNAKE;
                break;
            case 2:
                this.tipoCelda = TipoCelda.MANZANA;
                break;
        }
    }
    
    public int getTipoCelda(){
        switch(tipoCelda){
            default: //if VACIA
                return 0; 
            case SNAKE: 
                return 1;
            case MANZANA: 
                return 2;
        }
    }
    
    //boolean checkers to make it easier to understand
    public boolean celdaSnake(){
        return tipoCelda.equals(TipoCelda.SNAKE);
    }
    
    public boolean celdaManzana(){
        return tipoCelda.equals(TipoCelda.MANZANA);
    }
    public boolean celdaVacia(){
        return tipoCelda.equals(TipoCelda.VACIA);
    }
    
    //each cell is represented as a circulo
    public void setCirculo(double x, double y, double w, double h){
        circulo.setFrame(x, y, w, h);
    }
    
    public Ellipse2D getCirculo(){return circulo;}

}
