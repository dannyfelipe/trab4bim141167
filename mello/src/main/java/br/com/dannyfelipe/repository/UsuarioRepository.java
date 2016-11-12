package br.com.dannyfelipe.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.dannyfelipe.model.UsuarioModel;
import br.com.dannyfelipe.repository.entity.UsuarioEntity;
import br.com.dannyfelipe.uteis.Uteis;

/**
 * @author Danny Felipe
 * @data 12 de nov de 2016 - 15:54:05
 *
 * CLASSE SERIALIZADA QUE RETORNA OBJETO DO USUÁRIO
 */
public class UsuarioRepository implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * APÓS CONSULTA BANCO DE DADOS, CASO EXISTIR, É RETORNADO OBJETO USUARIOENTITY
	 * @param usuarioModel - OBJETO USUARIO
	 * @return UsuarioeNTITY OR null
	 */
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){

		try {

			/* QUERY A SER EXECUTADA */
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");

			/* PARÂMETROS DA QUERY */
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());

			/* RETORNA OBJETO DO USUÁRIO CASO SEJA LOCALIZADO */
			return (UsuarioEntity)query.getSingleResult();

		} catch (Exception e) {

			return null;
		}
	}
}