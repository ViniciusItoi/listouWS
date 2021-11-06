package com.listou.listou.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lista implements Serializable {

	private static final long serialVersionUID = -1424883150213012979L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String descricao;
	private String dtCompra;
	private boolean recorrente;
	private String recorrencia;
	private Double orcamento;
	@ElementCollection
	private List<Long> itemsListaId;
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the dtCompra
	 */
	public String getDtCompra() {
		return dtCompra;
	}
	/**
	 * @param dtCompra the dtCompra to set
	 */
	public void setDtCompra(String dtCompra) {
		this.dtCompra = dtCompra;
	}
	/**
	 * @return the recorrente
	 */
	public boolean isRecorrente() {
		return recorrente;
	}
	/**
	 * @param recorrente the recorrente to set
	 */
	public void setRecorrente(boolean recorrente) {
		this.recorrente = recorrente;
	}
	/**
	 * @return the recorrencia
	 */
	public String getRecorrencia() {
		return recorrencia;
	}
	/**
	 * @param recorrencia the recorrencia to set
	 */
	public void setRecorrencia(String recorrencia) {
		this.recorrencia = recorrencia;
	}
	/**
	 * @return the orcamento
	 */
	public Double getOrcamento() {
		return orcamento;
	}
	/**
	 * @param orcamento the orcamento to set
	 */
	public void setOrcamento(Double orcamento) {
		this.orcamento = orcamento;
	}
	/**
	 * @return the itemsListaId
	 */
	public List<Long> getItemsListaId() {
		return itemsListaId;
	}
	/**
	 * @param itemsListaId the itemsListaId to set
	 */
	public void setItemsListaId(List<Long> itemsListaId) {
		this.itemsListaId = itemsListaId;
	}
}
