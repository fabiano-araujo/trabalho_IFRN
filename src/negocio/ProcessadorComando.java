package negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import database.Database;
import models.Usuario;



/**
 * Classe que representa um processador de dados. Deve ser estendido
 * por todos os outros processadores do sistema.
 * � esta class quem realiza opera��es cruciais do sistema, como
 * cadastro, edi��o, remo��o, etc.
 * 
 * @author Renan
 *
 */
public abstract class ProcessadorComando {
	
	/** 
	 * M�todo principal que ir� iniciar a execu��o dos processamentos 
	 * dos processadores filhos. � o m�todo que deve ser chamado para 
	 * iniciar o processamento.
	 * @throws Exception 
	 * 
	 * @throws ArqException 
	 * @throws NegocioException 
	 */
	public final Object execute() throws Exception {
		
		
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("RedeSocialDB"); //nome que est� no arquivo persistence.xml
			EntityManager em = emf.createEntityManager();
					
			em.getTransaction().begin();
			em.persist(new Usuario());
			iniciarExecucao();
			
			em.getTransaction().commit();
			
			return getResultado();
			
	}
	
	/** M�todo que os processadores filhos devem implementar para realizar as opera��es necess�rias. */
	protected abstract void iniciarExecucao() throws Exception;
	
	/** 
	 * M�todo que deve ser implementado pelos processadores filhos para retornar algum dado
	 * para quem chamou o processador. 
	 */
	protected abstract Object getResultado();
	
}
