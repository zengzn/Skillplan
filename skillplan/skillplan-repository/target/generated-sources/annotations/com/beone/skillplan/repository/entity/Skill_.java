package com.beone.skillplan.repository.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Skill.class)
public abstract class Skill_ extends com.beone.skillplan.repository.entity.AbstractEntity_ {

	public static volatile ListAttribute<Skill, User> skilledUsers;
	public static volatile SingularAttribute<Skill, String> name;

}

