package JuegoDeCartas;

public class Jugador {
	
	private String nombre;
	private Mazo cartas;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.cartas = new Mazo();
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
	
	public Personaje getPrimerCarta() {
		Personaje carta = this.cartas.get(0);
		this.cartas.quitarCarta(0);
		return carta;
	}
	
	public String seleccionarAtributo(Personaje p) {
		int i= (int)(p.getCantidadDeAtributos()*Math.random());
		return p.getNombreAtributo(i);
	}
	
	public void ganarCartas(Personaje cartaA,Personaje cartaB) {
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
