package br.com.slifesys.gloja.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.slifesys.gloja.model.Categoria;
import br.com.slifesys.gloja.repository.CategoriaRepository;
import br.com.slifesys.gloja.resource.util.PaginationUtil;
import br.com.slifesys.gloja.service.CategoriaService;

@RestController
@RequestMapping("/api")
public class CategoriaResource {


    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }



	/**
	 * Get /categorias : get all the categorias.
	 * 
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of categorias in body
	 */
	@GetMapping("/categorias")
	public ResponseEntity<List<Categoria>> getAllCategorias( Pageable pageable) {
		Page<Categoria> page = categoriaService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"/api/categorias");
		
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
	
	/* example without paging
	 * 
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		 Categoria categoria = categoriaRepository.findOne(codigo);
		 return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}
	*/
}
