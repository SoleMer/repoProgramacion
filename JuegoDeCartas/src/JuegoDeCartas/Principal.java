package JuegoDeCartas;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

	public static void main(String[] args) {
		String mazoPath = "./superheroes.json";
		Mazo mazo = Mazo.cargarMazo(mazoPath); // EN ESE METODO CARGO LOS VALORES DE TODAS LAS CARTAS / PERSONAJES
		Juego juego = new Juego(mazo); // CREO EL JUEGO
		cargarJugadores(juego); // CARGO LOS JUGADORES
		cargarCantidadDeRondas(juego); // CARGO LA CANTIDAD DE RONDAS
		juego.repartirCartas(); // REPARTO LAS CARTAS

		boolean juegoTerminado = false;
		int ronda = 0;

		Jugador jugadorEnTurno = juego.getJugador1(); // LE DOY EL PRIMER TURNO AL JUGADOR 1
		Jugador jugadorB = juego.getJugador2();

		while (!juegoTerminado) {
			ronda++; // SUMO UNA RONDA
			System.out.println("Ronda " + ronda); // IMPRIMO EL NUMERO DE RONDA

			Personaje cartaA = jugadorEnTurno.getUltimaCarta(); // OBTENGO UNA CARTA
			String atributo = jugadorEnTurno.seleccionarAtributo(cartaA); // ELIGE EL ATRIBUTO POR EL QUE VA A JUGAR
			System.out.println(
					"El jugador " + jugadorEnTurno.getNombre() + " selecciona competir por el atributo " + atributo);
			// IMPRIMO LA CARTA DE LOS DOS JUGADORES JUNTO AL NOMBRE Y VALOR DEL ATRIBUTO
			System.out.println("La carta de " + jugadorEnTurno.getNombre() + " es " + cartaA.toString(atributo));

			Personaje cartaB = jugadorB.getUltimaCarta();
			System.out.println("La carta de " + jugadorB.getNombre() + " es " + cartaB.toString(atributo));

			Jugador ganador = juego.getGanadorJugada(jugadorEnTurno, cartaA, cartaB, atributo); // VERIFICO QUE JUGADOR
																								// GANO
			System.out.println("Gana la ronda " + ganador.getNombre()); // IMPRIMO EL NOMBRE DEL GANADOR DE LA RONDA

			juego.reasignarCartas(cartaA, cartaB, ganador); // EL GANADOR DE LA RONDA SE LLEVA LA CARTA DEL PERDEDOR

			System.out.println(juego.getJugador1().getNombre() + " posee ahora "
					+ juego.getJugador1().getCantidadDeCartas() + " cartas, y " + juego.getJugador2().getNombre()
					+ " posee ahora " + juego.getJugador2().getCantidadDeCartas() + " cartas.");
			// IMPRIMO LA CANTIDAD DE CARTAS QUE LE QUEDA A CADA JUGADOR

			jugadorEnTurno = ganador; // EL JUGADOR QUE GANO LA RONDA ES EL QUE TIENE EL TURNO SIGUIENTE
			jugadorB = juego.getOtroJugador(ganador);
			;

			juegoTerminado = juego.terminado(ronda); // VERIFICO SI EL JUEGO SE TERMINO
		}

		System.out.print(juego.getGanador().getNombre() + " gano el juego."); // AL SALIR DEL WHILE IMPRIMO EL NOMBRE
																				// DEL GANADOR DEL JUEGO
	}

	public static void cargarJugadores(Juego j) {
		String nombreJ1 = pedirJugador(); // CARGO LOS NOMBRES DE LOS JUGADORES
		String nombreJ2 = pedirJugador();
		Jugador j1 = new Jugador(nombreJ1); // CREO LOS JUGADORES
		Jugador j2 = new Jugador(nombreJ2);

		j.setJugadores(j1, j2); // LOS SETEO EN EL JUEGO
	}

	public static String pedirJugador() {
		// PEDIR AL USUARIO QUE LO INGRESE POR TECLADO
		System.out.println("Ingrese nombre del jugador.");
		BufferedReader e = new BufferedReader(new InputStreamReader(System.in));
		String nombre = "";

		try {
			nombre = e.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return nombre;
	}

	public static void cargarCantidadDeRondas(Juego j) {
		int num = pedirCantidadDeRondas(); // PIDO LA CANTIDAD DE RONDAS QUE SE DESEAN JUGAR
		j.setRondas(num); // SETEO LA CANTIDAD DE RONDAS INGRESADA POR EL USUARIO
	}

	public static int pedirCantidadDeRondas() {
		// PEDIR AL USUARIO QUE LO INGRESE POR TECLADO
		System.out.println("Ingrese la cantidad de rondas que desea jugar.");

		BufferedReader e = new BufferedReader(new InputStreamReader(System.in));
		int cantidad = 0;

		while (cantidad < 2 || cantidad > 30) { // MINIMO 2 RONDAS, MAXIMO 30 RONDAS
			try {
				cantidad = Integer.parseInt(e.readLine());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		if (cantidad % 2 != 0) { // TIENE QUE SER UN NUMERO PAR DE RONDAS
			return cantidad++; // SI ES IMPAR LE SUMO UNA
		}
		return cantidad;
	}

	

}
