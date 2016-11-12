package br.com.dannyfelipe.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Danny Felipe
 * @data 8 de nov de 2016 - 22:54:03
 *
 * CLASSE QUE VAI SER A ENTIDADE PARA PERSISTIR A TABELA DE USUÁRIO NO BANCO DE DADOS
 */

@Table(name="tb_usuario")
@Entity
@NamedQuery(name = "UsuarioEntity.findUser",
		    query= "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha")
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_usuario")
	private String codigo;

	@Column(name="ds_login")
	private String usuario;

	@Column(name="ds_senha")
	private String senha;

	/*
	 * RETORNA CODIGO DO USUARIO
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/*
	 * DEFINE O CODIGO DO USUARIO
	 * @param codigo - CODIGO
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/*
	 * RETORNA O USUARIO
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/*
	 * DEFINE O USUÁRIO
	 * @param usuario - USUARIO
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/*
	 * RETORNA SENHA DO USUARIO
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}
	/* DEFINE A SENHA
	 * @param senha - SENHA
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
