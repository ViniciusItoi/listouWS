package com.listou.listou.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemLista {

	private static final long serialVersionUID = 8359799885670176176L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int qtdItems = 1;
	private long itemId;
	
	/**
	 * @return the qtdItems
	 */
	public int getQtdItems() {
		return qtdItems;
	}
	/**
	 * @param qtdItems the qtdItems to set
	 */
	public void setQtdItems(int qtdItems) {
		this.qtdItems = qtdItems;
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
	 * @return the itemId
	 */
	public long getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	
}
