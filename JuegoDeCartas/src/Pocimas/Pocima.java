package Pocimas;

import JuegoDeCartas.Atributo;

public abstract class Pocima {

	private String nombre;
	
	public Pocima(String nombre) {
		this.nombre = nombre;
	}

	public abstract double aplicarPocima(Atributo a);
	
	public String getNombre() {
		return nombre;
	}
}
