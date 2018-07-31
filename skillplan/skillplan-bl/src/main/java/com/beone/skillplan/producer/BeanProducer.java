package com.beone.skillplan.producer;

import java.util.Arrays;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

@ManagedBean
public class BeanProducer {
	@Produces
	@ApplicationScoped
	public Mapper createMapper() {
		List<String> myMappingFiles = Arrays.asList("/dozerMapping.xml");
		return new DozerBeanMapper(myMappingFiles);
	}
}
