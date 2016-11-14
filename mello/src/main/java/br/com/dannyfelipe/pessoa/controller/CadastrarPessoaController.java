package br.com.dannyfelipe.pessoa.controller;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.primefaces.model.UploadedFile;
import org.xml.sax.SAXException;

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

	private UploadedFile file;

	/*
	 * RETORNA O ARQUIVO PARA UPLOAD
	 * @return file - ESPECIFICA O CAMINHO QUE VAI SER CARREGADO PARA O SERVIDOR
	 */
	public UploadedFile getFile() {
		return file;
	}

	/*
	 * DEFINE O ARQUIVO PARA UPLOAD
	 * @param file - ARQUIVO PARA UPLOAD DE CADASTRO DE PESSOA
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

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

		/* CADASTRO DA PESSOA REALIZADO VIA INPUT NO FORMULARIO */
		pessoaModel.setOrigemCadastro("I");

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);

		this.pessoaModel = null;

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

	}

	/*
	 * MÉTODO PARA REALIZAR UPLOAD DE ARQUIVO XML PARA PERSISTIR UMA NOVA PESSOA NO BANCO DE DADOS
	 * @return - SE O CAMINHO FOR VAZIO, EXIBE MENSAGEM DE ATENÇÃO PARA O USUÁRIO
	 * @return - SE REALIZADO UPLOAD, EXIBE MENSAGEM DE SUCESSO PARA O USUÁRIO
	 * @exception IOException - ARQUIVO NÃO PODE SER LIDO
	 * @exception SAXException - PROBLEMA FAZER PARSE DO ARQUIVO
	 * @exception ParserConfigurationException - PARSER NÃO FOI CONFIGURADO CORRETAMENTE
	 */
	public void UploadRegistros() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			if (this.file.getFileName().equals("")) {
				Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
				return;
			}

			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = builder.parse(this.file.getInputstream());

			Element element = doc.getDocumentElement();

			NodeList nodes = element.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {

				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element elementPessoa = (Element) node;

					/* OBTÉM OS VALORES DO ARQUIVO XML */
					String nome = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0)
							.getNodeValue();
					String sexo = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0)
							.getNodeValue();
					String email = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0)
							.getNodeValue();
					String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0)
							.getNodeValue();

					PessoaModel newPessoaModel = new PessoaModel();

					newPessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
					newPessoaModel.setEmail(email);
					newPessoaModel.setEndereco(endereco);
					newPessoaModel.setNome(nome);
					newPessoaModel.setOrigemCadastro("X");
					newPessoaModel.setSexo(sexo);

					/* PERSISTE UM NOVO REGISTRO NO BANCO DE DADOS ATRAVÉS DE ARQUIVO XML */
					pessoaRepository.SalvarNovoRegistro(newPessoaModel);
				}
			}

			Uteis.MensagemInfo("Registros cadastrados com sucesso!");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		} catch (SAXException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}