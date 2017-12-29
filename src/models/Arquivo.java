package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dominio.ObjetoPersistivel;


/** 
 * Entidade responsável por armazenar um arquivo no banco de dados.
 * @author Renan
 */
@SuppressWarnings("serial")
@Entity
public class Arquivo implements ObjetoPersistivel {
	
	@Id
    @GeneratedValue  
	@Column(name="id_arquivo", nullable = false)
	private int id;
	
	@Column(nullable = false)
	private String nome;
	
	/** Bytes do arquivo. */
	@Column(nullable = false, columnDefinition="LONGBLOB")
	private byte[] bytes;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
}
