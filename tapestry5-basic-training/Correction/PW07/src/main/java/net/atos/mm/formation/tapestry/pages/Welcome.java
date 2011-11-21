package net.atos.mm.formation.tapestry.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

public class Welcome
{
	
    /**
     * This variable is used to store in session the first date access of the user on this
     * page
     */
    @Persist
    private Date sessionStart;

    /**
     * This variable is used to store in session the last message from Hilo Game.
     * 
     */
    @Persist
    private String messageFromHilo;
    
    /**
     * This object can be returned by event method to redirect to the
     * corresponding page. This page must be injected by Tapestry
     */
    @InjectPage
    private Guess guess;

    /**
     * This variable must be initialized only once for this page instance and
     * not be deleted after.
     */
    @Persist
    private Long seed;
    
    @Inject
	private Logger logger;
    
    @OnEvent(EventConstants.ACTIVATE)
    public void initializeRandomizer()
    {
		if (seed == null)
		{
		    seed = System.currentTimeMillis();
		    
		    logger.info("Initialize randomizer with seed :" + seed);
		}
		if (sessionStart == null)
		{
		    sessionStart = new Date();
		
		    logger.info("Initialize first page access time :" + sessionStart);
		}

    }
    
    public String getMessageFromHilo()
    {
    	return messageFromHilo;
    }

    public void setMessageFromHilo(String messageFromHilo)
    {
    	this.messageFromHilo = messageFromHilo;
    }

    public Date getSessionStart()
    {
    	return sessionStart;
    }

    public void setSessionStart(Date sessionStart)
    {
    	this.sessionStart = sessionStart;
    }

    /**
     * This method must be call on actionlink to setup the hilo Game and
     * redirect the user to the Guess Page.
     * 
     * @return Guess Tapestry handle this object to redirect the user to thus pa
     */
    @OnEvent(value = EventConstants.ACTION, component = "hilo")
    private Guess startHiloGame()
    {

		logger.info("Initializing Hilo Game");
	
		guess.setupGame(seed);
	
		return guess;
    }

    /**
     * This method is used by the Tapestry "if" component to display or not a
     * message from Hilo Game in the welcome page.
     * 
     * @return true if hilo has been played and successful, false otherwise
     */
    public boolean getLastHiloMessage()
    {
    	if((messageFromHilo != null && !("".equals(messageFromHilo))))
    	{
	    	//If the User found the good number, we reinit the seed value
	    	seed = System.currentTimeMillis();
	    	return true;
    	}
    	return false;
    }
    
    public SimpleDateFormat getFormat(){
    	return new SimpleDateFormat("dd/MM/yyyy");
    }

}