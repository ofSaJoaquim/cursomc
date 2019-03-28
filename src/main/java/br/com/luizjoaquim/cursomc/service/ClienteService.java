package br.com.luizjoaquim.cursomc.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luizjoaquim.cursomc.domain.Cliente;
import br.com.luizjoaquim.cursomc.repositories.ClienteRepository;
import br.com.luizjoaquim.cursomc.service.excepections.ObjectNotFoundException;

@Service
public class ClienteService {

@Autowired
private ClienteRepository repo;//automaticamente  instancianda pelo spring

	/*public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElse(null);
	}*/
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj= repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Cliente.class.getName()));
	}
}

