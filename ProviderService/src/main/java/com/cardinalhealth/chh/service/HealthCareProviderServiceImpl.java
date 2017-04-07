package com.cardinalhealth.chh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cardinalhealth.chh.model.HealthCareProvider;
import com.cardinalhealth.chh.model.HealthCareProviderHistory;
import com.cardinalhealth.chh.repository.HealthCareProviderRepository;

/**
 * Service for executing CRUD operation in a DB. this class behaves as a facade
 * and facilitates calls from controller to Repository. do note
 * that @Transactional notational which indicates that all transaction begins
 * from this layer marking a transaction boundary for interacting with DB.
 * 
 * @author guru
 */
@Service
@Transactional(readOnly = true)
public class HealthCareProviderServiceImpl implements HealthCareProviderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCareProviderServiceImpl.class);

	@Autowired
	private HealthCareProviderRepository repository;

	/* 
	 * @see com.cardinalhealth.chh.service.HealthCareProviderService#saveOrUpdate(com.cardinalhealth.chh.model.HealthCareProviderHistory)
	 */
	@Override
	@Transactional
	public HealthCareProviderHistory saveOrUpdate(HealthCareProviderHistory provider) throws Exception {
		LOGGER.info("Logging JSON input request {}", provider); 
		HealthCareProvider hcp = new HealthCareProvider();
		if (provider.getHcpMaster().getHcpId() != null) {
			hcp.setHcpId(provider.getHcpMaster().getHcpId());
		}
		provider.setHcpMaster(hcp);
		HealthCareProviderHistory providerFetch = getProvider(provider.getNpiId());
		if (providerFetch == null) {
			LOGGER.info("Saving provider into Db with provider ID {}", provider.getNpiId());
			return repository.saveAndFlush(provider);
		}
		LOGGER.info("Updating provider into Db with provider ID {}", provider.getNpiId());
		BeanUtils.copyProperties(provider, providerFetch, "hcpHistId");
		return repository.saveAndFlush(providerFetch);
	}

	/* 
	 * @see com.cardinalhealth.chh.service.HealthCareProviderService#getProvider(java.lang.Long)
	 */
	@Override
	public HealthCareProviderHistory getProvider(Long npiId) throws Exception {
		LOGGER.info("Fetching provider with npi Id {}", npiId);
		return repository.findByNpiId(npiId);
	}

	/*
	 * @see com.cardinalhealth.chh.service.HealthCareProviderService#getProviderByFirstNameAndLastName(java.lang.String, java.lang.String)
	 */
	@Override
	public HealthCareProviderHistory getProviderByFirstNameAndLastName(String firstName, String lastName)
			throws Exception {
		LOGGER.info("Fetching provider with first Name {} and last Name {}", firstName, lastName);
		return repository.findByFirstNameAndLastNameAllIgnoreCase(firstName, lastName);
	}

	/*
	 * @see com.cardinalhealth.chh.service.HealthCareProviderService#getProviderNpiAndCreatedBy(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<HealthCareProviderHistory> getProviderNpiAndCreatedBy(Long npiId, String createdByUser)
			throws Exception {
		LOGGER.info("Fetching provider with npi Id {} and created By user {}", npiId);
		return repository.findByNpiIdAndCreatedByUser(npiId, createdByUser);
	}

	/*
	 * @see com.cardinalhealth.chh.service.HealthCareProviderService#getProviderByNpiAndCreatedByCust(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<HealthCareProviderHistory> getProviderByNpiAndCreatedByCust(Long npiId, String createdBy)
			throws Exception {
		LOGGER.info("Fetching provider with npi Id using custom method {}", npiId);
		return repository.fetchNPIIdCustom(npiId, createdBy);
	}

	/*
	 * @see com.cardinalhealth.chh.service.HealthCareProviderService#getAllProviders()
	 */
	@Override
	public List<HealthCareProviderHistory> getAllProviders() throws Exception {
		LOGGER.info("Fetching all providers");
		return repository.findAll();
	}

	/*
	 * @see com.cardinalhealth.chh.service.HealthCareProviderService#deleteProvider(java.lang.Long)
	 */
	@Override
	@Transactional
	public void deleteProvider(Long providerId) throws Exception {
		LOGGER.info("Deleting provider with providerId {}", providerId);
		repository.delete(getProvider(providerId));
	}
}
