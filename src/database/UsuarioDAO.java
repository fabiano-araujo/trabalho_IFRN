package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dominio.ObjetoPersistivel;
import models.Usuario;
import util.ValidatorUtil;

/**Classe para objetos do tipo UsuarioDAO, 
 * responsavel pelas alterações da tabela usuario
 * @author Fabiano de Araujo
 * @version 1.0
 * @since Release 01 da aplicaÃ§Ã£o
 */
	public class UsuarioDAO implements IGenericDAO{
		
		 /**Método para encontrar o usuario
        * @author Jefferson         
        * @param  login String - email do login.
        * @param  senha String - senha do login.
        * @return Usuario - usuario logged
         */
		
		public Usuario findUsuarioByLoginSenha(String login, String senha){
			
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("RedeSocialDB"); //nome que estï¿½ no arquivo persistence.xml
			EntityManager em = emf.createEntityManager();
			
			String hql = "SELECT usuario ";
			hql += " FROM Usuario usuario WHERE usuario.email = :login and usuario.senha = :senha ";
			
			Query q = em.createQuery(hql);
			q.setParameter("login", login);
			q.setParameter("senha", senha);
			
			try {
				Usuario usuario = (Usuario) q.getSingleResult();
				return usuario;
			} catch (NoResultException e){
				return null;
			}
		}
		/*public boolean inserirUsuario(Usuario usuario){
			try{
				EntityManagerFactory emf = Persistence
						.createEntityManagerFactory("RedeSocialDB"); //nome que estï¿½ no arquivo persistence.xml
				EntityManager em = emf.createEntityManager();
				
				
				
				String hql = "SELECT usuario";
				hql += " FROM Usuario usuario "
						+"WHERE usuario.email = :login "
						+"and usuario.senha = :senha ";
						
				Query q = em.createQuery(hql);
				q.setParameter("login", login);
				q.setParameter("senha", senha);
				try{
					Usuario usuario = (Usuario) q.getSingleResult();
					return usuario;
				}catch (NoResultException e) {
					return null;
				}		
		
				
				String query = " insert into usuario (nome,dt_nascimento,endereco,telefone,email,senha)"
					        + " values (?, ?, ?, ?, ?, ?)";				      
			    PreparedStatement preparedStmt = conexao.prepareStatement(query);
			    
			    preparedStmt.setString (1, usuario.getNome());
			    preparedStmt.setString(2, usuario.getDt_nascimento());
			    preparedStmt.setString(3, usuario.getEndereco());
			    preparedStmt.setString(4, usuario.getTelefone());
			    preparedStmt.setString(5, usuario.getEmail());
			    preparedStmt.setString(6, usuario.getSenha());
			    
			    
	 			PreparedStatement comando = conexao.prepareStatement ( query ,
	 					Statement . RETURN_GENERATED_KEYS ) ; 			
			    preparedStmt.execute();
	 			
	 			ResultSet generatedKeys = comando.getGeneratedKeys();
	 			generatedKeys.next(); 			
	 			System.out.println("ID: "+generatedKeys.getLong(1)); 			
	 			conexao.close();				
				return true;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}		
	}
	public List<Usuario> findUsuarioGeral(String email, String nome, String sobrenome, 
			String ordenarPor){
		EntityManager em = getEm();
		
		String hql = "SELECT u ";
		hql += " FROM Usuario u WHERE 1=1";
		
		if (ValidatorUtil.isNotEmpty(email)){
			hql += " AND u.email = :email ";
		}
		if (ValidatorUtil.isNotEmpty(nome)){
			hql += " AND upper(u.pessoa.nome) like :nome ";
		}
		if (ValidatorUtil.isNotEmpty(sobrenome)){
			hql += " AND upper(u.pessoa.sobrenome) like :sobrenome ";
		}
		if (ValidatorUtil.isNotEmpty(ordenarPor)){
			hql += " ORDER BY :ordenarPor ";
		}
		
		Query q = em.createQuery(hql);
		
		if (ValidatorUtil.isNotEmpty(email)){
			q.setParameter("email", email);
		}
		if (ValidatorUtil.isNotEmpty(nome)){
			q.setParameter("nome", "%" + nome.toUpperCase() + "%");
		}
		if (ValidatorUtil.isNotEmpty(sobrenome)){
			q.setParameter("sobrenome", "%" + sobrenome.toUpperCase() + "%");
		}
		if (ValidatorUtil.isNotEmpty(ordenarPor)){
			q.setParameter("ordernarPor", ordenarPor);
		}
		
		try {
			@SuppressWarnings("unchecked")
			List<Usuario> result = q.getResultList();
			
			return result;
		} catch (NoResultException e){
			return null;
		}
	}*/

		@Override
		public EntityManager getEm() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void detach(ObjetoPersistivel p) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void refresh(ObjetoPersistivel p) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void create(ObjetoPersistivel c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void update(ObjetoPersistivel c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void createOrUpdate(ObjetoPersistivel c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(ObjetoPersistivel c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void update(String sql) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <T extends ObjetoPersistivel> T findByPrimaryKey(int id, Class<T> classe) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends ObjetoPersistivel> List<T> findAll(Class<T> classe) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends ObjetoPersistivel> List<T> findAllAtivos(Class<T> classe) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends ObjetoPersistivel> List<T> findAllAtivos(Class<T> classe, String orderBy) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends ObjetoPersistivel> List<T> findAllLike(String coluna, String valor, String orderby,
				Class<T> classe) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends ObjetoPersistivel> List<T> findByExactField(String coluna, Object valor, Class<T> classe) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends ObjetoPersistivel> List<T> findByExactField(String coluna, Object valor, String orderBy,
				Class<T> classe) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends ObjetoPersistivel> List<T> findByExactFields(String[] colunas, Object[] valores,
				Class<T> classe) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends ObjetoPersistivel> T findByExactFields(String[] colunas, Object[] valores, boolean limit,
				Class<T> classe) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends ObjetoPersistivel> void updateField(Class<T> classe, int id, String coluna, Object valor) {
			// TODO Auto-generated method stub
			
		}
}
