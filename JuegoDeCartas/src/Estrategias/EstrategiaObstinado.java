package Estrategias;

import JuegoDeCartas.Carta;

public class EstrategiaObstinado implements Estrategia{

	private String atributoFijo;
	
	public EstrategiaObstinado(String atributo) {
		atributoFijo = atributo;
	}
	
	public void setAtributoFijo(String a) {
		atributoFijo = a;
	}

	public String seleccionarAtributo(Carta c) {
		return atributoFijo;		//SIEMPRE EL MISMO
	}
}
