package datos;

import java.util.Date;

public class Sorteo {
    private int id;
    private Date fecha;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int numExtra;
    private double totalApostado;
    private double pozoOro;
    private double pozoPlata;
    
    public Sorteo() {
        super();
    }
    
    public Sorteo(Date fecha, int num1, int num2, int num3, int num4, int num5, 
                  int numExtra, double totalApostado, double pozoOro, double pozoPlata) {
        this.fecha = fecha;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.numExtra = numExtra;
        this.totalApostado = totalApostado;
        this.pozoOro = pozoOro;
        this.pozoPlata = pozoPlata;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public int getNum1() {
        return num1;
    }
    
    public void setNum1(int num1) {
        this.num1 = num1;
    }
    
    public int getNum2() {
        return num2;
    }
    
    public void setNum2(int num2) {
        this.num2 = num2;
    }
    
    public int getNum3() {
        return num3;
    }
    
    public void setNum3(int num3) {
        this.num3 = num3;
    }
    
    public int getNum4() {
        return num4;
    }
    
    public void setNum4(int num4) {
        this.num4 = num4;
    }
    
    public int getNum5() {
        return num5;
    }
    
    public void setNum5(int num5) {
        this.num5 = num5;
    }
    
    public int getNumExtra() {
        return numExtra;
    }
    
    public void setNumExtra(int numExtra) {
        this.numExtra = numExtra;
    }
    
    public double getTotalApostado() {
        return totalApostado;
    }
    
    public void setTotalApostado(double totalApostado) {
        this.totalApostado = totalApostado;
    }
    
    public double getPozoOro() {
        return pozoOro;
    }
    
    public void setPozoOro(double pozoOro) {
        this.pozoOro = pozoOro;
    }
    
    public double getPozoPlata() {
        return pozoPlata;
    }
    
    public void setPozoPlata(double pozoPlata) {
        this.pozoPlata = pozoPlata;
    }
}
