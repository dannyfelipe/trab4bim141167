package br.com.dannyfelipe.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.dannyfelipe.model.PessoaModel;
import br.com.dannyfelipe.repository.entity.PessoaEntity;
import br.com.dannyfelipe.repository.entity.UsuarioEntity;
import br.com.dannyfelipe.uteis.Uteis;

/**
 *
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
}