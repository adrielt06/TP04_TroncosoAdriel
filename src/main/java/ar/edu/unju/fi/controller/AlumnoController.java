package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.ListadoAlumnos;
import ar.edu.unju.fi.model.Alumno;

@Controller
public class AlumnoController {
	@Autowired
	Alumno nuevoAlumno = new Alumno();
	
	@GetMapping("/tablaAlumnos")
	public ModelAndView getTablaAlumnos() {
		ModelAndView mv = new ModelAndView("tablaAlumnos");
		mv.addObject("listadoAlumnos", ListadoAlumnos.listarAlumnos());
		return mv;
	}
	
	@GetMapping("/formAlumno")
	public ModelAndView getFormAlumno() {
		ModelAndView mv = new ModelAndView("formAlumno");
		
		mv.addObject("nuevoAlumno", nuevoAlumno);
		mv.addObject("band", false);
		return mv;
	}
	
	@PostMapping("guardarAlumno")
	public ModelAndView guardarAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoAGuardar) {
		ListadoAlumnos.agregarAlumno(alumnoAGuardar);
		
		ModelAndView mv = new ModelAndView("tablaAlumnos");
		mv.addObject("listadoAlumnos", ListadoAlumnos.listarAlumnos());
		return mv;
	}

	@GetMapping("/borrarAlumno/{lu}")
	public ModelAndView borrarAlumno(@PathVariable(name="lu") String lu) {
		ListadoAlumnos.eliminarAlumno(lu);
		
		ModelAndView mv = new ModelAndView("tablaAlumnos");
		mv.addObject("listadoAlumnos", ListadoAlumnos.listarAlumnos());
		return mv;
	}
	
	@GetMapping("/editarAlumno/{lu}")
	public ModelAndView editarAlumno(@PathVariable(name="lu") String lu) {
		Alumno alumnoAEditar = ListadoAlumnos.buscarAlumnoPorLu(lu);
		
		ModelAndView mv = new ModelAndView("formAlumno");
		mv.addObject("nuevoAlumno", alumnoAEditar);
		mv.addObject("band", true);
		return mv;
	}
	
	@PostMapping("/editarAlumno")
	public ModelAndView actualizarAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoEditado) {
		ListadoAlumnos.modificarAlumno(alumnoEditado);
		
		ModelAndView mv = new ModelAndView("tablaAlumnos");
		mv.addObject("listadoAlumnos", ListadoAlumnos.listarAlumnos());
		return mv;
	}
}
