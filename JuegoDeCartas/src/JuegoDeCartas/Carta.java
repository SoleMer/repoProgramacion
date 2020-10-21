package JuegoDeCartas;

import java.util.ArrayList;

import Pocimas.Pocima;

public class Carta {

	private String nombre;
	private ArrayList<Atributo> atributos;
	private Pocima pocima;

	public Carta(String nombre) {
		this.nombre = nombre;
		this.atributos = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void addCaracteristica(Atributo c) {
		atributos.add(c);
	}

	public boolean setPocima(Pocima p) {
		if(pocima == null) {		//SI NO TIENE POCIMA
			this.pocima = p;		//LA AGREGO
			return true;			//RETORNO VERDADERO PORQUE FUE ASIGNADA
		}
		return false;				//SI YA TIENE POCIMA, RETORNO FALSO PARA QUE SE SEPA QUE LA POCIMA NO FUE ASIGNADA
	}

	public double getValor(String s) { // RECIBO EL NOMBRE DEL ATRIBUTO QUE BUSCO

		double valor = 0; // INICIALIZO VALOR EN 0
		for (int i = 0; i < atributos.size(); i++) { // RECORRO LAS CARACTERISTICAS
			if (atributos.get(i).getNombre().equals(s)) { // CUANDO ENCUENTRO LA CARACTERISTICA CON EL NOMBRE QUE BUSCO
				valor = atributos.get(i).getValor(); // VALOR SE VUELVE EL VALOR DE LA CARACTERISTICA
				if (!(pocima == null)) 					// SI LA CARTA TIENE UNA POCIMA
					valor = pocima.aplicarPocima(atributos.get(i)); // EL VALOR SE MODIFICA SEGUN LA POCIMA
			}
		}
		return valor; // RETORNO VALOR
	}

	public double getValorSinPocima(String s) {
		double valor = 0; // INICIALIZO VALOR EN 0
		for (int i = 0; i < atributos.size(); i++) { // RECORRO LAS CARACTERISTICAS
			if (atributos.get(i).getNombre().equals(s)) // CUANDO ENCUENTRO LA CARACTERISTICA CON EL NOMBRE QUE
																// BUSCO
				valor = atributos.get(i).getValor(); // VALOR SE VUELVE EL VALOR DE LA CARACTERISTICA
		}
		return valor; // RETORNO VALOR
	}

	public String getNombreAtributo(int i) {
		return this.atributos.get(i).getNombre();
	}

	public int getCantidadDeAtributos() {
		return atributos.size();
	}

	public String toString(String atributo) { // RETORNA UN STRING CON EL NOMBRE DEL PERSONAJE + EL NOMBRE Y EL VALOR
												// DEL ATRIBUTO
		String principal = (this.getNombre() + " con " + atributo + " " + this.getValorSinPocima(atributo));
		if (pocima == null)
			return principal;
		else
			return (principal + ", se aplicó pócima " + pocima.getNombre() + ". Valor resultante: "
					+ this.getValor(atributo));
	}

	public boolean leGana(Carta p, String atributo) {
		return this.getValor(atributo) > p.getValor(atributo); // COMPARO LOS VALORES DEL MISMO ATRIBUTO EN LOS DOS
																// PERSONAJES
	}

	public boolean esDelMismoMazo(Carta p) { // VERIFICO QUE DOS CARTAS PERTENEZCAN AL MISMO MAZO
		if (this.getCantidadDeAtributos() == p.getCantidadDeAtributos()) { // SI TIENEN LA MISMA CANTIDAD DE ATRIBUTOS
			int i = 0;
			while (i < this.atributos.size()) { // RECORRO LOS ATRIBUTOS COMPARANDO QUE LOS NOMBRES COINCIDAN
				if (!this.getNombreAtributo(i).equals(p.getNombreAtributo(i)))
					return false; // SI UNO NO COINCIDE RETORNO FALSE
				else
					i++;
			}
			return true; // SI SALE DEL WHILE RETORNA TRUE
		}
		return false; // SI TIENE DIFERENTE CANTIDAD DE ATRIBUTOS RETORNO FALSE
	}
	
	public ArrayList<Atributo> getAtributos(){
		return (ArrayList<Atributo>)atributos.clone();
	}
}