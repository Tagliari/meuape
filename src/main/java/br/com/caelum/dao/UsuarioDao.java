//package br.com.caelum.dao;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.persistence.EntityManager;
//
//import br.com.caelum.model.Usuario;
//
//@ApplicationScoped
//public class UsuarioDao {
//	
//	public UsuarioDao(){
//		
//	}
//
//	// private EntityManager em = new EntityManagerCreator().getInstance();
//
//	private EntityManager em;
//
//	public UsuarioDao(EntityManager em) {
//		this.em = em;
//	}
//
//	public void InsertUser(Usuario usuario) {
//		try {
//			this.em.persist(usuario);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
//
//
