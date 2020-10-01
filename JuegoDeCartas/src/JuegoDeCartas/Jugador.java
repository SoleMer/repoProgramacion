package JuegoDeCartas;

public class Jugador {
	
	private String nombre;
	private Mazo cartas;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void addCarta(Personaje p) {
		cartas.addCarta(p);
	}
	
	public int getCantidadDeCartas() {
		return cartas.getCantidadDeCartas();
	}
	
	public Personaje getUltimaCarta() {
		return this.cartas.get(cartas.getCantidadDeCartas() - 1);
	}
	
	public String seleccionarAtributo(Personaje p) {
		int i= (int)(p.getCantidadDeAtributos()*Math.random());
		return p.getNombreAtributo(i);
	}
	
	public void ganarCarta(Personaje cartaA,Personaje cartaB,Jugador j) {
		if(this.cartas.tengoCarta(cartaA)) {			//SI NO TENGO LA CARTA A ES PORQUE ES DEL OTRO JUGADOR
			j.quitarCarta(cartaA);							//SE LA QUITO
			cartas.addCarta(cartaA);						//LA AGREGO A MI COLECCION
		}
		else {
			j.quitarCarta(cartaB);							//SI YA TENGO LA CARTA A, ME GANE LA B. SE LA QUITO AL OTRO JUGADOR
			cartas.addCarta(cartaB);						//LA AGREGO A MI COLLECCION
		}
	}
	
	public void quitarCarta(Personaje p) {
		cartas.quitarCarta(p);
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
