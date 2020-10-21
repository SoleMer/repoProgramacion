package Estrategias;

import java.util.ArrayList;

import JuegoDeCartas.Atributo;
import JuegoDeCartas.Carta;

public class EstrategiaAmbicioso implements Estrategia{

	public String seleccionarAtributo(Carta c) {
		ArrayList<Atributo> atributos = c.getAtributos();
		Atributo atributoMayor=atributos.get(0);
		for(int i=0; i< atributos.size(); i++) {
			Atributo a = atributos.get(i);
			System.out.println("Atributo: "+a.getNombre()+" "+a.getValor());
			System.out.println("Atributo Mayor: "+atributoMayor.getNombre()+" "+atributoMayor.getValor());
			if(a.compareTo(atributoMayor)>0) atributoMayor=a;
		}
		return atributoMayor.getNombre();		//ELEGIR MAYOR VALOR
	}
}
