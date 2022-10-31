package org.finance;

import java.io.IOException;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogManager {

	// Pour les logs
	public static final Logger LOGGER = Logger.getLogger(TestFinance.class.getName());
	
	
	static void setupLogs () {
		Handler consoleHandler = null;
        Handler fileHandler  = null;
        Formatter simpleFormatter = null;
        
        try{
            //Creating consoleHandler and fileHandler
            consoleHandler = new ConsoleHandler();
            fileHandler  = new FileHandler("./finance.log");
             
            // Creating SimpleFormatter
            simpleFormatter = new SimpleFormatter();
            
            // Setting formatter to the handler
            consoleHandler.setFormatter(simpleFormatter);
            fileHandler.setFormatter(simpleFormatter);
            
            // definir le format du log
            consoleHandler.setFormatter(new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            });
            
            fileHandler.setFormatter(new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            });
            
            //Assigning handlers to LOGGER object
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);
                      
            //Setting levels to handlers and LOGGER
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
            
            // supprimer le log par defaut sur la console pour éviter un double log
            LOGGER.setUseParentHandlers(false);
            
            LOGGER.log(Level.INFO,"Logging prêt");
             
        }catch(IOException exception){
            LOGGER.log(Level.SEVERE, "Erreur dans le FileHandler.", exception);
        }    
	}
	
}
