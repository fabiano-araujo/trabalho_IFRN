package negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import database.Database;
import models.Usuario;



/**
 * Classe que representa um processador de dados. Deve ser estendido
 * por todos os outros processadores do sistema.
 * É esta class quem realiza operações cruciais do sistema, como
 * cadastro, edição, remoção, etc.
 * 
 * @author Renan
 *
 */
public abstract class ProcessadorComando {
	
	/** 
	 * Método principal que irá iniciar a execução dos processamentos 
	 * dos processadores filhos. É o método que deve ser chamado para 
	 * iniciar o processamento.
	 * @throws Exception 
	 * 
	 * @throws ArqException 
	 * @throws NegocioException 
	 */
	public final Object execute() throws Exception {
		
		
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("RedeSocialDB"); //nome que está no arquivo persistence.xml
			EntityManager em = emf.createEntityManager();
					
			em.getTransaction().begin();
			em.persist(new Usuario());
			iniciarExecucao();
			
			em.getTransaction().commit();
			
			return getResultado();
			
	}
	
	/** Método que os processadores filhos devem implementar para realizar as operações necessárias. */
	protected abstract void iniciarExecucao() throws Exception;
	
	/** 
	 * Método que deve ser implementado pelos processadores filhos para retornar algum dado
	 * para quem chamou o processador. 
	 */
	protected abstract Object getResultado();
	
}
