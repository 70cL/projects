package Log;

import org.apache.commons.logging.Log;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class LogInfo {
    private static final Logger log = Logger.getLogger(LogInfo.class);

    public LogInfo()
    {
        BasicConfigurator.configure();
    }

    public void Infos(String str)
    {

        log.info(str);
    }

    public void error(String str)
    {
        log.error(str);
    }
}
