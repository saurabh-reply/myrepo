package de.reply.iam.ReconciliateOrganizationsSchedulerJob;

import com.thortech.util.logging.Logger;

import de.reply.iam.ReconciliateOrganizationsSchedulerJob.Exception.DBConnectionException;
import de.reply.iam.ReconciliateOrganizationsSchedulerJob.Exception.PropertyFileException;

import java.sql.SQLException;

import java.util.LinkedList;
import java.util.Properties;


public class ReconciliateOrganizationsEngine {

    private OIMclient oimclient;
    private Properties prop;
    private static Logger logger = Logger.getLogger("IAM.CUSTOM.RECONORGANIZATIONSCHEDULERJOB");
    private DBManager dbManager;
    ReconciliateOrganizationsEngine(Properties prop) {
    
       
    
        try {
                    this.prop = prop;
                    logger.debug("Initializing dbManager");
                 //   String qry = prop.getProperty("exampleQuery");
                    
                   // logger.info(qry);
                    this.dbManager = new DBManager(this.prop);

                    this.dbManager.logUsers();
 
                } catch (DBConnectionException e) {
                    logger.error("DB error",e);
                } catch (PropertyFileException e) {
                    logger.error("Property file error",e);
                } catch (SQLException e) {
        } finally {
                    if (this.dbManager != null) {
                        try {
                            dbManager.closeConnections();
                        } catch (DBConnectionException e) {
                            //ignore
                        }
                    }
                }
    
    

    // logger.info("Parent organizations will be reconciled! Creating mapping for parent organizations");
    
        
    }
    
}





