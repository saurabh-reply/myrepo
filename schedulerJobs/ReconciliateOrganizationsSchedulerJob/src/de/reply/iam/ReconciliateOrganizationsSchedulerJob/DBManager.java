package de.reply.iam.ReconciliateOrganizationsSchedulerJob;

import com.thortech.util.logging.Logger;


import de.reply.iam.ReconciliateOrganizationsSchedulerJob.Exception.DBConnectionException;
import de.reply.iam.ReconciliateOrganizationsSchedulerJob.Exception.PropertyFileException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import oracle.iam.platform.Platform;

public class DBManager {

    private Properties prop;
    private Connection oimDBConnection = null;
    private static Logger logger = Logger.getLogger("IAM.CUSTOM.RECONORGANIZATIONSCHEDULERJOB");



    public DBManager(Properties prop) throws DBConnectionException, PropertyFileException {
        this.prop = prop;
        try {
            this.oimDBConnection = Platform.getOperationalDS().getConnection();
                        
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DBConnectionException("SQLException in retrieving staging data... ", e);
        } 
        
    }

     
    public void logUsers ()throws SQLException, DBConnectionException{
        PreparedStatement prepared_statement = oimDBConnection.prepareStatement(prop.getProperty("exampleQuery"));
       String qry = prop.getProperty("exampleQuery");
         ResultSet rs = prepared_statement.executeQuery(qry);
        while (rs.next()) {
          
            
            logger.info(rs.getString(1)+ ":  :"+ rs.getString(2)+ ":  :" + rs.getString(3)+ ":  :"+ rs.getString(4)+ ":  :"+ rs.getString(5) );
        }
     
        rs.close();
      
    }


    public void closeConnections() throws DBConnectionException {
        try {
            if (this.oimDBConnection != null)
                this.oimDBConnection.close();
        } catch (SQLException e) {
            throw new DBConnectionException("SQLException in closing connection... ", e);
        }
    }
}

