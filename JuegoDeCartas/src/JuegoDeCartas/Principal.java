package JuegoDeCartas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Estrategias.EstrategiaAmbicioso;
import Estrategias.EstrategiaObstinado;

public class Principal {

	public static void main(String[] args) {
		String mazoPath = "./superheroes.json";
		Mazo mazo = Mazo.cargarMazo(mazoPath); // EN ESE METODO CARGO LOS VALORES DE TODAS LAS CARTAS / PERSONAJES
		Juego juego = new Juego(mazo); // CREO EL JUEGO
		cargarJugadores(juego); // CARGO LOS JUGADORES
		cargarCantidadDeRondas(juego); // CARGO LA CANTIDAD DE RONDAS
		juego.cargarPocimas();		//CARGO LAS POCIMAS
		juego.repartirCartas(); // REPARTO LAS CARTAS
		System.out.println(juego.getJugador1().getNombre() + " tiene "+ juego.getJugador1().getCantidadDeCartas() +" cartas");
		System.out.println(juego.getJugador2().getNombre() + " tiene "+ juego.getJugador2().getCantidadDeCartas() +" cartas");
		System.out.println(juego.getJugador1().getNombre() + " y " + juego.getJugador2().getNombre() + " jugaran " + juego.getRondas() + " rondas");

		boolean juegoTerminado = false;
		int ronda = 0;
		
		Jugador jugadorEnTurno = juego.getJugador1(); // LE DOY EL PRIMER TURNO AL JUGADOR 1
		Carta carta;

		while (!juegoTerminado) {
			ronda++; // SUMO UNA RONDA
			System.out.println("Ronda " + ronda); // IMPRIMO EL NUMERO DE RONDA

			Carta cartaJ1 = juego.getJugador1().getPrimerCarta(); // OBTENGO UNA CARTA 
			Carta cartaJ2 = juego.getJugador2().getPrimerCarta();	//
			
			if(jugadorEnTurno.equals(juego.getJugador1())) {	//IDENTIFICO LA CARTA DEL JUGADOR EN TURNO
				carta = cartaJ1;				
			} else {	//VER EQUALS DE JUGADOR
				carta = cartaJ2;
			}
			String atributo = jugadorEnTurno.seleccionarAtributo(carta); // ELIGE EL ATRIBUTO POR EL QUE VA A JUGAR
			System.out.println(
					"El jugador " + jugadorEnTurno.getNombre() + " selecciona competir por el atributo " + atributo);
			
			// IMPRIMO LA CARTA DE LOS DOS JUGADORES JUNTO AL NOMBRE Y VALOR DEL ATRIBUTO
			System.out.println("La carta de " + juego.getJugador1().getNombre() + " es " + cartaJ1.toString(atributo));

			System.out.println("La carta de " + juego.getJugador2().getNombre() + " es " + cartaJ2.toString(atributo));
			
			Jugador ganador = juego.getGanadorJugada(cartaJ1, cartaJ2, atributo); // VERIFICO QUE JUGADOR GANO
			if(ganador == null) System.out.println("Empate."); //SI NO HAY GANADOR, IMPRIMO EMPATE
			else{															//SI HAY GANADOR
				System.out.println("Gana la ronda " + ganador.getNombre()); // IMPRIMO EL NOMBRE DEL GANADOR DE LA RONDA
				ganador.ganarCartas(cartaJ1, cartaJ2); // EL GANADOR DE LA RONDA SE LLEVA LAS DOS CARTAS
				jugadorEnTurno = ganador; // EL JUGADOR QUE GANO LA RONDA ES EL QUE TIENE EL TURNO SIGUIENTE
			}
					
			// IMPRIMO LA CANTIDAD DE CARTAS QUE LE QUEDA A CADA JUGADOR
			System.out.println(juego.getJugador1().getNombre() + " posee ahora "
					+ juego.getJugador1().getCantidadDeCartas() + " cartas, y " + juego.getJugador2().getNombre()
					+ " posee ahora " + juego.getJugador2().getCantidadDeCartas() + " cartas.");

			juegoTerminado = juego.terminado(ronda); // VERIFICO SI EL JUEGO SE TERMINO
		}

		System.out.print(juego.getGanador().getNombre() + " gano el juego."); // AL SALIR DEL WHILE IMPRIMO EL NOMBRE
																				// DEL GANADOR DEL JUEGO
	}

	public static void cargarJugadores(Juego j) {
		String nombreJ1 = pedirJugador(); // CARGO LOS NOMBRES DE LOS JUGADORES
		String nombreJ2 = pedirJugador();
		EstrategiaObstinado eJ1 = new EstrategiaObstinado("fuerza");
		EstrategiaAmbicioso eJ2 = new EstrategiaAmbicioso();
		Jugador j1 = new Jugador(nombreJ1, eJ1);	// CREO LOS JUGADORES
		Jugador j2 = new Jugador(nombreJ2, eJ2);	//CON LAS ESTRATEGIAS

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

		BufferedReader e = new BufferedReader(new InputStreamReader(System.in));
		int cantidad = 0;

		while (cantidad < 2 || cantidad > 30) { // MINIMO 2 RONDAS, MAXIMO 30 RONDAS
			// PEDIR AL USUARIO QUE LO INGRESE POR TECLADO
			System.out.println("Ingrese la cantidad de rondas que desea jugar.");
			try {
				cantidad = Integer.parseInt(e.readLine());
			} catch (IOException e1) {
				cantidad = 0;
			}
		}

		if (!(cantidad % 2 == 0)) { // TIENE QUE SER UN NUMERO PAR DE RONDAS
			return cantidad+1; // SI ES IMPAR LE SUMO UNA
		}
		return cantidad;
	}

	

}
