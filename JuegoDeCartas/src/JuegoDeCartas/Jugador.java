package JuegoDeCartas;

import Estrategias.Estrategia;

public class Jugador {
	
	private String nombre;
	private Mazo cartas;
	private Estrategia estrategia;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.cartas = new Mazo();
	}

	public Jugador(String nombre, Estrategia estrategia) {
		this.nombre = nombre;
		this.estrategia = estrategia;
		this.cartas = new Mazo();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void addCarta(Carta p) {
		cartas.addCarta(p);
	}
	
	public int getCantidadDeCartas() {
		return cartas.getCantidadDeCartas();
	}
	
	public Carta getPrimerCarta() {
		Carta carta = this.cartas.get(0);
		this.cartas.quitarCarta(0);
		return carta;
	}
	
	public String seleccionarAtributo(Carta c) {
		return estrategia.seleccionarAtributo(c);
	}
	
	public void ganarCartas(Carta cartaA,Carta cartaB) {
		this.cartas.addCartas(cartaA, cartaB);
	}
	
	public boolean tengoMasCartas(Jugador j) {		//VERIFICO SI TENGO MAS CARTAS QUE EL OTRO JUGADOR
		return this.getCantidadDeCartas() > j.getCantidadDeCartas();
	}

	@Override
	public boolean equals(Object obj) {
		try {
            Jugador otro = (Jugador) obj;
            return this.getNombre().equals(otro.getNombre());
        } catch (Exception e){
            return false;
        }
	}


}
