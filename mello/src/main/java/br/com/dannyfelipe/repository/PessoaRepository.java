package br.com.dannyfelipe.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.dannyfelipe.model.PessoaModel;
import br.com.dannyfelipe.model.UsuarioModel;
import br.com.dannyfelipe.repository.entity.PessoaEntity;
import br.com.dannyfelipe.repository.entity.UsuarioEntity;
import br.com.dannyfelipe.uteis.Uteis;

/**
 * @author Danny Felipe
 * @data 12 de nov de 2016 - 20:48:11
 *
 * CLASSE SERIALIZADA QUE RETORNA OBJETO DA PESSOA
 */
public class PessoaRepository {

	@Inject
	PessoaEntity pessoaEntity;

	EntityManager entityManager;

	/**
	 * MÉTODO RESPONSÁVEL POR PERSISTIR ENTIDADE DE UMA NOVA PESSOA
	 * @param pessoaModel - OBJETO PESSOA
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				pessoaModel.getUsuarioModel().getCodigo());

		pessoaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(pessoaEntity);

	}

	/**
	 * MÉTODO RESPOSÁVEL PARA REALIZAR CONSULTA DE UMA PESSOA NO BANCO DE DADOS
	 * @return pessoasModel - RETORNA UMA LISTA DE PESSOAS
	 */
	public List<PessoaModel> GetPessoas() {

		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>) query.getResultList();

		PessoaModel pessoaModel = null;

		for (PessoaEntity pessoaEntity : pessoasEntity) {

			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());

			/*
			 * VERIFICA A ORIGEM DA PERSISTENCIA DE UMA NOVA PESSOA
			 * XML - ATRAVÉS DE UPLOAD DE ARQUIVO XML
			 * INPUT - ATRAVÉS DE CADASTRO VIA FORMULÁRIO
			 */
			if (pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");

			else
				pessoaModel.setOrigemCadastro("INPUT");

			/* VERIFICA O SEXO DA PESSOA */
			if (pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");

			else
				pessoaModel.setSexo("Feminino");

			UsuarioEntity usuarioEntity = pessoaEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			pessoaModel.setUsuarioModel(usuarioModel);

			pessoasModel.add(pessoaModel);
		}

		return pessoasModel;

	}

	/**
	 * MÉTODO RESPONSÁVEL REALIZAR A CONSULTA UMA PESSOA PERSISTIDA NO BANCO PELO SEU CÓDIGO
	 * @param codigo - CODIGO DA PESSOA
	 * @return codigo - RETORNA UM OBJETO PESSOA
	 */
	private PessoaEntity GetPessoa(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(PessoaEntity.class, codigo);
	}

	/**
	 * MÉTODO RESPONSÁVEL PARA ALTERAR UMA PESSOA PERSISTIDA NO BANCO DE DADOS
	 * @param pessoaModel - OBJETO MODELO DE PESSOA
	 */
	public void AlterarRegistro(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(pessoaModel.getCodigo());

		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		entityManager.merge(pessoaEntity);
	}

	/**
	 * MÉTODO RESPONSÁVEL POR EXCLUIR UMA PESSOA PERSISTIDA DO BANCO DE DADOS
	 * @param codigo - CÓDIGO DA PESSOA
	 */
	public void ExcluirRegistro(int codigo){

		entityManager =  Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(codigo);

		entityManager.remove(pessoaEntity);
	}

	/**
	 * MÉTODO RESPONSÁVEL POR RETORNAR O TOTAL DE PESSOAS POR ORIGEM DE CADASTRO
	 * @return hashtableRegistros - TOTAL DE PESSOAS
	 */
	public Hashtable<String, Integer> GetOrigemPessoa() {

		Hashtable<String, Integer> hashtableRegistros = new Hashtable<String, Integer>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("PessoaEntity.GroupByOrigemCadastro");

		@SuppressWarnings("unchecked")
		Collection<Object[]> collectionRegistros = (Collection<Object[]>) query.getResultList();

		for (Object[] objects : collectionRegistros) {

			String tipoPessoa = (String) objects[0];
			int totalDeRegistros = ((Number) objects[1]).intValue();

			if (tipoPessoa.equals("X"))
				tipoPessoa = "XML";
			else
				tipoPessoa = "INPUT";

			hashtableRegistros.put(tipoPessoa, totalDeRegistros);

		}

		return hashtableRegistros;

	}

}