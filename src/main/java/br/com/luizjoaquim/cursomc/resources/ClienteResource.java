package br.com.luizjoaquim.cursomc.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizjoaquim.cursomc.domain.Cliente;
import br.com.luizjoaquim.cursomc.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente obj = service.buscar(id);
		
	/*	Cliente cat1 = new Cliente(1,"Farmacia");
		Cliente cat2 = new Cliente(2,"Escritorio");
		List<Cliente>lista=new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);*/
		
		return ResponseEntity.ok().body(obj);//retorna o objeto dentro de um http
	}
}
