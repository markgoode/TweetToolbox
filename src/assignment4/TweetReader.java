package assignment4;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.oracle.javafx.jmx.json.JSONException;


/**
 * TweetReader contains method used to return tweets from method
 * Do not change the method header
 */
public class TweetReader {

    public static boolean checkName(String message, int i) throws InvalidNameException
    {
        boolean truth = false;
        try{
            Pattern p = Pattern.compile("([^A-Za-z0-9_]+)");
            Matcher m = p.matcher(message);
            if (m.find()){
                truth = true;
                throw new InvalidNameException(message, i);

            }
        } catch(InvalidNameException e){
            e.printStackTrace();
        }
        return truth;


    }
    public static boolean checkLength(int length, String message, int i) throws InvalidLengthException
    {
        boolean truth = false;
        try{
            if ((length > 140) || (length == 0)) {
                truth = true;
                throw new InvalidLengthException(message, i);
            }
        } catch(InvalidLengthException e){
            e.printStackTrace();
        }
        return truth;
    }

    /**
     * Find tweets written by a particular user.
     *
     * @param url
     *            url used to query a GET Request from the server
     * @return return list of tweets from the server
     *
     */
    public static List<Tweets> readTweetsFromWeb(String url) throws Exception
    {
     //   String USER_AGENT = "Mozilla/5.0";
        List<Tweets> tweetList = new ArrayList<>();
     /*   ObjectMapper mapper = new ObjectMapper();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");                                                                    //establishing connection

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;


        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
*/      StringBuffer response = new StringBuffer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String USER_AGENT = "Mozilla/5.0";

            URL obj = new URL("https://api.darksky.net/forecast/91064979649945a5465006904adb8770/30.2672,-97.7431");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");                                                                    //establishing connection

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String after = response.toString();
            int x=0;
           // JSONObject json = new JSONObject(after);
           // return json;

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;

   /* try {


        Tweets[] tweets = mapper.readValue(response.toString(), Tweets[].class);

        for(int i = 0; i<tweets.length; i++) {

            if(!((tweets[i].getId() > 0) && (tweets[i].getId() < Math.pow(2, 32))))                                 //making sure id in range
            {
                continue;
            }

            if(tweets[i].getName() == null)
            {
                continue;
            }

            if(checkName(tweets[i].getName(), i)){
                continue;
            }

            Date date = null;
            try {
                if(tweets[i].getDate() == null)
                {
                    continue;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");                                    //making date format
                date = sdf.parse(tweets[i].getDate());                                                                              //parsing into format
                if (!tweets[i].getDate().equals(sdf.format(date))) {
                    date = null;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date == null) {
                continue;
            }

            if(tweets[i].getText() == null)
            {
                continue;
            }

            if(checkLength(tweets[i].getText().length(), tweets[i].getName(), i)){
                continue;
            }

       //     tweetList.add(tweets[i]);
        }




    }catch (UnrecognizedPropertyException e) {
        e.printStackTrace();
    } catch (JsonMappingException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return tweetList;
    */
    }

}

