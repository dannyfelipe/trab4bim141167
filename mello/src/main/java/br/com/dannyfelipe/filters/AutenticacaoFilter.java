package br.com.dannyfelipe.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.dannyfelipe.model.UsuarioModel;

/**
 * @author Danny Felipe
 * @data 12 de nov de 2016 - 16:38:56

 * FILTER PARA VALIDAR SE O USUÁRIO ESTÁ LOGADO PARA QUE POSSA ACESSAR AS DEMAIS PÁGINAS DO SISTEMA
 */

@WebFilter("/sistema/*")
public class AutenticacaoFilter implements Filter {

	/* CONSTRUTOR PADRÃO*/
	public AutenticacaoFilter() {

	}

	/*
	 * ENCERRA A APLICAÇÃO
	 * @see destroy()
	 */
	public void destroy() {

	}

	/*
	 * MÉTODO PARA FILTRAR AS REQUISIÇÕES DE TODOS OS ITENS QUE TIVEREM EM /sistema/*
	 * VERIFICA SE EXISTE O USUÁRIO LOGADO NA APLICAÇÃO, CASO NÃO, REDIRECIONA PARA A PÁGINA DE LOGIN
	 *
	 * @request - REQUISIÇÃO
	 * @response - RESPOSTA
	 * @chain - INICIA FACE SERVLETS
	 * @throws - EXCEÇÃO NO CASO DE ERRO NA TRANSAÇÃO, SERVLET INVÁLIDO
	 *
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession httpSession = ((HttpServletRequest) request).getSession();

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1) {

			UsuarioModel usuarioModel = (UsuarioModel) httpSession.getAttribute("usuarioAutenticado");

			if (usuarioModel == null) {

				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.xhtml");

			} else {

				chain.doFilter(request, response);
			}
		} else {

			chain.doFilter(request, response);
		}
	}


	/*
	 * MÉTODO VAZIO DA INTERFACE
	 * @param fConfig
	 * @throws - EXCEÇÃO NO CASO DE UM SERVLET INVÁLIDO
	 * @see init
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}