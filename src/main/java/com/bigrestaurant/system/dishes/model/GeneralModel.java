package com.bigrestaurant.system.dishes.model;

import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author Anas Abu Hussein
 * @since 16/8/2018
 **/
@JacksonXmlRootElement
public interface GeneralModel {
	
	@Transient
	public List<HATEOAS> getHATEOAS();
}
