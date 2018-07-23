package com.beone.skillplan.repository.migration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This web listener will attempt to start a Flyway database migratiion process
 * at the startup of the server. For more information regarding Flyway, please
 * redd the <a href="https://flywaydb.org/">official website</a>.<br>
 * The SQL migration files are located in the <b>/db/migration</b> folder.
 * 
 * @author Proway FS
 *
 */
@WebListener
public class DatabaseMigrationListener implements ServletContextListener {

	private Logger logger = LoggerFactory.getLogger(DatabaseMigrationListener.class);
	
	@PersistenceContext(name = "persistenceUnit")
	private EntityManager em;
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.debug("Starting database migration");
		
		DataSource datasource = getDataSource();
		
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setDataSource(datasource);
        
        flyway.migrate();
		
		logger.debug("Database migration finished");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("{} destroyed",this.getClass().getSimpleName());
	}
	
	/**
	 * Returns a {@link DataSource} instance from the currently persistence context.
	 * 
	 * @return The datasource 
	 */
	private DataSource getDataSource() {
		return (DataSource) em.getEntityManagerFactory()
				.getProperties()
				.get("javax.persistence.jtaDataSource");
	}

}
