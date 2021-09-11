package br.com.casadocodigo.loja.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.dao.LivroDao;
import br.com.casadocodigo.loja.model.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();
	
	@Inject
	private LivroDao livroDao;

	@Transactional
	public void salvar() {
		livroDao.salvar(livro);
		System.out.println("Livro Salvo "+livro.toString());
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
