package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.model.Alumno;

public class ListadoAlumnos {
	public static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	public static List<Alumno> listarAlumnos() {
	    return alumnos;
	}
	
	public static Alumno buscarAlumnoPorLu(String lu) {
		return alumnos.stream()
					   .filter(a->a.getLu().equalsIgnoreCase(lu))
					   .findFirst()
					   .orElse(null);
	}
	
	public static void agregarAlumno(Alumno a) {
		alumnos.add(a);
	}
	
	public static void modificarAlumno(Alumno alumnoModificado) {
		for (int i = 0; i < alumnos.size(); i++) {
			Alumno alumno = alumnos.get(i);
			if (alumno.getLu().equals(alumnoModificado.getLu())) {
				alumnos.set(i, alumnoModificado);
				break;
			}
		}
	}
	
	public static void eliminarAlumno(String lu) {
		alumnos.removeIf(a -> a.getLu().equalsIgnoreCase(lu));
	}
	
}
