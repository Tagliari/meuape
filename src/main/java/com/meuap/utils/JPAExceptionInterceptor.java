//package com.meuap.utils;
//
//import java.util.Arrays;
//
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//
//import br.com.caelum.vraptor.AroundCall;
//import br.com.caelum.vraptor.Intercepts;
//import br.com.caelum.vraptor.Result;
//import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
//import br.com.caelum.vraptor.jpa.JPATransactionInterceptor;
//import br.com.caelum.vraptor.validator.SimpleMessage;
//import br.com.caelum.vraptor.view.Results;
//
//@Intercepts(before = JPATransactionInterceptor.class)
//@RequestScoped
//public class JPAExceptionInterceptor {
//	private final Result result;
//
//	@Deprecated
//	protected JPAExceptionInterceptor() {
//		this(null);
//	}
//
//	@Inject
//	public JPAExceptionInterceptor(Result result) {
//		this.result = result;
//	}
//
//	@AroundCall
//	public void intercept(SimpleInterceptorStack stack) {
//		try {
//			stack.next();
//		} catch (Exception e) {
//			String message = Parser.exceptionRootCauseMessage(e);
//
//			result.include("errors", Arrays.asList(new SimpleMessage(message, "ERRO"))).use(Results.referer()).redirect();
//		}
//	}
//}
