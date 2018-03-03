package br.com.slifesys.gloja.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.slifesys.gloja.model.Categoria;
import br.com.slifesys.gloja.repository.CategoriaRepository;

@RestController
@RequestMapping("/api")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	/**
	 * Get /categorias : get all the categorias.
	 * 
	 * 
	 * @return the List of categorias in body and status 200 (OK).
	 */
	@GetMapping("/categorias")
	public List<Categoria> getAllCategorias() {
		return categoriaRepository.findAll();
	}

}
