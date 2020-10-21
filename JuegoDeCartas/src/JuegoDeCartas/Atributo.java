package JuegoDeCartas;

public class Atributo implements Comparable<Atributo>{

	private String nombre;
	private double valor;
	
	public Atributo(String nombre, double valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public double getValor() {
		return valor;
	}

	@Override
	public int compareTo(Atributo arg0) {
		return (int) (this.getValor() - arg0.getValor());
	}
}
