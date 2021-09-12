package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.dao.AutoDao;
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

	@Inject
	private AutoDao autorDao;

	@Inject
	private FacesContext context;

	@Transactional
	public String salvar() {
		for (Long autorId : autoresId) {
			livro.getAutores().add(new Autor(autorId));
		}

		System.out.println(autoresId);
		livroDao.salvar(livro);
		System.out.println("Livro Salvo " + livro.toString());

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));

		return "/livros/lista?faces-redirect=true";
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public List<Long> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Long> autoresId) {
		this.autoresId = autoresId;
	}

}
