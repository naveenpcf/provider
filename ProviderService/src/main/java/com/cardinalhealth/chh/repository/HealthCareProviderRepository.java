package com.cardinalhealth.chh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cardinalhealth.chh.model.HealthCareProviderHistory;

/**
 * Repository class to interface with the DB implements only finder methods
 * since others are automatically taken care by JPARepository.
 * *********************************************************************** There
 * is no query implementation in this interface nor anywhere else. The framework
 * generates the queries by looking up method names.
 * *********************************************************************** For
 * complex queries JPQL can be used otherwise it is optional for simple queries.
 * JPA deduces the queries using method names. It uses find By <attribute>
 * nomenclature for generating queries automatically.
 * 
 * Refer: http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 * 
 * @author guru
 */
@Transactional(readOnly = true)
@Repository
public interface HealthCareProviderRepository
		extends JpaRepository<HealthCareProviderHistory, Long>, HealthCareProviderRepositoryCustom {

	/**
	 * The method generates the queries by looking up method names. For complex
	 * queries JPQL can be used otherwise it is optional for simple queries. JPA
	 * deduces the queries using method names. It uses find By <attribute>
	 * nomenclature for generating queries automatically.
	 * 
	 * 
	 * the @query notation is commented below to show that that is an
	 * alternative means to write queries. However methodNames themselves are
	 * enough to generate queries.
	 * 
	 * @param npiId
	 *            used to find the HealthCareProviderHistory object in the query
	 * @return provider object
	 */
	// @Query("select hcp from HealthCareProviderHistory hcp where hcp.npiId = :npiId")
	// another way to write queries
	HealthCareProviderHistory findByNpiId(@Param("npiId") Long npiId);

	/**
	 * The method generates the queries by looking up method names. For complex
	 * queries JPQL can be used otherwise it is optional for simple queries. JPA
	 * deduces the queries using method names. It uses find By <attribute>
	 * nomenclature for generating queries automatically.
	 * the keywords are -----
	 * findBy<FirstName> <AND> <LastName><AllIgnoreCase> - Used for ignore case in query.
	 * 
	 * @Param in method signature is another way to pass the attributes inside
	 *        the @Query notation.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return provider object
	 */
	
	//@Query("select o from HealthCareProviderHistory o where upper(o.firstName) = upper(:firstName) AND upper(o.lastName) = upper(:lastName)")
	HealthCareProviderHistory findByFirstNameAndLastNameAllIgnoreCase(@Param("firstName") String firstName,
			@Param("lastName") String lastName);

	/**
	 * Method to query DB using account number and created by user name field.
	 * This method does not use @Query notation to demonstrate that queries can
	 * be generated by naming methods appropriately. the keywords are -----
	 * findBy<AtributeName1> <AND/OR> <AttributeName2>
	 * 
	 * @param npiId
	 *            attributename1 to be used in where clause by the framework
	 * @param createdByUser
	 *            attributename2 to be used in where clause by the framework
	 * @return
	 */
	// No @Query notation as it is optional
	List<HealthCareProviderHistory> findByNpiIdAndCreatedByUser(Long npiId, String createdByUser);
}