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
 * @data 15 de nov de 2016 - 14:51:17
 *
 * CLASSE SERIALIZADA PARA CONSULTA DE PESSOAS UTILIZANDO COMPONENTE CAROUSEL DO PRIMEFACES
 */

@Named(value = "consultarPessoaCarouselController")
@ViewScoped
public class ConsultarPessoaCarouselController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private PessoaRepository pessoaRepository;

	@Produces
	private List<PessoaModel> pessoas;

	/**
	 * RETORNA UMA LISTA
	 * @return pessoas - OBTÉM UMA LISTA DE PESSOAS
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/**
	 * CHAMADO APÓS A CONSTRUÇÃO DA CLASSE,
	 * MÉTODO RESPONSÁVEL POR ATRIBUIR VALOR A VARIÁVEL PESSOAS
	 */
	@PostConstruct
	private void init() {

		this.pessoas = pessoaRepository.GetPessoas();
	}

}