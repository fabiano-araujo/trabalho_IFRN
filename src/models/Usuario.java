package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import org.primefaces.model.UploadedFile;

import dominio.ObjetoPersistivel;
import util.CriptografiaUtils;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Usuario implements ObjetoPersistivel {			  	 	  
	@Id
    @GeneratedValue  
	@Column(name="id_usuario", nullable = false)
	private int id_usuario;
	
	
	@Column(nullable = false)	
	private String nome;
	
	@Column(nullable = false)
	private String dt_nascimento;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(nullable = false)
	private String telefone;
	
	@Column(nullable = false)	
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private boolean logged = false;
	
	@Column(name="id_foto")
	private Integer idFoto;

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

	/*	@Transient
	private UploadedFile arquivo;
*/	
	public boolean getLogged() {
		return logged;
	}
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	public String getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimento(String dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id_usuario;
	}
	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}	
}
