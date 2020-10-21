package Pocimas;

import JuegoDeCartas.Atributo;

public class NumeroMagico extends Pocima {
	
	double numMagico;
	

	public NumeroMagico(String nombre, int numMagico) {
		super(nombre);
		this.numMagico = numMagico;
	}

	@Override
	public double aplicarPocima(Atributo a) {
		return numMagico;
	}

}
