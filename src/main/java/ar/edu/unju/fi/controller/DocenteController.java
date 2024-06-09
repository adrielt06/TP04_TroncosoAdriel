package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.ListadoDocentes;
import ar.edu.unju.fi.model.Docente;

@Controller
public class DocenteController {
	@Autowired
	Docente nuevoDocente = new Docente();
	
	@GetMapping("/tablaDocentes")
	public ModelAndView getTablaDocentes() {
		ModelAndView mv = new ModelAndView("tablaDocentes");
		mv.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		return mv;
	}
	
	@GetMapping("/formDocente")
	public ModelAndView getFormDocente() {
		ModelAndView mv = new ModelAndView("formDocente");
		
		mv.addObject("nuevoDocente", nuevoDocente);
		mv.addObject("band", false);
		return mv;
	}
	
	@PostMapping("guardarDocente")
	public ModelAndView guardarDocente(@ModelAttribute("nuevoDocente") Docente docenteAGuardar) {
		ListadoDocentes.agregarDocente(docenteAGuardar);
		
		ModelAndView mv = new ModelAndView("tablaDocentes");
		mv.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		return mv;
	}
	
	@GetMapping("/borrarDocente/{legajo}")
	public ModelAndView borrarDocente(@PathVariable(name="legajo") String legajo) {
		ListadoDocentes.eliminarDocente(legajo);
		
		ModelAndView mv = new ModelAndView("tablaDocentes");
		mv.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		return mv;
	}
	
	@GetMapping("/editarDocente/{legajo}")
	public ModelAndView editarDocente(@PathVariable(name="legajo") String legajo) {
		Docente docenteAEditar = ListadoDocentes.buscarDocentePorLegajo(legajo);
		
		ModelAndView mv = new ModelAndView("formDocente");
		mv.addObject("nuevoDocente", docenteAEditar);
		mv.addObject("band", true);
		return mv;
	}
	
	@PostMapping("/editarDocente")
	public ModelAndView actualizarDocente(@ModelAttribute("nuevoDocente") Docente docenteEditado) {
		ListadoDocentes.modificarDocente(docenteEditado);
		
		ModelAndView mv = new ModelAndView("tablaDocentes");
		mv.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		return mv;
	}
}
