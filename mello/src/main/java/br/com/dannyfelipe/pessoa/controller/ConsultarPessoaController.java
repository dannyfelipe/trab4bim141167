package br.com.dannyfelipe.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dannyfelipe.model.PessoaModel;
import br.com.dannyfelipe.repository.PessoaRepository;

/**
 * @author Danny Felipe
 * @data 14 de nov de 2016 - 15:06:24
 *
 * CLASSE PARA CONSULTA DE PESSOAS PERSISTIDAS NO BANCO DE DADOS
 * CLASSE PARA EDIÇÃO DE PESSOAS PERSISTIDAS NO BANCO DE DADOS
 */

@Named(value = "consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private PessoaModel pessoaModel;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject
	transient private PessoaRepository pessoaRepository;

	/**
	 * OBTÉM UMA LISTA DE PESSOAS
	 * @return - ESPECIFICA UMA LISTA DE PESSOAS
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/**
	 * DEFINE UMA LISTA
	 * @param pessoas - LISTA DE PESSOAS
	 */
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}

	/**
	 * RETORNA O MODELO DE PESSOA
	 * @return pessoaModel - ESPECIFICA O MODELO DE PESSOA
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * DEFINE O MODELO DE PESSOA
	 * @param usuarioEntity - MODELO DE PESSOA
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * MÉTODO CHAMADO APÓS A CONSTRUÇÃO DA CLASSE
	 * CARREGA UMA LISTA DE PESSOAS PERSISTIDA NO BANCO DE DADOS
	 */
	@PostConstruct
	public void init() {

		// RETORNAR AS PESSOAS CADASTRADAS
		this.pessoas = pessoaRepository.GetPessoas();
	}

	/**
	 * MÉTODO RESPONSÁVEL POR CARREGAR AS INFORMAÇÕES DE UM OBJETO PESSOA PARA EDIÇÃO
	 * @param pessoaModel - OBJETO MODELO PESSOA
	 */
	public void Editar(PessoaModel pessoaModel) {

		/* OBTÉM APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO NO FORMULÁRIO (M OU F)*/
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));

		this.pessoaModel = pessoaModel;

	}

	/**
	 * MÉTODO RESPONSÁVEL POR ATUALIZAR O REGISTRO ALTERADO
	 */
	public void AlterarRegistro() {

		this.pessoaRepository.AlterarRegistro(this.pessoaModel);

		/* RECARREGA OS REGISTROS ALTERADOS */
		this.init();
	}

}