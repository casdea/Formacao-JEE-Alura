package br.com.alura.leilao.service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Pagamento;
import br.com.alura.leilao.model.Usuario;

class GeradorDePagamentoTest {

	@Mock
	private PagamentoDao mockPagamentoDao;

	private GeradorDePagamento geradorDePagamento;
	
	@Captor
	private ArgumentCaptor<Pagamento> captorPagamento;

	@Mock
	private Clock clock;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.geradorDePagamento = new GeradorDePagamento(mockPagamentoDao, clock);
	}
	
	@Test
	void deveriaCriarPagamentoParaVencedorDoLeilao() {
		Leilao leilao = obterLeilao();
		Lance lanceVencedor = leilao.getLanceVencedor();
		 
		this.geradorDePagamento.gerarPagamento(lanceVencedor);
		Mockito.verify(mockPagamentoDao).salvar(captorPagamento.capture());
		
		Pagamento pagamento = captorPagamento.getValue();
		Assert.assertEquals(LocalDate.now().plusDays(1), pagamento.getVencimento());
		Assert.assertEquals(lanceVencedor.getValor(), pagamento.getValor());
		Assert.assertFalse(pagamento.getPago());
		Assert.assertEquals(lanceVencedor.getUsuario(), pagamento.getUsuario());
		Assert.assertEquals(leilao, pagamento.getLeilao());
	}
	
	@Test
	void deveriaCriarPagamentoParaVencedorDoLeilaoUtilizandoClock() {
		Leilao leilao = obterLeilao();
		Lance vencedor = leilao.getLanceVencedor();
		
		LocalDate data = LocalDate.of(2020, 12, 7);
		
		Instant instant  = data.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		Mockito.when(clock.instant()).thenReturn(instant);
		Mockito.when(clock.getZone()).thenReturn(ZoneId.systemDefault());
		
		geradorDePagamento.gerarPagamento(vencedor);
		
		Mockito.verify(mockPagamentoDao).salvar(captorPagamento.capture());
		
		Pagamento pagamento = captorPagamento.getValue();
		
		Assert.assertEquals(LocalDate.now().plusDays(1), 
				pagamento.getVencimento());
		Assert.assertEquals(vencedor.getValor(), pagamento.getValor());
		Assert.assertFalse(pagamento.getPago());
		Assert.assertEquals(vencedor.getUsuario(), pagamento.getUsuario());
		Assert.assertEquals(leilao, pagamento.getLeilao());
	}

	private Leilao obterLeilao() {
		Leilao leilao = new Leilao("Celular", new BigDecimal("500"), new Usuario("Fulano"));

		Lance lance = new Lance(new Usuario("Beltrano"), new BigDecimal("600"));

		leilao.propoe(lance);
		leilao.setLanceVencedor(lance);

		return leilao;
	}

	
	
}
