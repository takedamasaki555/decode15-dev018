var azure = require('azure-storage');

var mycontainer = 'images';
var myblob = 'nodejs.jpg';
var myfile = 'c:\\demo\\AzureStorageSDK\\AzureStorageSDKDemo.jpg';

var blobService = azure.createBlobService(
	'dev018storage',
	'q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg==');

blobService.createBlockBlobFromLocalFile(mycontainer, myblob, myfile, function (error, result, response) {
	if (!error) {
		// file uploaded
		console.log("\n\"" + myblob + "\" Uploaded by Node.js!\n");
	}
});