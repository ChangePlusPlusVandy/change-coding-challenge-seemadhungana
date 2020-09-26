import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GuessTheTweet {




    public static void main(String[] args) throws TwitterException {

        //Set up the Twitter API with authentication codes
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("pbU00rXTSUKs5z86Gf9pl1OE0")
                .setOAuthConsumerSecret("TbR1TXW1O52ksNkhJ9uax85eFlPm9Ia3kh9D0Ox55FT68ZDu7H")
                .setOAuthAccessToken("1029492180-RR0mvNZDBGdrEO2xmYDP6QociSAZMfcsmXJm4Dg")
                .setOAuthAccessTokenSecret("ATpsPSOsSTzLCilsnVMCdAdaAikt6uoMKeLCH8TEpw4Pi");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        //Initialize point value
        int points = 0;

        //Create random number generator object to randomly choose from list of tweets
        Random generator = new Random();
        int realNum = generator.nextInt(41);

        //Create scanner object to hold player input
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String playerName = userInput.nextLine();
        System.out.println("Welcome to 'Guess That Tweet' " + playerName + "!");
        System.out.println("Rules: A tweet will be displayed on the screen and you must decide whether " +
                "Kanye West or Elon Musk tweeted the message. Please enter either 'K' or 'E'"+
                        "to submit your guess");

        //Create lists of tweets from Kanye West and Elon Musk
        List<Status> statusesKanye = twitter.getUserTimeline("@kanyewest");
        List<Status> statusesElon = twitter.getUserTimeline("@elonmusk");

        //Combine lists of tweets from each person
        List<Status> allTweets = new ArrayList<Status>();
        allTweets.addAll(statusesElon);
        allTweets.addAll(statusesKanye);


        //For loop that iterates through all values in the list allTweets
        for (Status status : allTweets){

            //Picks a random tweet in allTweets to display to the player
            status = allTweets.get(realNum);

            //Asks player to decide who tweeted a random tweet
            System.out.println("\nWho tweeted this tweet?\n");
            System.out.println(status.getText());
            System.out.println("\nPlease enter either 'E' for Elon Musk or 'K' Kanye West");

            String guess = userInput.nextLine();
            char charGuess = guess.toUpperCase().charAt(0);

            if (charGuess == 'E'){
                guess = "Elon Musk";
            }
            else if (charGuess == 'K'){
                guess = "ye";
            }


            //Checks to see if the player's guess was correct or incorrect
            if (guess.equals(status.getUser().getName())){
                System.out.println("Congrats! You got it right!!\n");
                points += 1;
            }
            else{
                System.out.println("Sorry that was incorrect\n");}

            System.out.println("Your total points are: "+ points);
            realNum = generator.nextInt(41);
        }


    }


    }

