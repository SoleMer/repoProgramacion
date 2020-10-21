package Pocimas;

import JuegoDeCartas.Atributo;

public class Cocktail extends Pocima {

	private Pocima pocima1;
	private Pocima pocima2;

	public Cocktail(String nombre, Pocima pocima1, Pocima pocima2) {
		super(nombre);
		this.pocima1 = pocima1;
		this.pocima2 = pocima2;
	}

	@Override
	public double aplicarPocima(Atributo a) {
		double valorNuevo = pocima1.aplicarPocima(a);
		Atributo b = new Atributo(a.getNombre(),valorNuevo);
		return pocima2.aplicarPocima(b);
	}

}
