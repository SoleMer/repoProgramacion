package JuegoDeCartas;

import java.util.ArrayList;

public class Personaje {
	
	private String nombre;
	private ArrayList<Caracteristica> caracteristicas;
	
	public Personaje(String nombre) {
		this.nombre = nombre;
		this.caracteristicas = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}
	
	public void addCaracteristica(Caracteristica c) {
		caracteristicas.add(c);
	}
	
	public double getValor(String s) {								//RECIBO EL NOMBRE DEL ATRIBUTO QUE BUSCO
		for (int i = 0; i < caracteristicas.size(); i++) {			//RECORRO LAS CARACTERISTICAS
            if (caracteristicas.get(i).getNombre().equals(s))		//CUANDO ENCUENTRO LA CARACTERISTICA CON EL NOMBRE QUE BUSCO
                return caracteristicas.get(i).getValor();			//RETORNO EL VALOR
        }
        return 0;													//SI MO LA ENCUENTRO RETORNO 0
	}
	public String getNombreAtributo(int i) {
		return this.caracteristicas.get(i).getNombre();
	}
	
	public int getCantidadDeAtributos() {
		return caracteristicas.size();
	}
	
	public String seleccionarAtributo() {
		int i = (int)(this.getCantidadDeAtributos()*Math.random() +1);		//ELIJO UN NUMERO ALEATORIO ENTRE LA CANTIDAD DE ATRIBUTOS
		return caracteristicas.get(i-1).getNombre();									//RETORNO EL NOMBRE DEL ATRIBUTO EN ESA POSICION
	}

	public String toString(String atributo) {					//RETORNA UN STRING CON EL NOMBRE DEL PERSONAJE + EL NOMBRE Y EL VALOR DEL ATRIBUTO
		return (this.getNombre() +" con "+ atributo +" "+this.getValor(atributo));
	}
	
	public boolean leGana(Personaje p,String atributo) {
		return this.getValor(atributo) > p.getValor(atributo);		//COMPARO LOS VALORES DEL MISMO ATRIBUTO EN LOS DOS PERSONAJES
	}
	
	public boolean esDelMismoMazo(Personaje p) {		//VERIFICO QUE DOS CARTAS PERTENEZCAN AL MISMO MAZO
		if(this.getCantidadDeAtributos() == p.getCantidadDeAtributos()) {	//SI TIENEN LA MISMA CANTIDAD DE ATRIBUTOS
			int i=0;								
			while(i<this.caracteristicas.size()) {	//RECORRO LOS ATRIBUTOS COMPARANDO QUE LOS NOMBRES COINCIDAN
				if(! this.getNombreAtributo(i).equals(p.getNombreAtributo(i))) return false; //SI UNO NO COINCIDE RETORNO FALSE
				else i++;		
			}
			return true;		//SI SALE DEL WHILE RETORNA TRUE
		}
		return false;			//SI TIENE DIFERENTE CANTIDAD DE ATRIBUTOS RETORNO FALSE
	}
}
