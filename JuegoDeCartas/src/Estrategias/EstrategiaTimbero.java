package Estrategias;

import JuegoDeCartas.Carta;

public class EstrategiaTimbero implements Estrategia{


	public String seleccionarAtributo(Carta c) {
		int i= (int)(c.getCantidadDeAtributos()*Math.random());
		return c.getNombreAtributo(i);			//ELIGE AL AZAR
	}
}
