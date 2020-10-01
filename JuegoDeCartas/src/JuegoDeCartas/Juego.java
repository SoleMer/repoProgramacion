package JuegoDeCartas;

import java.util.Collections;

public class Juego {

	private Mazo mazo;
	private Jugador jugador1;
	private Jugador jugador2;
	private int rondas;

	public Juego(Mazo mazo) {
		this.mazo = mazo;
	}

	public Juego(Mazo mazo, Jugador jugador1, Jugador jugador2, int rondas) {
		this.mazo = mazo;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.rondas = rondas;
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
			Personaje p1 = mazo.get(i);
			Personaje p2 = mazo.get(i++);
			jugador1.addCarta(p1); // UNA CARTA AL JUGADOR 1
			jugador2.addCarta(p2); // LA SIGUIENTE AL JUGADOR 2
		} // REPITE HASTA TERMINAR EL MAZO
	}

	public Jugador getGanadorJugada(Jugador j, Personaje cartaA, Personaje cartaB, String atributo) {
		if (cartaA.leGana(cartaB, atributo)) { // LE PREGUNTO A LA CARTA A SI LE GANA A LA B 
			return j; 							//LA CARTA A ES DEL JUGADOR QUE RECIBO POR PARAMETRO
		}										//SI LA CARTA A GANA, RETORNO EL MISMO JUGADOR QUE RECIBI
		return this.getOtroJugador(j);			//SI GANA LA CARTA B, PIDO EL OTRO JUGADOR PARA RETORNARLO
	}
	
	public Jugador getOtroJugador(Jugador j) {
		if(this.getJugador1().equals(j)){		//SI EL JUGADOR 1 ES IGUAL AL RECIBIDO POR PARAMETRO
			return this.getJugador2();			//RETORNO EL JUGADOR 2
		}
		return this.getJugador1();				//SINO, RETORNO EL JUGADOR 1
	}

	public void reasignarCartas(Personaje cartaA, Personaje cartaB, Jugador ganador) {
		if (ganador.equals(jugador1)) { 
			jugador1.ganarCarta(cartaA, cartaB, this.getJugador2()); // EL JUGADOR QUE GANA LA RONDA SE GANA LA CARTA DEL OTRO
		} else {
			jugador2.ganarCarta(cartaA, cartaB, this.getJugador1());
		}
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
