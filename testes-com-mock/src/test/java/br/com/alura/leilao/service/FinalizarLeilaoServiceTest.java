package br.com.alura.leilao.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import javassist.expr.NewArray;

class FinalizarLeilaoServiceTest {

	@Mock
	private LeilaoDao leilaoDao;
	
	private FinalizarLeilaoService finalizarLeilaoService;
	
	@BeforeEach
	public void beforeEach()
	{
		MockitoAnnotations.initMocks(this);
		this.finalizarLeilaoService = new FinalizarLeilaoService(leilaoDao);
	}
	
	@Test
	void deveriaFinalizarUmLeilao() {
		List<Leilao> leiloes = obterLeiloes();
		Mockito.when(leilaoDao.buscarLeiloesExpirados()).thenReturn(leiloes);
		
		finalizarLeilaoService.finalizarLeiloesExpirados();
		Leilao leilao = leiloes.get(0);
		Assert.assertTrue(leilao.isFechado());
		Assert.assertEquals(new BigDecimal("900"), leilao.getLanceVencedor().getValor());
		Mockito.verify(leilaoDao).salvar(leilao);
	}

	private List<Leilao> obterLeiloes()
	{
		List<Leilao> lista = new ArrayList<Leilao>();
		Leilao leilao = new Leilao("Celular", new BigDecimal("500"), new Usuario("Fulano"));
		
		Lance primeiro = new Lance(new Usuario("Beltrano"),new BigDecimal("600"));
		Lance segundo = new Lance(new Usuario("Ciclano"),new BigDecimal("900"));
		
		leilao.propoe(primeiro);
		leilao.propoe(segundo);
		
		lista.add(leilao);
		return lista;
	}
	
}