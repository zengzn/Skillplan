package com.beone.skillplan.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractEntity.class)
public abstract class AbstractEntity_ {

	public static volatile SingularAttribute<AbstractEntity, Serializable> id;
	public static volatile SingularAttribute<AbstractEntity, Integer> version;
	public static volatile SingularAttribute<AbstractEntity, Timestamp> updated;

}

