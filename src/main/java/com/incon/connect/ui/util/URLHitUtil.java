package com.incon.connect.ui.util;

/**
 * 
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bogavalli.srinivas
 *
 */
public class URLHitUtil {
	
	private static Logger logger = LoggerFactory.getLogger(URLHitUtil.class);
	
	final String USER_AGENT = "Mozilla/5.0";

	public static String hittheData(String urlStr) throws IOException {
        InputStreamReader input = null;
        try {
            URL url = new URL(urlStr);
            URLConnection uc = url.openConnection();
            input = new InputStreamReader(uc.getInputStream());
            BufferedReader in = new BufferedReader(input);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	logger.info("Message response : "+inputLine);
            	System.out.println(inputLine);
                break;
            }
            in.close();
            input.close();
            return inputLine;
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            logger.error(" :: URLHitUtil :: hittheData :: error while hitting url :: "+urlStr+ ":: "+ex);
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return null;

    }
	
	private void sendPost() throws Exception {

		String url = "http://bhashsms.com/api/sendmsg.php";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
                                    response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());

	}

	
	
	
	public static String hitPostData(String urlStr) throws IOException {
        InputStreamReader input = null;
        try {
        	
        	String strurl = "http://bhashsms.com/api/sendmsg.php";
        	
        	final String USER_AGENT = "Mozilla/5.0";
            URL url = new URL(strurl);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
          //add reuqest header
    		uc.setRequestMethod("POST");
    		uc.setRequestProperty("User-Agent", USER_AGENT);
    		uc.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
    		
    		
    		String urlParameters = "user=bogavalli&pass=98877665&sender=ValueB&phone=7799879990,7799879990,7799879990&text=Cipla+Augexp+TP745%2C+RainInd+TP140%2CGAEL+TP+90%2CSSPDL+TP+85%2C+JindalPoly+TP1000%2CTriveniGlassTP+55%2C+Tantia+Con+TP100.Full+reports%2CBkgNews+on+denofwealth.blogspot.in&priority=sdnd&stype=normal";
    		
    		// Send post request
    		uc.setDoOutput(true);
    		DataOutputStream wr = new DataOutputStream(uc.getOutputStream());
    		wr.writeBytes(urlParameters);
    		wr.flush();
    		wr.close();
    		
    		
            input = new InputStreamReader(uc.getInputStream());
            BufferedReader in = new BufferedReader(input);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	System.out.println(inputLine);
                break;
            }
            in.close();
            input.close();
            return inputLine;
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            logger.error(" :: URLHitUtil :: hittheData :: error while hitting url :: "+urlStr+ ":: "+ex);
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return null;

    }
	
}
