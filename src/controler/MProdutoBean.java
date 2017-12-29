package controler;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.jws.soap.InitParam;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Transient;
import org.primefaces.model.UploadedFile;
import arq.AbstractController;
import database.MConnection;
import models.Arquivo;
import models.Produto;
import models.Usuario;
import util.ValidatorUtil;


/**Classe para realizar operações referente ao objeto prouto
 * @author Fabiano de Araujo
 * @version 1.0
 * @since Release 01 da aplicação
 */

@ManagedBean
@ViewScoped
public class MProdutoBean extends AbstractController{
	private static final Object usuario = null;
	private Produto produto;
	private String search;
	
	 

	@PostConstruct
	public void init() {		
		produto = new Produto();
		EntityManager em = MConnection.getConection();
		String hql = null;
		Query q = null;
		Map<String, String> params =FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
							
		String id = params.get("id");		
		if(id != null){						
			try{
				produto = em.find(Produto.class,Integer.parseInt(id));
				System.out.println(produto.getId());				
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}	
	}
	
	public Produto getProduto() {
		return produto;
	}
	

	private UploadedFile arquivo;
	
	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}
	
	/**Método para pegar produtos de um usuario
     * @author Fabiano              * 
     * @return List<Produto> - lista de produto
      */
	public List<Produto> getProdutosUsuario() {
		EntityManager em = MConnection.getConection();
					
		Usuario usuario = (Usuario)getCurrentSession().getAttribute("usuarioLogado");
		//usuario = em.find(Usuario.class,usuario.getId());					
				
		String hql = "SELECT p FROM Produto p where p.usuario = "+usuario.getId()+" order by p.id_produto desc";
		Query q = em.createQuery(hql,Produto.class);					
		return q.getResultList();					
	}
	public List<Produto> getProdutos() {
		EntityManager em = MConnection.getConection();
		String where = "";
		String categoria;
		String hql = null;
		Query q = null;
		Map<String, String> params =FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
					
		categoria= params.get("categoria");
		String id = params.get("id");
		if(categoria != null){			
			hql = "SELECT p FROM Produto p where p.categoria = :categoria order by p.id_produto desc";
			q = em.createQuery(hql,Produto.class);	
			q.setParameter("categoria", categoria);
		}else if(id != null){			
			hql = "SELECT p FROM Produto p where p.id_produto = "+id+" order by p.id_produto desc";
			q = em.createQuery(hql,Produto.class);			
		}else{
			hql = "SELECT p FROM Produto p";
			q = em.createQuery(hql,Produto.class);			
		}
		return q.getResultList();					
	}
	public List<Produto> getProdutosSearch() {	
		System.out.println(search);
		if(search != null && search.length() > 0){
			search = search.toUpperCase();
			EntityManager em = MConnection.getConection();		
			
			String hql = "SELECT p FROM Produto p where upper(p.categoria) like :categoria or "
					+ "upper(p.nome) like :nome or "
					+ "upper(p.descricao) like :descricao "
					+ "order by p.id_produto desc";
			Query q = em.createQuery(hql,Produto.class);	
				q.setParameter("categoria", "%"+search+"%");
				q.setParameter("nome", "%"+search+"%");
				q.setParameter("descricao", "%"+search+"%");				
			List<Produto> produtos =q.getResultList();	
			System.out.println(produtos.size());
			return produtos;					
		}		
		return null;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}	
	
	public String cadastrar(){		
		try{						
			EntityManager em = MConnection.getConection();			
			Usuario usuario = (Usuario)getCurrentSession().getAttribute("usuarioLogado");
			produto.setUsuario(em.find(Usuario.class,usuario.getId()));			
			
			if (getArquivo() != null && ValidatorUtil.isNotEmpty(getArquivo().getFileName())
					&& getArquivo().getSize() != -1){
				
				//Cria nova entidade arquivo
				Arquivo arq = new Arquivo();
				arq.setNome(getArquivo().getFileName());
				arq.setBytes(getArquivo().getContents());
				
				em.getTransaction().begin();
				em.persist(arq);					
				em.getTransaction().commit();
				
				//Informa o ID da foto do usuário, para que possa ser carregada posteriormente
				produto.setIdFoto(arq.getId());			
			}
			em.getTransaction().begin();
							
			if(produto.getId() == 0){
				em.persist(produto);		
			}else{				
				em.merge(produto);				
			}					
			em.getTransaction().commit();				
			return "index.xhtml?categoria="+produto.getCategoria();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			produto = null;
		}	
	}
	public void update(){
		try{					
			EntityManager em = MConnection.getConection(); 					
			em.getTransaction().begin();
			em.merge(produto);					
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
	public void delete(){
		try{					
			EntityManager em = MConnection.getConection(); 					
			em.getTransaction().begin();
			em.remove(produto);					
			em.getTransaction().commit();									
		}catch (Exception e) {
			e.printStackTrace();
		}									
	}
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
