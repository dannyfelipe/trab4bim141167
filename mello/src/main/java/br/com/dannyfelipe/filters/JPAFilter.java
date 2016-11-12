package br.com.dannyfelipe.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


/***
 * @author Danny Felipe
 * @data 08/11/2016 às 20:29
 * A CADA REQUISIÇÃO NO SERVIDOR PARA O FACES SERVLET, SERA CHAMADO O JPAFILTER.
 * */

@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {


	private EntityManagerFactory entityManagerFactory;

	private String persistence_unit_name = "unit_app";

	/* CONSTRUTOR */
    public JPAFilter() {
    }

    /* ENCERRA A CONEXÃO */
	public void destroy() {
		this.entityManagerFactory.close();
	}


	/*
	 * CONFIGURAÇÕES DO PROTOCOLO HTTP
	 * @request - REQUISIÇÃO
	 * @response - RESPOSTA
	 * @chain - INICIA FACE SERVLETS
	 * @throws - EXCEÇÃO NO CASO DE ERRO NA TRANSAÇÃO
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		/* CRIA UM ENTITYMANAGER */
		EntityManager entityManager =  this.entityManagerFactory.createEntityManager();

		/* ADICIONA O ENTITYMANAGER NA REQUISIÇÃO */
		request.setAttribute("entityManager", entityManager);

		/* INICIA UMA TRANSAÇÃO NA APLICAÇÃO */
		entityManager.getTransaction().begin();

		/* INICIA O FACES SERVLET */
		chain.doFilter(request, response);

		try {

			/*SE NÃO HOUVER ERRO NA TRANSAÇÃO, EXECUTA O COMMIT */
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			/* SE HOUVER ERRO NA TRANSAÇÃO, UM ROLLBACK É REALIZADO */
			entityManager.getTransaction().rollback();
		}
		finally{

			/* APÓS REALIZAR O COMMIT OU ROLLBACK, É FINALIZADO O ENTITYMANAGER */
			entityManager.close();
		}
	}

	/*
	 * CRIA ENTITYMANAGERFACTORY COM BASE NOS PARÂMETROS SETADOS NO ARQUIVO DE PERSISTÊNCIA COM O BANCO
	 * @param fConfig - CONFIGURAÇÃO DA PERSISTÊNCIA
	 * @throws - EXCEÇÃO NO CASO DE UM SERVLET INVÁLIDO
	 * */
	public void init(FilterConfig fConfig) throws ServletException {

		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}

}
