package br.com.dannyfelipe.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Danny Felipe
 * @data 8 de nov de 2016 - 21:04:20
 *
 * CLASSE DE MÉTODOS ÚTEIS PARA A APLICAÇÃO
 */
public class Uteis {

	/*
	 * RETORNA CONEXÃO REQUISITADA, CONFIGURADA NA JPA
	 * @return - EntityManager
	 */
	public static EntityManager JpaEntityManager() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();

		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		return (EntityManager) request.getAttribute("entityManager");
	}

	/**
	 * EXIBIR MENSAGEM DE ALERTA
	 * @param mensagem - MENSAGEM
	 */
	public static void Mensagem(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	/**
	 * EXIBIR MENSAGEM DE INFORMAÇÃO QUE NECESSITE ATENÇÃO
	 * @param mensagem - MENSAGEM
	 */
	public static void MensagemAtencao(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}

	/**
	 * EXIBIR MENSAGEM COM ALGUMA INFORMAÇÃO
	 * @param mensagem - MENSAGEM
	 */
	public static void MensagemInfo(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

}