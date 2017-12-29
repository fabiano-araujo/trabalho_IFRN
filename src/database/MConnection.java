package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**Classe para objetos do tipo MConnection, 
 * responsavel por realizar conex„o com o banco.
 * @author Fabiano de Araujo
 * @version 1.0
 * @since Release 01 da aplica√ß√£o
 */
public class MConnection {
	
	 /**MÈtodo para criar conex„o
     * @author Jefferson                       
     * @return EntityManager - Conex„o com o banco
      */
	public static EntityManager getConection(){		
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("RedeSocialDB"); //nome que est· no arquivo persistence.xml
			EntityManager em = emf.createEntityManager();
			
			/*String stringDeConexao = "jdbc:mysql://localhost:3306/loja";
			String usuario = "root";
			String senha = "";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexao = DriverManager.getConnection(stringDeConexao , usuario , senha ) ;*/
			return em;
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
			return null;
		}		
	}
}
