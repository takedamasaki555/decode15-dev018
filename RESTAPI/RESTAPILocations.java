import java.io.IOException;
import java.net.*;
import java.security.*;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyStoreException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
 
public class RESTAPILocations {
 
	public static void main(String[] args)
    {	
        try {
    		String url = "https://management.core.windows.net/";
    		String subscriptionId = "9bb84af6-75f7-4302-911a-5c6d30bb0341";
    		String keyStoreLocation = "c:\\jks\\d15-dev018.jks";
    		String keyStorePassword = "P@ssw0rd";
            //List locations
            url = String.format("%s/%s/locations", url,subscriptionId);
            String response = processGetRequest(new URL(url), keyStoreLocation, keyStorePassword);
            System.out.println(response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	
    private static KeyStore getKeyStore(String keyStoreName, String password) throws IOException
    {
        KeyStore ks = null;
        FileInputStream fis = null;
        try {
            ks = KeyStore.getInstance("JKS");
            char[] passwordArray = password.toCharArray();
            fis = new java.io.FileInputStream(keyStoreName);
            ks.load(fis, passwordArray);
            fis.close();
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if (fis != null) {
                fis.close();
            }
        }
        return ks;
    }
     
    private static SSLSocketFactory getSSLSocketFactory(String keyStoreName, String password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, IOException {
        KeyStore ks = getKeyStore(keyStoreName, password);
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(ks, password.toCharArray());
 
          SSLContext context = SSLContext.getInstance("TLS");
          context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
 
          return context.getSocketFactory();
    }
     
    // Source - http://www.mkyong.com/java/how-to-convert-inputstream-to-string-in-java/
    private static String getStringFromInputStream(InputStream is) {
          
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
  
        String line;
        try {
  
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
  
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
  
        return sb.toString();
  
    }
     
    private static String processGetRequest(URL url, String keyStore, String keyStorePassword) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, IOException {
        SSLSocketFactory sslFactory = getSSLSocketFactory(keyStore, keyStorePassword);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setSSLSocketFactory(sslFactory);
        con.setRequestMethod("GET");
        con.addRequestProperty("x-ms-version", "2012-03-01");
        InputStream responseStream = (InputStream) con.getContent();
        String response = getStringFromInputStream(responseStream);
        responseStream.close();
        return response;
    }
     
    private static int processPostRequest(URL url, byte[] data, String contentType, String keyStore, String keyStorePassword) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, IOException {
        SSLSocketFactory sslFactory = getSSLSocketFactory(keyStore, keyStorePassword);
        HttpsURLConnection con = null;
        con = (HttpsURLConnection) url.openConnection();
        con.setSSLSocketFactory(sslFactory);
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.addRequestProperty("x-ms-version", "2012-03-01");
        con.setRequestProperty("Content-Length", String.valueOf(data.length));
        con.setRequestProperty("Content-Type", contentType);
         
        DataOutputStream  requestStream = new DataOutputStream (con.getOutputStream());
        requestStream.write(data);
        requestStream.flush();
        requestStream.close();
        return con.getResponseCode();
    }
     
    private static int processDeleteRequest(URL url, String keyStore, String keyStorePassword) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, IOException {
        SSLSocketFactory sslFactory = getSSLSocketFactory(keyStore, keyStorePassword);
        HttpsURLConnection con = null;
        con = (HttpsURLConnection) url.openConnection();
        con.setSSLSocketFactory(sslFactory);
        con.setRequestMethod("DELETE");
        con.addRequestProperty("x-ms-version", "2012-03-01");
        return con.getResponseCode();
    }
}
