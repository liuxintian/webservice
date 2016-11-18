package com.omt.cms.repo.op.base;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.entity.BaseEntity;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public interface CrudOperations<T extends BaseEntity> {
	
	public Class<T> getEntityType();
	
	public PagingAndSortingRepository<T, Long> getRepository();
	
	public T findById(Long entityId);
	
	public T add(T entity);

	public T update(T entity);

	public T delete(T entity);
	
	public List<T> findAll();

	public T remove(T entity); //hard delete
	
	public List<T> findByNativeQuery(String query);
	
	public List<T> findByQuery(String query);

}
