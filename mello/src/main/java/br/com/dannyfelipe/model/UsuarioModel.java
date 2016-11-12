package br.com.dannyfelipe.model;

import java.io.Serializable;

/**
 *
 * @author Danny Felipe
 * @data 8 de nov de 2016 - 23:31:46
 *
 * CLASSE SERIALIZADA PARA SER USADA PELO MANAGED BENAS PARA RECEBER OS DADOS QUE SÃO INFORMADOS NA APLICAÇÃO
 */
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String usuario;
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
	 * DEFINE O CODIGO DO USUARIO
	 * @param codigo - CODIGO
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