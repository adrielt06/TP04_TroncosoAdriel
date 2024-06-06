package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.ListadoCarreras;
import ar.edu.unju.fi.model.Carrera;

@Controller
public class CarreraController {
	@Autowired
	Carrera nuevaCarrera = new Carrera();
	
	@GetMapping("/formCarrera")
	public ModelAndView getFormCarrera() {
		//vista de html (formulario)
		ModelAndView mv = new ModelAndView("formCarrera");
		
		//Agregado de objeto carrera y bandera para controlar modo edicion
		mv.addObject("nuevaCarrera", nuevaCarrera);
		mv.addObject("band", false);
		return mv;
	}
	
	@PostMapping("/guardarCarrera")
	public ModelAndView guardarCarrera(@ModelAttribute("nuevaCarrera") Carrera carreraAGuardar) {
		//Alta de carrera en listado
		carreraAGuardar.setEstado(true);
		ListadoCarreras.agregarCarrera(carreraAGuardar);
		
		//Muestra de vista con tabla de Carreras
		ModelAndView mv = new ModelAndView("tablaCarreras");
		mv.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
		
		return mv;
	}
	
	@GetMapping("/borrarCarrera/{codigo}")
	public ModelAndView borrarCarrera(@PathVariable(name="codigo") String codigo) {
		//Borrado de carrera de listado
		ListadoCarreras.eliminarCarrera(codigo);
		
		//Mostrar nueva tabla de carreras
		ModelAndView mv = new ModelAndView("tablaCarreras");
		mv.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
		return mv;
	}
	
	
	@GetMapping("/editarCarrera/{codigo}")
	public ModelAndView editarCarrera(@PathVariable(name="codigo") String codigo) {
		//Busqueda de objeto Carrera para edicion por codigo
		Carrera carreraAEditar = ListadoCarreras.buscarCarreraPorCodigo(codigo);
		
		//Mostrar formulario para edicion
		ModelAndView mv = new ModelAndView("formCarrera");
		mv.addObject("nuevaCarrera", carreraAEditar);
		mv.addObject("band", true);
		return mv;
	}
	
	@PostMapping("/editarCarrera")
	public ModelAndView actualizarCarrera(@ModelAttribute("nuevaCarrera") Carrera carreraEditada) {
		//Guardado de objeto Carrera editado
		carreraEditada.setEstado(true);
		ListadoCarreras.modificarCarrera(carreraEditada);
		
		//Mostrar la tabla de carreras actualizada
		ModelAndView mv = new ModelAndView("tablaCarreras");
		mv.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
		return mv;
	}
	
}
