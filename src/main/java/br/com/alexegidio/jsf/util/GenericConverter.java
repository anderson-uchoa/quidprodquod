package br.com.alexegidio.jsf.util;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class GenericConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent component,
			String value) throws ConverterException {

		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent component,
			Object value) throws ConverterException {
		if ((value != null) && !("".equals(value))) {

			Object entity = (Object) value;

			// adiciona item como atributo do componente
			this.addAttribute(component, entity);

			return entity.toString();
		}
		return (String) value;

	}

	protected void addAttribute(UIComponent component, Object o) {
		String key = String.valueOf(o);
		this.getAttributesFrom(component).put(key, o);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}
}
