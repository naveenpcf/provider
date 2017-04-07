package com.cardinalhealth.chh.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardinalhealth.chh.model.HealthCareProviderHistory;

/**
 * This class implements the custom interface methods. It is responsible for
 * implementing customized queries to be written for DB. All the queries that
 * cannot be put in ObjectRepository can be done here. Repository methods that
 * are more complex and cannot be handled with a simple query or an annotation.
 * 
 * @author guru
 */
public class HealthCareProviderRepositoryImpl implements HealthCareProviderRepositoryCustom {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCareProviderRepositoryImpl.class);

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Custom implementation for find account number Good old customized queries
	 * using simple JPQL notations can be done here.
	 * 
	 * @param npiId
	 * @param createByNam
	 * 
	 * @return List of providers
	 */
	public List<HealthCareProviderHistory> fetchNPIIdCustom(Long npiId, String createByNam) {
		LOGGER.info("fetching custom method with custom params {} : {}  -- ", npiId, createByNam);
		return entityManager
				.createQuery("from HealthCareProviderHistory where npiId = :npiId AND createByNam = :createByNam",
						HealthCareProviderHistory.class)
				.setParameter("npiId", npiId).setParameter("createByNam", createByNam).getResultList();
	}
}