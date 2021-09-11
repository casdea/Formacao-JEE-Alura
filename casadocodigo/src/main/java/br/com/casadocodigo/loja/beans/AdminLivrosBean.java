package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.dao.LivroDao;
import br.com.casadocodigo.loja.model.Autor;
import br.com.casadocodigo.loja.model.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	private List<Long> autoresId = new ArrayList<Long>();

	@Inject
	private LivroDao livroDao;

	@Transactional
	public void salvar() {
		System.out.println(autoresId);
		livroDao.salvar(livro);
		System.out.println("Livro Salvo " + livro.toString());
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Autor> getAutores() {
		return Arrays.asList(new Autor(1L, "CARLOS"), new Autor(2L, "MANOEL"));
	}

	public List<Long> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Long> autoresId) {
		this.autoresId = autoresId;
	}

}
