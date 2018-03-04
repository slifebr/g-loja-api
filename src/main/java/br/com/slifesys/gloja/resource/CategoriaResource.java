package br.com.slifesys.gloja.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.slifesys.gloja.model.Categoria;
import br.com.slifesys.gloja.resource.util.HeaderUtil;
import br.com.slifesys.gloja.resource.util.PaginationUtil;
import br.com.slifesys.gloja.resource.util.ResponseUtil;
import br.com.slifesys.gloja.service.CategoriaService;

@RestController
@RequestMapping("/api")
public class CategoriaResource {

    private static final String ENTITY_NAME = "categoria";
    
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
	
    /**
     * GET  /categorias/:id : get the "id" categoria.
     *
     * @param id the id of the categoria to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categoria, 
     * or with status 404 (Not Found)
     */
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Long id) {
        //log.debug("REST request to get Categoria : {}", id);
        Categoria categoria = categoriaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(categoria));
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
	
	
	/**
	 * POST /categorias : Create a new categoria
	 * 
	 * @param categoria the categoria to create
	 * @return The ResponseEntity with status 201 (Created) and with body the new categoria,
	 * 		   or with status 400 (Bad Request) if the categoria has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
    @PostMapping("/categorias")
	public ResponseEntity<Categoria> createCategoria( @Valid @RequestBody Categoria categoria) throws URISyntaxException {
		if (categoria.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil
					.createFailureAlert(ENTITY_NAME, "idexists", "Uma categoria nova não pode ter um ID")).body(null);
		}
		Categoria result = categoriaService.save(categoria);
		return ResponseEntity.created(new URI("/api/categorias/"+result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	
    /**
     * PUT  /categorias : Updates an existing categoria.
     *
     * @param categoria the categoria to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoria,
     * or with status 400 (Bad Request) if the categoria is not valid,
     * or with status 500 (Internal Server Error) if the categoria couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/categorias")
    public ResponseEntity<Categoria> updateCategoria(@Valid @RequestBody Categoria categoria) throws URISyntaxException {
        //log.debug("REST request to update Categoria : {}", categoria);
        if (categoria.getId() == null) {
            return createCategoria(categoria);
        }
        Categoria result = categoriaService.save(categoria);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categoria.getId().toString()))
            .body(result);
    }
    
    /**
     * DELETE  /categorias/:id : delete the "id" categoria.
     *
     * @param id the id of the categoria to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        //log.debug("REST request to delete Categoria : {}", id);
    	Categoria categoria = categoriaService.findOne(id);
    	if (categoria == null) {
           return ResponseEntity.notFound().headers(HeaderUtil
        		                           .createFailureAlert(ENTITY_NAME, "idnotexists", "Uma categoria tem que existir para ser excluida")).build();
    	}
        categoriaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil
        		                            .createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }    
}
