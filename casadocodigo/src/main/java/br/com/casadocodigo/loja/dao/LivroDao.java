package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.model.Livro;

public class LivroDao {

	@PersistenceContext // (unitName="casadocodigo-dev")
	private EntityManager entityManager;

	public void salvar(Livro livro) {
		entityManager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "select distinct(l) from Livro l " + " join fetch l.autores";

		return entityManager.createQuery(jpql, Livro.class).getResultList();
	}

}
