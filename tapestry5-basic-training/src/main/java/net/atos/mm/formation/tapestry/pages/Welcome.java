package net.atos.mm.formation.tapestry.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;


public class Welcome
{

    /**
     * This variable is used to store in session the first date access on this
     * page
     */
	@Property
    private Date sessionStart;

    /**
     * This variable is used to store in session the last message from Hilo Game
     */
	
    private String messageFromHilo;

    /**
     * This object can be returned by event method to redirect to the
     * corresponding page. This page must be injected by Tapestry
     */
	@Property
    private Guess guess;

    /**
     * This variable must be initialized only once for this page instance and
     * not be deleted after.
     */
	@Property
    private Long seed;

	@Inject
	private Logger logger;
	
    @OnEvent("activate")
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

    /**
     * This method must be call on actionlink to setup the hilo Game and
     * redirect the user to the Guess Page.
     * 
     * @return Guess Tapestry handle this object to redirect the user to thus pa
     */
    private Guess startHiloGame()
    {

	logger.info("Initializing Hilo Game");

	// Setup hilo game by using the Guess injected page
	// There is a corresponding setup method in Guess class
	// ...

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
    
    public String getMessageFromHilo(){
    	return messageFromHilo;
    }
    
    public void setMessageFromHilo(String messageFromHilo){
    	this.messageFromHilo = messageFromHilo;
    }
}
