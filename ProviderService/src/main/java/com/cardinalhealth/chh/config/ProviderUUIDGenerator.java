package com.cardinalhealth.chh.config;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible for generating UUIDs. 
 * 
 * If the user assigns the UUIDs along with the JSON request
 * then this class will accept the assigned UUIDS and pass on the assigned UUIDs to the DB.
 * If no UUIDS are passed then this class will generate one before saving into DB.
 * 
 * @author guru
 */
public class ProviderUUIDGenerator extends UUIDGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProviderUUIDGenerator.class);
    private String entityName;

    /* 
     * @see org.hibernate.id.UUIDGenerator#configure(org.hibernate.type.Type, 
     * java.util.Properties, org.hibernate.service.ServiceRegistry)
     */
    @Override
    public void configure(Type type, Properties params, ServiceRegistry dialect) {
        entityName = params.getProperty(ENTITY_NAME);
        super.configure(type, params, dialect);
    }

    /**
     * @see org.hibernate.id.UUIDGenerator#generate(org.hibernate.engine.spi.SessionImplementor, java.lang.Object)
     * 
     * If the user assigns the UUIDs along with the JSON request
     * then this class will accept the assigned UUIDS and pass on the assigned UUIDs to the DB.
     * If no UUIDS are passed then this class will generate one before saving into DB.
     */
    @Override
    public Serializable generate(SessionImplementor session, Object object) {
        Serializable id = session
                .getEntityPersister(entityName, object)
                .getIdentifier(object, session);
        LOGGER.info("After ID generation : {}", id);
        if (id == null) {
        	LOGGER.info("Generated ones", id);
            return super.generate(session, object);
        } else {
        	LOGGER.info("Assigned generated values : {}", id);
            return id;
        }
    }
}