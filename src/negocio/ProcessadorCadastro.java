package negocio;

import database.GenericDAOImpl;
import database.IGenericDAO;
import dominio.ObjetoPersistivel;

/** 
 * Classe capaz de realizar cadastro/edição de quaisquer entidades do sistema.
 * @author Renan
 */
public class ProcessadorCadastro extends ProcessadorComando {
	
	/** 
	 * Objeto que se quer cadastrar/editar no banco. eu sou gay
	 */
	protected ObjetoPersistivel obj;
	
	@Override
	protected void iniciarExecucao() {
		IGenericDAO dao = new GenericDAOImpl();
		
		if (obj.getId() == 0){
			dao.create(obj);
		} else {
			dao.update(obj);
		}
	}

	@Override
	protected Object getResultado() {
		return obj;
	}

	public void setObj(ObjetoPersistivel obj) {
		this.obj = obj;
	}
	
}
