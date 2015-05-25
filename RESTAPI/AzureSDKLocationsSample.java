import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.microsoft.windowsazure.core.utils.KeyStoreType;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.management.*;
import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.management.configuration.ManagementConfiguration;
import com.microsoft.windowsazure.management.models.LocationsListResponse;
import com.microsoft.windowsazure.management.models.LocationsListResponse.Location;

public class AzureSDKLocationsSample {
		static String uri = "https://management.core.windows.net/";
		static String subscriptionId = "9bb84af6-75f7-4302-911a-5c6d30bb0341";
		static String keyStoreLocation = "c:\\jks\\d15-dev018.jks";
		static String keyStorePassword = "P@ssw0rd";

		public static void main(String[] args) throws IOException, URISyntaxException, ServiceException, ParserConfigurationException, SAXException {
			Configuration config = ManagementConfiguration.configure(
					new URI(uri),
					subscriptionId,
					keyStoreLocation, // the file path to the JKS
					keyStorePassword, // the password for the JKS
					KeyStoreType.jks // flags that I'm using a JKS keystore
	    	);
			// create a management client to call the API
			ManagementClient client = ManagementService.create(config);

			// get the list of regions
			LocationsListResponse response = client.getLocationsOperations().list();
			ArrayList<Location> locations = response.getLocations();

			// write them out
			for( int i=0; i<locations.size(); i++){
				System.out.println(locations.get(i).getDisplayName());
			}
	  }
}
