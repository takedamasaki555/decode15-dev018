import java.io.FileOutputStream;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

public class DownloadAzureStorage {
	static String mycontainer = "images";
	static String mypath = "c:\\demo\\AzureStorageSDK\\images\\";

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
		    System.out.println("\nDownload by Java\n");
		    // Loop over blobs within the container and output the URI to each of them.
		    for (ListBlobItem blobItem : container.listBlobs()) {
		    	 // If the item is a blob, not a virtual directory.
		        if (blobItem instanceof CloudBlob) {
		            // Download the item and save it to a file with the same name.
		             CloudBlob blob = (CloudBlob) blobItem;
		             blob.download(new FileOutputStream(mypath + blob.getName()));
		             System.out.println("\""+blob.getName()+"\" Downloaded!\n");
		             // Delete the blob.
		             blob.deleteIfExists();
		        }
		    }
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
	}
}
