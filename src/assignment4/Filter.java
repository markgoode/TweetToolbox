package assignment4;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.ZoneId;


/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     *
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweets> writtenBy(List<Tweets> tweets, String username) {
        List<Tweets> filteredList = new ArrayList<Tweets>();

        for(int i = 0; i<tweets.size(); i++)
        {
            if(tweets.get(i).getName().equalsIgnoreCase(username))                                      //ignore case to make comparing usernames same
            {
                filteredList.add(tweets.get(i));
            }
        }

        return filteredList;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     *
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweets> inTimespan(List<Tweets> tweets, Timespan timespan) {
        List<Tweets> filteredList = new ArrayList<Tweets>();

        Instant start = timespan.getStart();
        Instant end = timespan.getEnd();

        for(int i = 0; i<tweets.size(); i++)
        {
            Instant result = Instant.parse(tweets.get(i).getDate());

            if(!(result.isAfter(start) || result.equals(start)))                                            // get bounds for start and end
            {
                continue;
            }
            else if(!(result.isBefore(end) || result.equals(end)))
            {
                continue;
            }
            else{
                filteredList.add(tweets.get(i));                                    //add matching to filtered list
            }

        }


        return filteredList;
    }

    /**
     * Find tweets that contain certain words.
     *
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets.
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when
     *         represented as a sequence of nonempty words bounded by space characters
     *         and the ends of the string) includes *at least one* of the words
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweets> containing(List<Tweets> tweets, List<String> words) {
        List<Tweets> filteredList = new ArrayList<Tweets>();

        int setFlag = 0;

        for(int i = 0; i<tweets.size(); i++)
        {
            String current = tweets.get(i).getText().toLowerCase();                                     //let all letters be same case
            for(int j = 0; j<words.size(); j++)
            {
                String lWord = words.get(j).toLowerCase();
                String word1 = " " + lWord + " ";
                String word2 = lWord + " ";                                                             //handling space delimiting and front and back of string
                String word3 = " " + lWord;
                if(current.contains(word1))
                {
                    setFlag = 1;
                    break;
                }
                else if(current.startsWith(word2))
                {
                    setFlag = 1;
                    break;
                }
                else if(current.endsWith(word3))
                {
                    setFlag = 1;
                    break;
                }
            }

            if(setFlag == 1)
            {
                filteredList.add(tweets.get(i));                                                                                    //if match add to filtered
            }

            setFlag = 0;                                                                                                               //reset flag

        }
        return filteredList;
    }
}