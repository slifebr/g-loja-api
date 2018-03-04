package br.com.slifesys.gloja.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.slifesys.gloja.model.Categoria;
import br.com.slifesys.gloja.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {
	

    private  final CategoriaRepository categoriaRepository;

	public CategoriaService( CategoriaRepository categoriaRepository ) {
      this.categoriaRepository = categoriaRepository;
	}


	/**
     *  Get all the categorias.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }
    
    /**
     *  Get one categoria by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Categoria findOne(Long id) {
        //log.debug("Request to get Categoria : {}", id);
        return categoriaRepository.findOne(id);
    }
    
    /**
     * Save the categoria
     * 
     * @param categoria the entity to save
     * @return the persisted entity
     */
    public Categoria save(Categoria categoria) {
    	return categoriaRepository.save(categoria);
    }

    /**
     *  Delete the  categoria by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        //log.debug("Request to delete Categoria : {}", id);
        categoriaRepository.delete(id);
    }	
}
