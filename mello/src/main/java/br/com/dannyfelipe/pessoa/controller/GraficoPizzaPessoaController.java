package br.com.dannyfelipe.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.dannyfelipe.repository.PessoaRepository;

/**
 * @author Danny Felipe
 * @data 15 de nov de 2016 - 15:26:21
 *
 * CLASSE RESPONSÁVEL POR GERIR O GRÁFICO DE ORIGEM DE CADASTRO
 */

@Named(value = "graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;

	private PieChartModel pieChartModel;

	/**
	 * MÉTODO DEFINE O TIPO DE GRÁFICO A UTILIZAR
	 * @return pieChartModel - TIPO DO GRÁFICO
	 */
	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	/**
	 * APÓS A CLASSE SER CONSTRUÍDA, EXECUTA O MÉTODO, INJETANDO O BEANS NO CONTEXTO DO CDI
	 */
	@PostConstruct
	public void init() {

		this.pieChartModel = new PieChartModel();

		this.MontaGrafico();
	}

	/**
	 * MÉTODO RESPONSÁVEL POR CRIAR O GRÁFICO NA VIEW DA APLICAÇÃO
	 */
	private void MontaGrafico() {

		/* CONSULTA OS DADOS NO BANCO DE DADOS PARA CRIAR O GRÁFICO */
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa();

		/* INFORMA OS VALORES PARA CRIAR O GRÁFICO */
		hashtableRegistros.forEach((chave, valor) -> {

			pieChartModel.set(chave, valor);

		});

		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");

	}
}