package br.com.luizjoaquim.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.luizjoaquim.cursomc.domain.Categoria;
import br.com.luizjoaquim.cursomc.domain.Cidade;
import br.com.luizjoaquim.cursomc.domain.Cliente;
import br.com.luizjoaquim.cursomc.domain.Endereco;
import br.com.luizjoaquim.cursomc.domain.Estado;
import br.com.luizjoaquim.cursomc.domain.Pagamento;
import br.com.luizjoaquim.cursomc.domain.PagamentoComBoleto;
import br.com.luizjoaquim.cursomc.domain.PagamentoComCartao;
import br.com.luizjoaquim.cursomc.domain.Pedido;
import br.com.luizjoaquim.cursomc.domain.Produto;
import br.com.luizjoaquim.cursomc.domain.enums.EstadoPagamento;
import br.com.luizjoaquim.cursomc.domain.enums.TipoCliente;
import br.com.luizjoaquim.cursomc.repositories.CategoriaRepository;
import br.com.luizjoaquim.cursomc.repositories.CidadeRepository;
import br.com.luizjoaquim.cursomc.repositories.ClienteRepository;
import br.com.luizjoaquim.cursomc.repositories.EnderecoRepository;
import br.com.luizjoaquim.cursomc.repositories.EstadoRepository;
import br.com.luizjoaquim.cursomc.repositories.PagamentoRepository;
import br.com.luizjoaquim.cursomc.repositories.PedidoRepository;
import br.com.luizjoaquim.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
//CommandLineRunner  executa o metodo run antes de subir a aplicação

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository ;
	
	@Autowired 
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "escritorio");
		Categoria cat2 = new Categoria(null, "peças");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().add(c1);
		e2.getCidades().addAll(Arrays.asList(c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "APTO 303", "Jardim", "38220834", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PEDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgto2);
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1,pgto2));
		
	}

}
