package JuegoDeCartas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Mazo {
	private ArrayList<Carta> cartas;
	
	public Mazo() {
		this.cartas = new ArrayList<>();
	}
	
	public void addCarta(Carta p) {
		if(cartas.isEmpty()) {							//SI EL MAZO ESTA VACIO AGREGO
			cartas.add(p);								//AGREGO LA CARTA			
		}else if(cartas.get(0).esDelMismoMazo(p)) {		//SI NO ESTA VACIO VERIFICO QUE SEA DEL MISMO MAZO QUE LA PRIMERA
			cartas.add(p);								//SI COINCIDE LA AGREGO
		}
	}			
	
	public void addCartas(Carta cartaA, Carta cartaB) {
		this.cartas.add(cartaA);
		this.cartas.add(cartaB);
	}
	
	public int getCantidadDeCartas() {
		return cartas.size();			//RETORNOLA CANTIDAD DE CARTAS
	}
	
	public void mezclar() {
		Collections.shuffle(cartas);
	}
	
	public Carta get(int i) {
		return cartas.get(i);			//OBTENGO LA CARTA EN LA POSICION SELECCTIONADA
	}
	
	public boolean tengoCarta(Carta p) {
		return !this.cartas.contains(p);			//CORROBORO SI TENGO LA CARTA RECIBIDA POR PARAMETRO
	}
	
	public void quitarCarta(int i) {			//BUSCO LA POSICION DE LA CARTA QUE PERDI
		cartas.remove(i);						//LA ELIMINO DE MI COLECCION
	}
	
	public static Mazo cargarMazo(String jsonFile) {
		Mazo mazo = new Mazo();
		
		File jsonInputFile = new File(jsonFile);
		InputStream is;
		try {
			is = new FileInputStream(jsonInputFile);
			// Creo el objeto JsonReader de Json.
			JsonReader reader = Json.createReader(is);
			// Obtenemos el JsonObject a partir del JsonReader.
			JsonArray cartas = (JsonArray) reader.readObject().getJsonArray("cartas");
			for (JsonObject carta : cartas.getValuesAs(JsonObject.class)) {
				String nombreCarta = carta.getString("nombre");
				Carta c = new Carta(nombreCarta);

				JsonObject atributos = (JsonObject) carta.getJsonObject("atributos");
				for (String nombreAtributo : atributos.keySet()) {
					Atributo a = new Atributo(nombreAtributo, atributos.getInt(nombreAtributo));
					c.addCaracteristica(a);
				}

				mazo.addCarta(c);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mazo;
	}

}
