package JuegoDeCartas;

import java.util.ArrayList;

import Pocimas.Cocktail;
import Pocimas.NumeroMagico;
import Pocimas.Pocima;
import Pocimas.ValorEspecifico;
import Pocimas.ValorTodosLosAtributos;

public class Juego {

	private Mazo mazo;
	private ArrayList<Pocima> pocimas;
	private Jugador jugador1;
	private Jugador jugador2;
	private int rondas;

	public Juego(Mazo mazo) {
		this.mazo = mazo;
		this.pocimas = new ArrayList<>();
	}

	public Juego(Mazo mazo, Jugador jugador1, Jugador jugador2, int rondas) {
		this.mazo = mazo;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.rondas = rondas;
		this.pocimas = new ArrayList<>();
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}

	public void setJugadores(Jugador jugador1, Jugador jugador2) {
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}

	public int getRondas() {
		return rondas;
	}

	public void setRondas(int rondas) {
		this.rondas = rondas;
	}
	
	public void repartirCartas() {
		mazo.mezclar();
		for (int i = 0; i < mazo.getCantidadDeCartas(); i += 2) { // SUMO DE DOS EN DOS
			Carta p1 = mazo.get(i);
			Carta p2 = mazo.get(i+1);
			jugador1.addCarta(p1); // UNA CARTA AL JUGADOR 1
			jugador2.addCarta(p2); // LA SIGUIENTE AL JUGADOR 2
		}						 // REPITE HASTA TERMINAR EL MAZO
	}
	
	public void cargarPocimas() {				//CREO LAS POCIMAS
		ValorTodosLosAtributos p1 = new ValorTodosLosAtributos("Fortalecedora", 1.20);
		ValorTodosLosAtributos p2 = new ValorTodosLosAtributos("Fortalecedora Plus", 1.50); //BIEN
		ValorTodosLosAtributos p3 = new ValorTodosLosAtributos("Kriptonita", 0.75);	//BIEN
		ValorTodosLosAtributos p4 = new ValorTodosLosAtributos("Reductor de Plomo", 0.45);  //MAL
		NumeroMagico p5 = new NumeroMagico("Quiero Vale 4", 4);//BIEN
		NumeroMagico p6 = new NumeroMagico("NumeroMagico", 23);//BIEN
		ValorEspecifico p7 = new ValorEspecifico("PocimaSelectivaFuerza", "fuerza", 1.35);//CAMBIA CUALQUIER VALOR
		ValorEspecifico p8 = new ValorEspecifico("PocimaSelectivaPeso", "peso", 1.43); //CAMBIA CUALUIER VALOR Y LO ACHICA
		Cocktail p9 = new Cocktail("Cocktail", p1, p7);
		Cocktail p10 = new Cocktail("Cocktail", p2, p8);
		Cocktail p11 = new Cocktail("Cocktail Fuerza y Peso", p7, p8);
		Cocktail p12 = new Cocktail("Cocktail Plus", p9, p10);
						//LAS AGREGO A LA LISTA
		this.pocimas.add(p1);
		this.pocimas.add(p2);
		pocimas.add(p3);
		pocimas.add(p4);
		pocimas.add(p5);
		pocimas.add(p6);
		pocimas.add(p7);
		pocimas.add(p8);
		pocimas.add(p9);
		pocimas.add(p10);
		pocimas.add(p11);
		pocimas.add(p12);
		
		repartirPocimas();		//LAS REPARTO
	}
	
	public void repartirPocimas() {
		int i;
		for(Pocima pocima: pocimas) {		//RECORRO TODAS LAS POCIMAS
			boolean asignada = false;
			while(!asignada) {				//ME ASEGURO DE QUE LA POCIMA SE LE ASIGNE A UNA CARTA
				i= (int)(mazo.getCantidadDeCartas()*Math.random());		//OBTENGO UNA CARTA AL AZAR DEL MAZO
				asignada = mazo.get(i).setPocima(pocima);			//LE SETEO LA POCIMA A LA CARTA				
			}
		}		
	}

	public Jugador getGanadorJugada(Carta cartaJ1, Carta cartaJ2, String atributo) {
		if (cartaJ1.leGana(cartaJ2, atributo)) return this.jugador1; // SI LA CARTA DE JUGADOR 1 LE GANA A LA DEL JUGADOR 2 RETORNO EL JUGADOR 1
		else if(cartaJ2.leGana(cartaJ1, atributo))	return this.jugador2;//SINO RETORNO EL JUGADOR 2
		else return null;												//SI ES EMPATE RETORNO NULL
	}
	
	public boolean terminado(int ronda) {  //SI UNO DE LOS DOS SE QUEDA SIN CARTAS O SI SE TERMINO LA CANTIDAD DE RONDAS, EL JUEGO SE TERMINA
		return this.getJugador1().getCantidadDeCartas() == 0 || this.getJugador2().getCantidadDeCartas() == 0 || ronda >= this.getRondas();
	}

	public Jugador getGanador() {		//RETORNA EL JUGADOR QUE MAS CARTAS TIENE
		if(this.getJugador1().tengoMasCartas(this.getJugador2())) {
			return jugador1;	
		}
		return jugador2; 
	}
}
