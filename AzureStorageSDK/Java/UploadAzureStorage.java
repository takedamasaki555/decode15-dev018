import java.io.File;
import java.io.FileInputStream;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

public class UploadAzureStorage {
	static String mycontainer = "images";
	static String myfile = "c:\\demo\\AzureStorageSDK\\AzureStorageSDKDemo.jpg";
	static String myblob  = "java.jpg";

	static String storageConnectionString = "DefaultEndpointsProtocol=https;" + 
		    "AccountName=dev018storage;" + 
		    "AccountKey=q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg==";
	
	public static void main(String[] args) {
		try
		{
		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

		    // Create the blob client.
		    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

		   // Retrieve reference to a previously created container.
		    CloudBlobContainer container = blobClient.getContainerReference(mycontainer);

		    // Create or overwrite the "myimage.jpg" blob with contents from a local file.
		    CloudBlockBlob blob = container.getBlockBlobReference(myblob);
		    File source = new File(myfile);
		    blob.upload(new FileInputStream(source), source.length());
		    System.out.println("\n\"" + myblob + "\" Uploaded by Java!");
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
		
	}
}
