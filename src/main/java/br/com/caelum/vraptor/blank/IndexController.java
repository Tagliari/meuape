//package br.com.caelum.vraptor.blank;
//
//import br.com.caelum.dao.UsuarioDao;
//import br.com.caelum.model.Usuario;
//import br.com.caelum.vraptor.Controller;
//import br.com.caelum.vraptor.Path;
//import br.com.caelum.vraptor.Post;
//import br.com.caelum.vraptor.Result;
//
//@Controller
//public class IndexController {
//
//	private final Result result;
//	private final UsuarioDao userDao;
//
//	public IndexController(Result result, UsuarioDao userDao) {
//		this.result = result;
//		this.userDao = userDao;
//	}
//
//	@Path("/indexteste")
//	public void index() {
//	}
//
//	@Post
//	@Path("/cadastroteste")
//	public void cadastro(Usuario usuario) {
//
//		// System.out.println(usuario);
//
//		this.userDao.InsertUser(usuario);
//		this.result.forwardTo(IndexController.class).index();
//	}
//}