package br.com.dannyfelipe.model;

import java.time.LocalDateTime;
/**
 *
 * @author Danny Felipe
 * @data 12 de nov de 2016 - 20:41:44
 *
 * CLASSE SERIALIZADA PARA SER USADA PELO MANAGED BENAS PARA RECEBER OS DADOS QUE SÃO INFORMADOS NA APLICAÇÃO
 */
public class PessoaModel {

	private Integer codigo;
	private String nome;
	private String sexo;
	private LocalDateTime dataCadastro;
	private String email;
	private String endereco;
	private String origemCadastro;
	private UsuarioModel usuarioModel;


	/*
	 * RETORNA O CODIGO
	 * @return codigo - ESPECIFICA O CODIGO DA PESSOA
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/*
	 * DEFINE O CODIGO
	 * @param codigo - CODIGO DA PESSOA
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/*
	 * RETORNA O NOME
	 * @return nome - ESPECIFICA O NOME DA PESSOA
	 */
	public String getNome() {
		return nome;
	}

	/*
	 * DEFINE O NOME
	 * @param nome - NOME DA PESSOA
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/*
	 * RETORNA O SEXO
	 * @return sexo - ESPECIFICA O SEXO DA PESSOA
	 */
	public String getSexo() {
		return sexo;
	}

	/*
	 * DEFINE O SEXO
	 * @param sexo - SEXO DA PESSOA
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/*
	 * RETORNA A DATA DE CADASTRO
	 * @return dataCadastro - ESPECIFICA A DATA EM QUE FOI REALIZADO O CADASTRO
	 */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	/*
	 * DEFINE DATA DE CADASTRO
	 * @param dataCadastro - DATA DE CADASTRO DA PESSOA
	 */
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/*
	 * RETORNA O EMAIL
	 * @return dataCadastro - ESPECIFICA O EMAIL DA PESSOA
	 */
	public String getEmail() {
		return email;
	}

	/*
	 * DEFINE O EMAIL
	 * @param email - EMAIL DA PESSOA
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * RETORNA O ENDERECO
	 * @return dataCadastro - ESPECIFICA O ENDERECO DA PESSOA
	 */
	public String getEndereco() {
		return endereco;
	}

	/*
	 * DEFINE O ENDERECO
	 * @param endereco - ENDERECO DA PESSOA
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/*
	 * RETORNA A ORIGEM DE CADASTRO
	 * @return origemCadastro - ESPECIFICA A ORIGEM DO CADASTRO DA PESSOA
	 */
	public String getOrigemCadastro() {
		return origemCadastro;
	}

	/*
	 * DEFINE A ORIGEM DE CADASTRO
	 * @param origemCadastro - ORIGEM DE CADASTRO
	 */
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}

	/*
	 * RETORNA O MODELO DO USUARIO
	 * @return dataCadastro - ESPECIFICA O MODELO DO USUARIO DA PESSOA
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/*
	 * DEFINE O MODELO DO USUARIO
	 * @param usuarioEntity - MODELO DO USUARIO
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}