package dominio;

import java.io.Serializable;

/** 
 * Interface que deve ser implementada por todas as entidades do sistema.
 * @author Renan
 */
public interface ObjetoPersistivel extends Serializable {

	public int getId();
	
	public void setId(int id);
}

