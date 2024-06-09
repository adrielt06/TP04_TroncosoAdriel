package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.model.Docente;

public class ListadoDocentes {
	public static List<Docente> docentes = new ArrayList<Docente>();
	
	public static List<Docente> listarDocentes() {
		return docentes;
	}
	
	public static Docente buscarDocentePorLegajo(String leg) {
		return docentes.stream()
					   .filter(d->d.getLegajo().equalsIgnoreCase(leg))
					   .findFirst()
					   .orElse(null);
	}
	
	public static void agregarDocente(Docente d) {
		docentes.add(d);
	}
	
	public static void modificarDocente(Docente docenteModificado) {
		for (int i = 0; i < docentes.size(); i++) {
			Docente docente = docentes.get(i);
			if (docente.getLegajo().equals(docenteModificado.getLegajo())) {
				docentes.set(i, docenteModificado);
				break;
			}
	    }
	}
	
	public static void eliminarDocente(String leg) {
		docentes.removeIf(docente -> docente.getLegajo().equals(leg));
	}
}
