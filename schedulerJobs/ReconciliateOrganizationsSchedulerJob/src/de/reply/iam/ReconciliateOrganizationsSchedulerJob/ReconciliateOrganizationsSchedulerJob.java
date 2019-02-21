package de.reply.iam.ReconciliateOrganizationsSchedulerJob;

import com.thortech.util.logging.Logger;

import de.reply.iam.ReconciliateOrganizationsSchedulerJob.Exception.ReconciliateOrganizationsSchedulerJobException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import oracle.iam.provisioning.exception.AccountNotFoundException;
import oracle.iam.provisioning.exception.EntitlementNotProvisionedException;
import oracle.iam.provisioning.exception.GenericProvisioningException;
import oracle.iam.provisioning.exception.UserNotFoundException;
import oracle.iam.scheduler.vo.TaskSupport;

public class ReconciliateOrganizationsSchedulerJob extends TaskSupport {

    private Logger logger =
        Logger.getLogger("IAM.CUSTOM.RECONORGANIZATIONSCHEDULERJOB");
    
    private Properties prop;

    public HashMap getAttributes() {
        return null;
    }

    public void setAttributes() {
    }

    public void execute(HashMap options)throws Exception {
        
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
      //  long startTimestamp = new Date().getTime();
        logger.info("ReconciliateOrganizationsSchedulerJob started.");
        /* Read Schedule Job Parameters */
        String filePath = (String) options.get("filePath");;
       FileReader reader = new FileReader(filePath);
        Properties prop;
        prop = new Properties();
       prop.load(reader);
        new ReconciliateOrganizationsEngine(prop);
          
    }
        
}

