package com.cardinalhealth.chh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cardinalhealth.chh.model.HealthCareProviderHistory;
import com.cardinalhealth.chh.service.HealthCareProviderService;

/**
 * Front Controller for executing CRUD operation in a DB. All the requests from
 * SoapUI or any client are intercepted by this class.
 * 
 * Usage :
 * https://providerservice.apps.nonprod.cloudplatform.cardinalhealth.net/persist/<Below
 * method names> with Path variables for testing
 * 
 * @author guru
 */
@RestController
@RequestMapping(value = "/entity")
public class HealthCareProviderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCareProviderController.class);

	@Autowired
	private HealthCareProviderService providerService;

	/**
	 * Rest method for creating a provider profile in DB. It acts as the same interface for either inert of update.
	 * Whether it is an insert of update is autodetecting by the method by the usage or primary keys.
	 * 
	 * @param provider
	 * @return
	 */
	@RequestMapping(value = "/providers", method = RequestMethod.POST)
	public HealthCareProviderHistory createProvider(@RequestBody HealthCareProviderHistory provider) {
		try {
			return providerService.saveOrUpdate(provider);
		} catch (Exception e) {
			LOGGER.error("Error while creating the data", e);
			return null;
		}
	}

	/**
	 * Fetches the provider
	 * 
	 * @param providerId
	 * @return
	 */
	@RequestMapping(value = "/providers/{providerId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HealthCareProviderHistory getProvider(@PathVariable Long providerId) {
		HealthCareProviderHistory provider = null;
		try {
			provider = providerService.getProvider(providerId);
		} catch (Exception e) {
			LOGGER.error("Error while fetching the record", e);
			return null;
		}
		return provider;
	}

	/**
	 * Fetches the provider profile by NpiId which is unique per provider.
	 * 
	 * @param providerId
	 * @return
	 */
	@RequestMapping(value = "/providers/name/{firstName},{lastName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HealthCareProviderHistory getProviderByNameNpiId(@PathVariable String firstName,
			@PathVariable String lastName) {
		HealthCareProviderHistory provider = null;
		try {
			provider = providerService.getProviderByFirstNameAndLastName(firstName, lastName);
		} catch (Exception e) {
			LOGGER.error("Error while fetching the record", e);
			return null;
		}
		return provider;
	}

	/**
	 * Fetches the provider profile by NpiId and createdBy attribute.
	 * 
	 * @param providerId
	 * @return
	 */
	@RequestMapping(value = "/providers/npiUser/{npiId},{createdBy}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<HealthCareProviderHistory> getProviderByNpiandCreatedBy(@PathVariable Long npiId,
			@PathVariable String createdBy) {
		List<HealthCareProviderHistory> provider = null;
		try {
			provider = providerService.getProviderNpiAndCreatedBy(npiId, createdBy);
		} catch (Exception e) {
			LOGGER.error("Error while fetching the record", e);
			return null;
		}
		return provider;
	}

	/**
	 * Fetches the provider profile by NpiId and createdBy attribute to demonstrate custom methods in Repository.
	 * 
	 * @param providerId
	 * @return
	 */
	@RequestMapping(value = "/providers/npiCustom/{npiId},{createdBy}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<HealthCareProviderHistory> getProviderByNpiandCreatedByCust(@PathVariable Long npiId,
			@PathVariable String createdBy) {
		List<HealthCareProviderHistory> provider = null;
		try {
			provider = providerService.getProviderByNpiAndCreatedByCust(npiId, createdBy);
		} catch (Exception e) {
			LOGGER.error("Error while fetching the record", e);
			return null;
		}
		return provider;
	}

	/**
	 * Fetches all the providers in the DB.
	 * 
	 * @param providerId
	 * @return
	 */
	@RequestMapping(value = "/providers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<HealthCareProviderHistory> getAllProviders() {
		List<HealthCareProviderHistory> providerList = null;
		try {
			providerList = providerService.getAllProviders();
		} catch (Exception e) {
			LOGGER.error("Error while fetching the record", e);
			return null;
		}
		return providerList;
	}

	/**
	 * Separate interface for updating the provider. this is same as create provider in all respects 
	 * but as a feature this is provided for giving a different REST endpoint.
	 * 
	 * @param providerId
	 * @return
	 */
	@RequestMapping(value = "/providers", method = RequestMethod.PUT)
	public HealthCareProviderHistory updateProvider(@RequestBody HealthCareProviderHistory provider) {
		try {
			return providerService.saveOrUpdate(provider);
		} catch (Exception e) {
			LOGGER.error("Error while updating the data", e);
			return null;
		}
	}

	/**
	 * Deletes the provide profile.
	 * 
	 * @param providerId
	 * @return
	 */
	@RequestMapping(value = "/providers/{providerId}", method = RequestMethod.DELETE, produces = "application/json")
	public Boolean deleteProvider(@PathVariable String providerId) {
		try {
			providerService.deleteProvider(Long.parseLong(providerId));
		} catch (Exception e) {
			LOGGER.error("Error while deleting the record", e);
			return false;
		}
		return true;
	}
}