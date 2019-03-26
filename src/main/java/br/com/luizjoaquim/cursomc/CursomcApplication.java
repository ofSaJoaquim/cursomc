package br.com.luizjoaquim.cursomc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.luizjoaquim.cursomc.domain.Categoria;
import br.com.luizjoaquim.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
//CommandLineRunner  executa o metodo run antes de subir a aplicação
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"escritorio");
		Categoria cat2 = new Categoria(null,"peças");
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	}

}
