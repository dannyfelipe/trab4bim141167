package br.com.dannyfelipe.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Danny Felipe
 * @data 12 de nov de 2016 - 20:15:33
 *
 * CLASSE QUE VAI SER A ENTIDADE PARA PERSISTIR A TABELA DE PESSOA NO BANCO DE DADOS
 */

@Entity
@Table(name = "tb_pessoa")

@NamedQueries({
	@NamedQuery(name = "PessoaEntity.findAll", query = "SELECT p FROM PessoaEntity p"),
	@NamedQuery(name="PessoaEntity.GroupByOrigemCadastro",query= "SELECT p.origemCadastro, count(p) as total FROM PessoaEntity p GROUP By p.origemCadastro")
})
public class PessoaEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_pessoa")
	private Integer codigo;

	@Column(name = "nm_pessoa")
	private String nome;

	@Column(name = "fl_sexo")
	private String sexo;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "ds_email")
	private String email;

	@Column(name = "ds_endereco")
	private String endereco;

	@Column(name = "fl_origemCadastro")
	private String origemCadastro;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

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
	 * RETORNA A ENTIDADE DO USUARIO
	 * @return dataCadastro - ESPECIFICA A ENTIDADE DO USUARIO DA PESSOA
	 */
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	/*
	 * DEFINE A ENTIDADE DO USUARIO
	 * @param usuarioEntity - ENTIDADE DO USUARIO
	 */
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

}
