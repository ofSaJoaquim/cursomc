package br.com.luizjoaquim.cursomc.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luizjoaquim.cursomc.domain.Categoria;
import br.com.luizjoaquim.cursomc.repositories.CategoriaRepository;
import br.com.luizjoaquim.cursomc.service.excepections.ObjectNotFoundException;

@Service
public class CategoriaService {

@Autowired
private CategoriaRepository repo;//automaticamente  instancianda pelo spring

	/*public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}*/
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj= repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Categoria.class.getName()));
	}
}

