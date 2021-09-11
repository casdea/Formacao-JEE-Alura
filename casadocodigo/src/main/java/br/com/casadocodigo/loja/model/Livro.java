package br.com.casadocodigo.loja.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@Lob
	private String descricao;
	private BigDecimal preco;
	private Integer nrPaginas;

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNrPaginas() {
		return nrPaginas;
	}

	public void setNrPaginas(Integer nrPaginas) {
		this.nrPaginas = nrPaginas;
	}

	@Override
	public String toString() {
		return "Livro [titulo=" + titulo + ", descricao=" + descricao + ", preco=" + preco + ", nrPaginas=" + nrPaginas
				+ "]";
	}

}
