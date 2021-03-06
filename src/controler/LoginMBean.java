package controler;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import arq.AbstractController;
import database.UsuarioDAO;
import models.Usuario;
import util.CriptografiaUtils;
import util.ValidatorUtil;

/**
 * MBean que controla o login no sistema. 
 * @author Renan
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LoginMBean extends AbstractController {
	
	/** Armazena os dados informados na tela de login. */
	private Usuario usuario;
	
	/** Armazena os dados iniciais de cadastro do usu�rio. */
	private Usuario usuarioCadastro;
	
	@PostConstruct
	private void init(){
		usuario = new Usuario();
		usuarioCadastro = new Usuario();				
	}
	
	/** Autentica o usu�rio e faz login no sistema. */
	public String autenticar(){
		if (!validarLogin()){
			return null;
		}
		
		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuario = dao.findUsuarioByLoginSenha(usuario.getEmail(), CriptografiaUtils.criptografarMD5(usuario.getSenha()));
			
			if (!ValidatorUtil.isEmpty(usuario)){
				return null;				
			} else {
				init();
				//addMsgError("Usu�rio/Senha incorretos.");
				return null;
			}
			
			
		} catch (Exception e) {
			//tratamentoErroPadrao(e);
			return null;
		} 
	}
	
	/** Verifica se os dados para login est�o corretos */
	public boolean validarLogin(){
		if (usuario == null || (ValidatorUtil.isEmpty(usuario.getEmail()) && 
				ValidatorUtil.isEmpty(usuario.getSenha()))){
			addMsgError("Usu�rio/senha n�o informados.");
			return false;
		}
		
		if (ValidatorUtil.isEmpty(usuario.getEmail())){
			addMsgError("Usu�rio: campo obrigat�rio n�o informado.");
			return false;
		}
		
		if (ValidatorUtil.isEmpty(usuario.getSenha())){
			addMsgError("Senha: campo obrigat�rio n�o informado.");
			return false;
		}
		
		return true;
	}
	
	/** Realiza logoff do sistema. */
	public String logoff(){
		//getCurrentSession().invalidate();
		return Paginas.LOGIN_PAGE;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

}
