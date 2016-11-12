package br.com.dannyfelipe.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.dannyfelipe.model.UsuarioModel;
import br.com.dannyfelipe.repository.UsuarioRepository;
import br.com.dannyfelipe.repository.entity.UsuarioEntity;
import br.com.dannyfelipe.uteis.Uteis;

/**
 * @author Danny Felipe
 * @data 12 de nov de 2016 - 16:19:46
 *
 * CLASSE SERIALIZADA EM UM BEAN GERENCIADO PELO CDI PARA INJEÇÃO DE DEPENDÊNCIA DOS OBJETOS NA CLASSE
 */

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioModel usuarioModel;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private UsuarioEntity usuarioEntity;

	/*
	 * RETORNA OBJETO USUARIOMODEL
	 * @return usuarioModel - OBJETO DO USUÁRIO
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/*
	 * DEFINE OBJETO USUARIOMODEL
	 * @param usuarioModel - OBJETO DO USUÁRIO
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/*
	 * RETORNA O USUÁRIO LOGADO NA APLICAÇÃO
	 * @return UsuarioModel
	 */
	public UsuarioModel GetUsuarioSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	/*
	 * FINALIZA A SESSÃO DO USUÁRIO
	 * E REDIRECIONA PARA A PÁGINA DE LOGIN
	 * @return index.xhtml
	 */
	public String Logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

	/*
	 * MÉTODO PARA VALIDAÇÃO DE USUÁRIO NA APLICAÇÃO, MEDIANTE USUÁRIO E SENHA
	 * @return sistema/home - PÁGINA INICIAL DA APLICAÇÃO, CASO VERDADEIRO
	 * @return null - EXIBE MENSAGEM DE ALERTA, CASO FALSO
	 */
	public String EfetuarLogin() {

		if (StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())) {

			Uteis.Mensagem("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {

			Uteis.Mensagem("Favor informara senha!");
			return null;
		} else {

			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);

			if (usuarioEntity != null) {

				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());

				FacesContext facesContext = FacesContext.getCurrentInstance();

				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);

				return "sistema/home?faces-redirect=true";
			} else {

				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}

	}

}
