package Pocimas;

import JuegoDeCartas.Atributo;

public class ValorEspecifico extends Pocima{

	private String valorACambiar;
	private double porcentaje;
	
	public ValorEspecifico(String nombre, String valorACambiar, double porcentaje) {
		super(nombre);
		this.valorACambiar = valorACambiar;
		this.porcentaje = porcentaje;
	}
	
	@Override
	public double aplicarPocima(Atributo a) {
		if(a.getNombre().equals(valorACambiar)) {
			return ((a.getValor())*porcentaje);
		}
		return a.getValor();
	}
	
}
