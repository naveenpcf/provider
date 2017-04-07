package com.cardinalhealth.chh.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cardinalhealth.chh.model.HealthCareProviderHistory;

/**
 * This interface lists down all the methods which is late implemented by users
 * in a Impl class. Users can implement customized queries to be written for DB.
 * All the queries which are deemed complex and the ones that cannot be
 * accommodated in Repository class can be done here. Repository methods that
 * are more complex and cannot be handled with a simple query or an annotation.
 * 
 * @author guru
 */
@Repository
public interface HealthCareProviderRepositoryCustom {

	/**
	 * Custom implementation for find account number Good old customized queries
	 * using simple JPQL notations can be done here.
	 * 
	 * @param npiId
	 * @param createByNam
	 * 
	 * @return List of providers
	 */
	List<HealthCareProviderHistory> fetchNPIIdCustom(Long npiId, String createdByNam);
}
