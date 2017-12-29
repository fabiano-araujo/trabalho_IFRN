package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.Transient;

import org.primefaces.model.UploadedFile;

import database.MConnection;
import dominio.ObjetoPersistivel;
import util.CriptografiaUtils;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Produto implements ObjetoPersistivel{
	@Id
    @GeneratedValue  
	@Column(name="id_produto", nullable = false)
	private int id_produto;
	
	@Column(nullable = false)
	private String nome;	
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private double preco;
	
	@Column(nullable = false)
	private String categoria;
		
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable=false)
	private Usuario usuario;
	
	
	@Column(name="id_foto")
	private Integer idFoto;
	
	/** 
	 * Atributo não persisitido que armazena uma foto que o usuário deseja
	 * para seu perfil.
	 * */
	@Transient
	private UploadedFile arquivo;
	
	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public String getUrlFotoUsuario(){
		return "/verArquivo?"
				+ "idArquivo=" + getIdFoto() //id_usuario do arquivo
	 			+"&key=" + CriptografiaUtils.criptografarMD5(String.valueOf(getIdFoto())) //chave criptografada para acesso à imagem 
				+ "&salvar=false"; 
	}

	public Integer getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(Integer idFoto) {
		this.idFoto = idFoto;
	}
	
	public String getCategoria() {		
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}	
	private String condicao;
	
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getCondicao() {
		return condicao;
	}
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id_produto;
	}
	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}	
}
