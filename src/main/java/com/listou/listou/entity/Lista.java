package com.listou.listou.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
	private String isRecorrente;
	private String recorrencia;
	private Double orcamento;
	@ManyToOne
	@JoinColumn(name="userListou_id", nullable=false)
    private UserListou userListou;
	
	@OneToMany(mappedBy="lista")
	private Set<Item> items;
	
	/**
	 * @return the userListou
	 */
	public UserListou getUserListou() {
		return userListou;
	}
	/**
	 * @param userListou the userListou to set
	 */
	public void setUserListou(UserListou userListou) {
		this.userListou = userListou;
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
	 * @return the isRecorrente
	 */
	public String getIsRecorrente() {
		return isRecorrente;
	}
	/**
	 * @param isRecorrente the isRecorrente to set
	 */
	public void setIsRecorrente(String isRecorrente) {
		this.isRecorrente = isRecorrente;
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
	 * @return the items
	 */
	public Set<Item> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(Set<Item> items) {
		this.items = items;
	}
}
