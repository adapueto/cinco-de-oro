package datos;

import java.util.Date;


public class DatoOro {
	
	private String nombreJugador;
	private Date fecha;
	private int num1;
	private int num2;
	private int num3;
	private int num4;
	private int num5;
	private int num6;
	private int num7;
	private int num8;
	private boolean revancha;
	private double costoTotal;
	private boolean simple;
	
	public DatoOro () {
		super();	// permite la herencia en la definición de clases derivadas
	}
	
	public DatoOro(String nombreJugador, int num1, int num2, int num3, int num4, int num5, int num6, int num7, int num8, 
			boolean revancha, double costoTotal, boolean simple) {
		super();
		this.nombreJugador = nombreJugador;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.num4 = num4;
		this.num5 = num5;
		this.num6 = num6;
		this.num7 = num7;
		this.num8 = num8;
		this.revancha = revancha;
		this.costoTotal = costoTotal;
		this.simple = simple;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
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

	public int getNum6() {
		return num6;
	}

	public void setNum6(int num6) {
		this.num6 = num6;
	}

	public int getNum7() {
		return num7;
	}

	public void setNum7(int num7) {
		this.num7 = num7;
	}

	public int getNum8() {
		return num8;
	}

	public void setNum8(int num8) {
		this.num8 = num8;
	}

	public boolean isRevancha() {
		return revancha;
	}

	public void setRevancha(boolean revancha) {
		this.revancha = revancha;
	}

	public double getCostoTotal() {
		return costoTotal;
	}
	
	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
	
	public void setSimple(boolean simple) {
		this.simple = simple;
	}
	
	public boolean isSimple() {
		return simple;
	}
	
	public static double calcularPrecioSimple(boolean isSimple, boolean revancha) {
		if (isSimple && revancha) {
			return 65;
		}else if (isSimple) {
			return 45;
		}
		return 0;
	}
	
	public static double calcularPrecioMultiple(int cantidadNumeros, boolean revancha) {
		int numCombinaciones = 0;
		
		// Calcular número de combinaciones según cantidad de números
		switch (cantidadNumeros) {
			case 4:
				numCombinaciones = 1;   // 4 números + 1 fijo del sorteo = 1 combinación
				break;
			case 6:
				numCombinaciones = 6;   // C(6,5) = 6
				break;
			case 7:
				numCombinaciones = 21;  // C(7,5) = 21
				break;
			case 8:
				numCombinaciones = 56;  // C(8,5) = 56
				break;
			default:
				numCombinaciones = 1;   // Simple (1 combinación)
		}
		
		// Precio base: $45 por combinación
		double precioBase = numCombinaciones * 45;
		
		// Revancha: $20 adicionales POR CADA COMBINACIÓN
		if (revancha) {
			precioBase += numCombinaciones * 20;
		}
		
		return precioBase;
	}
}
