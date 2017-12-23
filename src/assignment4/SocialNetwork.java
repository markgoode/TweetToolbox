package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Social Network consists of methods that filter users matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Get K most followed Users.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @param k
     *            integer of most popular followers to return
     * @return the set of usernames who are most mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getName()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like ethomaz@utexas.edu does NOT
     *         contain a mention of the username.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static List<String> findKMostFollower(List<Tweets> tweets, int k) {
        List<String> mostFollowers = new ArrayList<>();
        List<String> allMatches = new ArrayList<String>();
        Map<String, Integer> map = new HashMap< >();

        Pattern p = Pattern.compile("(?<![A-Za-z0-9_])[@]([A-Za-z0-9_]+)");                                 //regex for twitter @

        for(int i = 0; i<tweets.size(); i++)
        {
           String noCase = tweets.get(i).getText().toLowerCase();
           Matcher m = p.matcher(noCase);
     //       Matcher m = p.matcher(tweets.get(i).getText());
            while (m.find()) {
                allMatches.add(m.group());
            }
        }

        for(int i = 0; i<allMatches.size(); i++)
        {
            if(map.containsKey(allMatches.get(i))){
                map.put(allMatches.get(i), (map.get(allMatches.get(i)) + 1));                                       //incrememnt if already exists
            }
            else{
                map.put(allMatches.get(i), 1);
            }

        }

        Map.Entry<String, Integer> maxEntry = null;
        int j = 0;

    if(k <= map.size()) {
        while (j < k) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {                                                           //get max k followed
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }
            mostFollowers.add(maxEntry.getKey());
            map.remove(maxEntry.getKey());
         //   System.out.println(maxEntry.getValue() + " " + maxEntry.getKey());
            maxEntry = null;
            j++;
        }
    }
    else{
        while(!map.isEmpty())
        {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {                                  // if entries < k
                    maxEntry = entry;
                }
            }
            mostFollowers.add(maxEntry.getKey());
            map.remove(maxEntry.getKey());
        //       System.out.println(maxEntry.getValue() + " " + maxEntry.getKey());
            maxEntry = null;

        }
    }

        return mostFollowers;
    }

    /**
     * Find all cliques in the social network.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     *
     * @return list of set of all cliques in the graph
     */
    public static List<Set<String>> findCliques(List<Tweets> tweets) {
        List<Set<String>> result = new ArrayList<Set<String>>();
        return result;
    }
}


