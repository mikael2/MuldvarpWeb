/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hials.muldvarweb.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johan
 */
public class Test {
    final static String testURLString = "http://timeedit.hials.no/4DACTION/WebShowSearch/1/1-0?wv_startWeek=1316&wv_stopWeek=1317&wv_obj1=183000&wv_text=Tekstformat";
    URL testURL;
    
    public Test() {
        System.out.println(httpGet());
    }
    
    public String httpGet(){
        
        BufferedReader bufReader = null;
        StringBuilder strBuilder;
        HttpURLConnection httpURLConnection = null;

        try {            
            testURL = new URL(URLEncoder.encode(testURLString, "UTF-8"));
            httpURLConnection.setReadTimeout(50000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            bufReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            strBuilder = new StringBuilder();
            
            String curLine;
            while((curLine = bufReader.readLine()) != null){
                strBuilder.append(curLine + "\n");
            }
            bufReader.close();
            return strBuilder.toString();
            
        } catch (ProtocolException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
}
