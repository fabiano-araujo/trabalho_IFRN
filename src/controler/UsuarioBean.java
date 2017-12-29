package controler;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import arq.AbstractController;
import database.DAO;
import database.MConnection;
import database.UsuarioDAO;
import models.Arquivo;
import models.Usuario;
import negocio.ProcessadorCadastro;
import util.CriptografiaUtils;
import util.ValidatorUtil;

/**Classe para realizar operações referente ao objeto Usuario
 * @author Fabiano de Araujo
 * @version 1.0
 * @since Release 01 da aplicação
 */
@ManagedBean
@SessionScoped
public class UsuarioBean extends AbstractController{	
	private Usuario usuario;

	@PostConstruct
	public void init() {		
		usuario = new Usuario();
	}


    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        usuario.setDt_nascimento(format.format(event.getObject()));
    }
    
	/**Método para add novo usuario
     * @author Fabiano               
     * @return String pagina 
     */
	public String cadastrar(){
		try{					
			usuario.setSenha(CriptografiaUtils.criptografarMD5(usuario.getSenha()));
			EntityManager em = MConnection.getConection();

			if (usuario.getArquivo() != null && ValidatorUtil.isNotEmpty(usuario.getArquivo().getFileName())
					&& usuario.getArquivo().getSize() != -1){
				
				//Cria nova entidade arquivo
				Arquivo arq = new Arquivo();
				arq.setNome(usuario.getArquivo().getFileName());
				arq.setBytes(usuario.getArquivo().getContents());
				
				em.getTransaction().begin();
				em.persist(arq);					
				em.getTransaction().commit();
				
				//Informa o ID da foto do usuário, para que possa ser carregada posteriormente
				usuario.setIdFoto(arq.getId());			
			}
						
			em.getTransaction().begin();
			if(usuario.getId() == 0){
				em.persist(usuario);
			}else{
				em.merge(usuario);
			}						
			em.getTransaction().commit();
			//usuario = em.find(Usuario.class,usuario.getId());
			usuario.setLogged(true);
			usuario.setSenha("");
			getCurrentSession().setAttribute("usuarioLogado", usuario);
			return "index";
		}catch (Exception e) {
			e.printStackTrace();
			addMsgError(e.getMessage());
			usuario.setLogged(false);
			return null;
		}									
	}
	
	/**Método para alterar usuario
     * @author Fabiano                   
     */
	
	public void update(){
		try{					
			EntityManager em = MConnection.getConection(); 					
			em.getTransaction().begin();
			em.merge(usuario);					
			em.getTransaction().commit();									
		}catch (Exception e) {
			e.printStackTrace();
		}						
		
		/*UsuarioDAO dao = new UsuarioDAO();
		if(dao.inserirUsuario(usuario)){
			usuario.setLogged(true);
			return "index";
		}else{
			usuario.setLogged(false);
			return "criar_conta";
		}*/
	}
	
	/**Método para deletar usuario
     * @author Fabiano                   
     */
	
	public void delete(){
		try{					
			EntityManager em = MConnection.getConection(); 					
			em.getTransaction().begin();
			em.remove(usuario);					
			em.getTransaction().commit();									
		}catch (Exception e) {
			e.printStackTrace();
		}						
		
		/*UsuarioDAO dao = new UsuarioDAO();
		if(dao.inserirUsuario(usuario)){
			usuario.setLogged(true);
			return "index";
		}else{
			usuario.setLogged(false);
			return "criar_conta";
		}*/
	}	
	
	public String autenticar(){		
		System.out.println("1");
		if (!validarLogin()){
			return null;
		}
		System.out.println("2");
		try {
			
		
			String hql = "SELECT usuario ";
			hql += " FROM Usuario usuario WHERE usuario.email = :login and usuario.senha = :senha ";
			
			System.out.println("SELECT usuario FROM Usuario usuario WHERE usuario.email = '"+
					usuario.getEmail()+"' and usuario.senha = '"+CriptografiaUtils.criptografarMD5(usuario.getSenha())+"'");
			EntityManager em = MConnection.getConection();
			Query q = em.createQuery(hql);
			q.setParameter("login", usuario.getEmail());
			q.setParameter("senha", CriptografiaUtils.criptografarMD5(usuario.getSenha()));
			
			try {
				usuario = (Usuario) q.getSingleResult();				
			} catch (NoResultException e){
				usuario = null;
			}
			
					
			if (ValidatorUtil.isEmpty(usuario)){
				usuario = new Usuario();
				addMsgError("Usuário/Senha incorretos.");
				usuario.setLogged(false);
				System.out.println("login");				
				getCurrentSession().setAttribute("usuarioLogado", null);
				return null;									
			}else{
				System.out.println("index");
				usuario.setLogged(true);
				getCurrentSession().setAttribute("usuarioLogado", usuario);
				return "index";					
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			tratamentoErroPadrao(e);
			return null;
		} 		
	}
	public boolean validarLogin(){
		if (usuario == null || (ValidatorUtil.isEmpty(usuario.getEmail()) && 
				ValidatorUtil.isEmpty(usuario.getSenha()))){
			addMsgError("Usuário/senha não informados.");
			return false;
		}
		
		if (ValidatorUtil.isEmpty(usuario.getEmail())){
			addMsgError("Usuário: campo obrigatório não informado.");
			return false;
		}
		
		if (ValidatorUtil.isEmpty(usuario.getSenha())){
			addMsgError("Senha: campo obrigatório não informado.");
			return false;
		}
		
		return true;
	}
	public void redirencionar() throws IOException{
		System.out.println("ok");
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String loggout(){		
		getCurrentSession().setAttribute("usuarioLogado", null);
		setUsuario(new Usuario());
		return "index";
	}
}
