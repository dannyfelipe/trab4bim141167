package br.com.dannyfelipe.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Danny Felipe
 * @data 12 de nov de 2016 - 21:08:39
 *
 * CLASSE PARA:
 * CONVERSÃO DE LOCALDATETIME -> TIMESTAMP - PARA PERSISTÊNCIA NO BANCO DE DADOS
 * CONVERSÃO DE TIMESTAMP -> LOCALDATETIME - PARA RETORNO DO BANCO E EXIBIÇÃO NA APLICAÇÃO
 */

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	/**
	 * SOBREESCREVE O MÉTODO
	 * CONVERTE O CAMPO DATA PARA TIMESTAMP PARA O JPA PERSISTIR NO BANCO DE DADOS
	 * @param localDateTime - CAMPO DE DATA E HORA
	 */
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {

		if (localDateTime != null)
			return Timestamp.valueOf(localDateTime);

		return null;

	}

	/**
	 * SOBREESCREVE O MÉTODO
	 * CONVERTE UM TIMESTAMP DO BANCO PARA LOCALDATETIME PARA EXIBIÇÃO NA APLICAÇÃO
	 * @param timestamp - CAMPO DE DATA E HORA
	 */
	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {

		if (timestamp != null)
			return timestamp.toLocalDateTime();

		return null;
	}
}