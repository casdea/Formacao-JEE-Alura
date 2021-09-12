package leilao;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Leilao;

public class HelloWorldMockito {

	@Test
	void hello() {
		LeilaoDao leilaoDaoMock = Mockito.mock(LeilaoDao.class);
		List<Leilao> todos = leilaoDaoMock.buscarTodos();
		Assert.assertTrue(todos.isEmpty());
	}

}
