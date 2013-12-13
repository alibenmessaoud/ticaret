package com.ticaret.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

public class DAO<T> {
	
	@PersistenceContext(unitName = "ticaret")
	public EntityManager em;
	private Class classe;
	static Logger l = Logger.getLogger(DAO.class.getName());

	public DAO(Class classe) {
		this.classe = classe;	
		em = Persistence.createEntityManagerFactory("ticaret").createEntityManager();
		//l.info(DAO.class + " <|-- " + classe.getName() + " : OK "); 
	}

	public T insert(T t) {
		em.getTransaction().begin();
		em.persist(t);		
		em.getTransaction().commit();
		//l.info( classe.getName() + " Insert (" + classe.getName() + " entity)"); 
		return t;
	}

	public T update(T t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		//l.info(classe.getName() + " update( " + classe.getName() + " entity) ");
		return t;
	}

	public T get(int id) {
		//l.info( classe.getName() + " get(int id = " + id + ") "); 
		return (T) em.find(classe, id);
	}

	public void remove(int id) {
		T t = get(id);
		if (t != null) {
			em.getTransaction().begin();
			em.remove(t);
			em.getTransaction().commit();
			//l.info("remove(int id = " + id + ") => " + classe.getName()); 
		}
	}

	public List<T> list() {
		//l.info("List<"+ classe.getName()+"> list()"); 
		return em.createQuery("SELECT e FROM " + classe.getSimpleName() + " e").getResultList();
	}
}