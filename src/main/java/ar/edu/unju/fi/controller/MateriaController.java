package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.ListadoCarreras;
import ar.edu.unju.fi.collections.ListadoDocentes;
import ar.edu.unju.fi.collections.ListadoMaterias;
import ar.edu.unju.fi.model.Materia;

@Controller
public class MateriaController {
	@Autowired
	Materia nuevaMateria = new Materia();
	
	@GetMapping("/tablaMaterias")
	public ModelAndView getTablaDocentes() {
		ModelAndView mv = new ModelAndView("tablaMaterias");
		mv.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
		return mv;
	}
	
	@GetMapping("/formMateria")
	public ModelAndView getFormMateria() {
		ModelAndView mv = new ModelAndView("formMateria");
		
		mv.addObject("nuevaMateria", nuevaMateria);
		mv.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
		mv.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		mv.addObject("band", false);
		return mv;
	}
	
	@PostMapping("guardarMateria")
	public ModelAndView guardarMateria(@ModelAttribute("nuevaMateria") Materia materiaAGuardar) {
		ListadoMaterias.agregarMateria(materiaAGuardar);
		
		materiaAGuardar.setDocente(ListadoDocentes.buscarDocentePorLegajo(materiaAGuardar.getDocente().getLegajo()));;
		materiaAGuardar.setCarrera(ListadoCarreras.buscarCarreraPorCodigo(materiaAGuardar.getCarrera().getCodigo()));;
		
		ModelAndView mv = new ModelAndView("tablaMaterias");
		mv.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
		return mv;
	}
	
	@GetMapping("/borrarMateria/{codigo}")
	public ModelAndView borrarMateria(@PathVariable(name="codigo") String codigo) {
		ListadoMaterias.eliminarMateria(codigo);
		
		ModelAndView mv = new ModelAndView("tablaMaterias");
		mv.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
		return mv;
	}
	
	@GetMapping("/editarMateria/{codigo}")
	public ModelAndView editarMateria(@PathVariable(name="codigo") String codigo) {
		Materia materiaAEditar = ListadoMaterias.buscarMateriaPorCodigo(codigo);
		
		ModelAndView mv = new ModelAndView("formMateria");
		mv.addObject("nuevaMateria", materiaAEditar);
		mv.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
		mv.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		mv.addObject("band", true);
		return mv;
	}
	
	@PostMapping("/editarMateria")
	public ModelAndView actualizarMateria(@ModelAttribute("nuevaMateria") Materia materiaEditada) {
		ListadoMaterias.modificarMateria(materiaEditada);
		
		materiaEditada.setDocente(ListadoDocentes.buscarDocentePorLegajo(materiaEditada.getDocente().getLegajo()));;
		materiaEditada.setCarrera(ListadoCarreras.buscarCarreraPorCodigo(materiaEditada.getCarrera().getCodigo()));;
		
		ModelAndView mv = new ModelAndView("tablaMaterias");
		mv.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
		return mv;
	}
}
