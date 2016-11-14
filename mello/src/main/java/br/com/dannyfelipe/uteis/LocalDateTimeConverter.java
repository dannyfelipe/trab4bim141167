package br.com.dannyfelipe.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 * @author Danny Felipe
 * @data 14 de nov de 2016 - 16:55:54
 *
 * CLASSE PARA CONVERTER OS VALORES LOCALDATETIME PARA EXIBIR NA CONSULTA DA APLICAÇÃO
 * @extends - HERENÇA DA CLASSE DATETIMECONVERTER
 */

@FacesConverter(value = LocalDateTimeConverter.ID)
public class LocalDateTimeConverter extends DateTimeConverter {

	public static final String ID = "br.com.ciceroednilson.uteis.LocalDateTimeConverter";

	/*
	 * MÉTODO RESPONSÁVEL PARA CONVERTER A DATA E EXIBIR NO DATATABLE
	 * @param facesContext - PARÂMETRO DO CDI
	 * @param uiComponent - COMPONENTE DO OBJETO
	 * @param value - DATA A SER CONVERTIDA
	 * @throws - EXCEÇÃO CASO NÃO SEJA POSSÍVEL CONVERTER A DATA LOCALDATETIME
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

		Date date = null;
		LocalDateTime localDateTime = null;

		Object object = super.getAsObject(facesContext, uiComponent, value);

		if (object instanceof Date) {

			date = (Date) object;

			Instant instant = Instant.ofEpochMilli(date.getTime());
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			return localDateTime;

		} else {

			throw new IllegalArgumentException(
					String.format("value=%s Não foi possível converter LocalDateTime, resultado super.getAsObject=%s",
							value, object));
		}

	}

	/*
	 * MÉTODO RESPONSÁVEL PARA CONVERTER A DATA LOCALDATETIME EM VALOR STRING
	 * @param facesContext - PARÂMETRO DO CDI
	 * @param uiComponent - COMPONENTE DO OBJETO
	 * @param value - DATA A SER CONVERTIDA
	 * @return - ESPECIFICA A DATA CONVERTIDA
	 * @throws - EXCEÇÃO CASO O VALOR NÃO SEJA UM LOCALDATETIME
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

		if (value == null)
			return super.getAsString(facesContext, uiComponent, value);

		if (value instanceof LocalDateTime) {

			LocalDateTime localDateTime = (LocalDateTime) value;

			Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

			Date date = Date.from(instant);

			return super.getAsString(facesContext, uiComponent, date);
		} else {

			throw new IllegalArgumentException(String.format("value=%s não é um LocalDateTime", value));
		}

	}
}