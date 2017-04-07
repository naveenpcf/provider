package com.cardinalhealth.chh.service;

import java.util.List;

import com.cardinalhealth.chh.model.HealthCareProviderHistory;

/**
 * Service interface lists down the popular methods for executing CRUD operation in a DB. 
 * 
 * @author guru
 */
public interface HealthCareProviderService {

	/**
	 * Fetch providerId by npiId
	 * 
	 * @param npiId
	 * @return
	 * @throws Exception
	 */
	HealthCareProviderHistory getProvider(Long npiId) throws Exception;

	/**
	 * Save or update an providerId
	 * 
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	HealthCareProviderHistory saveOrUpdate(HealthCareProviderHistory providerId) throws Exception;

	/**
	 * Deletes an providerId given the providerId Id
	 * 
	 * @param providerIdId
	 * @return
	 * @throws Exception
	 */
	void deleteProvider(Long providerId) throws Exception;

	/**
	 * Fetch providerId by first name and last names
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	HealthCareProviderHistory getProviderByFirstNameAndLastName(String firstName, String lastName) throws Exception;

	/**
	 * Fetched by npiId and created by user
	 * 
	 * @param npiId
	 * @param createdBy
	 * @return
	 */
	List<HealthCareProviderHistory> getProviderNpiAndCreatedBy(Long npiId, String createdBy) throws Exception;

	/**
	 * Fetches by npiId and created by user using custom method
	 * 
	 * @param npiId
	 * @param createdBy
	 * @return
	 * @throws Exception
	 */
	List<HealthCareProviderHistory> getProviderByNpiAndCreatedByCust(Long npiId, String createdBy) throws Exception;

	/**
	 * Fetches all providerIds
	 * 
	 * @return
	 * @throws Exception
	 */
	List<HealthCareProviderHistory> getAllProviders() throws Exception;

}
