package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unju.fi.model.Carrera;

public class ListadoCarreras {

	public static List<Carrera> carreras = new ArrayList<Carrera>();
	
	public static List<Carrera> listarCarreras() {
		  //Retorna solo carreras con estado TRUE
		  
	    return carreras.stream()
	    			   .filter(Carrera::isEstado)
	    			   .collect(Collectors.toList());
	}
	
	public static Carrera buscarCarreraPorCodigo(String codigo) {
		//Retorna la primera carrera cuyo codigo coincida con el parametro
		
		return carreras.stream()
				       .filter(c->c.getCodigo().equalsIgnoreCase(codigo))
				       .findFirst()
				       .orElse(null);
	}
	
	public static void agregarCarrera(Carrera c) {
		//La carrera se agrega en estado True de forma automatica
		c.setEstado(true);
	    carreras.add(c);
	}
	
	public static void modificarCarrera(Carrera carreraModificada) {
		for (int i = 0; i < carreras.size(); i++) {
			Carrera carrera = carreras.get(i);
			if (carrera.getCodigo().equals(carreraModificada.getCodigo())) {
				carreras.set(i, carreraModificada);
				break;
			}
	    }
	}
	
	public static void eliminarCarrera(String codigo) {
		//Eliminacion logica, solo se cambia el estado a FALSE.
		carreras.stream()
				.filter(c->c.getCodigo().equalsIgnoreCase(codigo))
				.findFirst()
				.ifPresent(c->c.setEstado(false));
	}
}
