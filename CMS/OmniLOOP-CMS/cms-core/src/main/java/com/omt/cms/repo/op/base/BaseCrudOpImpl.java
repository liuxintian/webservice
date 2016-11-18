package com.omt.cms.repo.op.base;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.entity.BaseEntity;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public abstract class BaseCrudOpImpl<T extends BaseEntity>  implements CrudOperations<T>{
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseCrudOpImpl.class);
	@PersistenceContext protected EntityManager entityManger;
	protected Class<T> entityType;
	
	@Override
	public Class<T> getEntityType(){
		return entityType;
	}

	public void setEntityType(Class<T> entityType) {
		this.entityType = entityType;
	}

	@Override
	public T findById(Long entityId) {
		return getRepository().findOne(entityId);
	}

	@Override	
	public T add(T entity) {
		return getRepository().save(entity);
	}

	@Override
	public T update(T entity) {
		return getRepository().save(entity);
	}

	@Override
	public T delete(T entity) {
		return getRepository().save(entity);
	}
	
	@Override
	public List<T> findAll() {
		return (List<T>) getRepository().findAll();
	}

	protected PageRequest createPageRequest(){
		return new PageRequest(0, SystemCodes.PAGE_SIZE);
	}

	protected PageRequest createPageRequest(int offset, int size, String sortField, int direction){
		Direction dir = Direction.ASC;
		if(direction == 2){
			dir = Direction.DESC;
		}
		Sort sort = new Sort(new Order(dir, sortField));
		return new PageRequest(offset, size, sort);
	}

	@Override
	public T remove(T entity) {
		getRepository().delete(entity);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNativeQuery(String query) {
		LOGGER.info("Query is:{}", query);
		return entityManger.createNativeQuery(query, getEntityType()).getResultList();
	}
	
	@Override
	public List<T> findByQuery(String query) {
		LOGGER.info("Query is:{}", query);
		return entityManger.createQuery(query, getEntityType()).getResultList();
	}
}
