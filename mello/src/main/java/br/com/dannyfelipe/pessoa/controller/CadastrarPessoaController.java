package br.com.dannyfelipe.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dannyfelipe.model.PessoaModel;
import br.com.dannyfelipe.repository.PessoaRepository;
import br.com.dannyfelipe.usuario.controller.UsuarioController;
import br.com.dannyfelipe.uteis.Uteis;

/**
 *
 * @author Danny Felipe
 * @data 12 de nov de 2016 - 20:52:25
 *
 * CLASSE SERIALIZADA EM UM BEAN GERENCIADO PELO CDI PARA INJEÇÃO DE DEPENDÊNCIA DOS OBJETOS NA CLASSE
 */

@Named(value = "cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController {

	@Inject
	PessoaModel pessoaModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	PessoaRepository pessoaRepository;

	/*
	 * RETORNA OBJETO PESSOAMODEL
	 * @return pessoaModel - ESPECIFICA O OBJETO DA PESSOA
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/*
	 * DEFINE OBJETO PESSOAMODEL
	 * @param pessoaModel - OBJETO DA PESSOA
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * MÉTODO PARA PERSISTIR UMA NOVA PESSOA ATRAVÉS DO CAMPO INPUT NA TELA DA APLICAÇÃO
	 */
	public void SalvarNovaPessoa() {

		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		// INFORMANDO QUE O CADASTRO FOI VIA INPUT
		pessoaModel.setOrigemCadastro("I");

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);

		this.pessoaModel = null;

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

	}

}