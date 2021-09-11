package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.model.Autor;

public class AutoDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Autor> listar()
	{
		return entityManager.createQuery("Select a From Autor a", Autor.class).getResultList();
	}
	
}


