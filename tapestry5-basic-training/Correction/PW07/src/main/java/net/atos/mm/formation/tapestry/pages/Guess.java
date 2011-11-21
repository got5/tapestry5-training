package net.atos.mm.formation.tapestry.pages;

import java.util.Random;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class Guess
{

    /**
     * This variable is used to store in session the last message for the user
     */
	@Property
	@Persist
    private String message;

    /**
     * This variable is used to store the current user choice
     */
    @Property
    private int guess;

    /**
     * This variable is used to store the target number
     */
    @Persist
    private int target;

    /**
     * Used to count the user attempts
     */
    @Persist
    private int count;
    
    /**
     * Used to create the random target
     */
    private long seed;

    /**
     * Used to redirect the user after a successful hit and then display the
     * game result
     */
    @InjectPage
    private Welcome welcomePage;


    /**
     * This method must be called on action event to verify if the user has
     * clicked on the target number.
     * 
     * @param userChoice
     *            the number clicked by the user
     * @return return an instance of Welcome page if the user win the hilo, null
     *         if verification fails
     */
    @OnEvent(value = EventConstants.ACTION, component = "link")
    private Object verifyChoice(int userChoice)
    {

    	count++;

		if (userChoice == target)
		{
		    welcomePage.setMessageFromHilo(String.format("You have been successful in %d hits", count));
		    return welcomePage;
		}

		if (userChoice < target)
		{
		    message = String.format("%d is too low.", userChoice);
		} else
		{
		    message = String.format("%d is too high.", userChoice);
		}
	
		return null;
    }

    /**
     * This method is called by the welcome page when the user click on the hilo
     * action link. The Welcome page create a random number that the user has to
     * find.
     * 
     * @param seed
     *            the seed to initialize a random number
     */
    public void setupGame(long seed)
    {
		Random random = new Random(seed);
	
		this.count = 0;
		this.message = null;
		this.target = random.nextInt(10) + 1;
		this.seed = seed;
    }
    
 // Aller plus loin
    @OnEvent(EventConstants.ACTIVATE)
    private void init(int guess, int count, long seed)
    {
		Random random = new Random(seed);
	
		this.seed = seed;
		this.guess = guess;
		this.count = count;
		this.target = random.nextInt(10) + 1;
    }

    @OnEvent(EventConstants.PASSIVATE)
    public Object[] getContext()
    {
    	return (new Object[] { Integer.valueOf(guess), Integer.valueOf(count), Long.valueOf(seed) });
    }

}
