package Pocimas;

import JuegoDeCartas.Atributo;

public class ValorTodosLosAtributos extends Pocima {
	
	private double porcentaje;

	public ValorTodosLosAtributos(String nombre, double porcentaje) {
		super(nombre);
		this.porcentaje = porcentaje;
	}


	@Override
	public double aplicarPocima(Atributo a) {
		return ((a.getValor())*porcentaje);
	}

}
