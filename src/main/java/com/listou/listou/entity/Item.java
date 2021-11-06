package com.listou.listou.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = -5940734063154232214L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private String nome;
	private int quantidade;
	private Double precoPretendidoUnd;
	
	@ManyToOne
	@JoinColumn(name="lista_id", nullable=false)
    private Lista lista;
	
	/**
	 * @return the lista
	 */
	public Lista getLista() {
		return lista;
	}
	/**
	 * @param lista the lista to set
	 */
	public void setLista(Lista lista) {
		this.lista = lista;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	/**
	 * @return the precoPretendidoUnd
	 */
	public Double getPrecoPretendidoUnd() {
		return precoPretendidoUnd;
	}
	/**
	 * @param precoPretendidoUnd the precoPretendidoUnd to set
	 */
	public void setPrecoPretendidoUnd(Double precoPretendidoUnd) {
		this.precoPretendidoUnd = precoPretendidoUnd;
	}
	 
}