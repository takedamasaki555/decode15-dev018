var azure = require('azure-storage');


var mycontainer = 'images';
var mypath = 'c:\\demo\\AzureStorageSDK\\images\\';

var blobService = azure.createBlobService(
	'dev018storage',
	'q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg=='
	);

blobService.listBlobsSegmented(mycontainer, null, function(error, result, response) {
	if (!error) {
		console.log("\nDownload by Node.js\n");
				
		for(var i=0;i<result.entries.length;i++) {
			var fs = require('fs');
			blobService.getBlobToStream(
				mycontainer, 
				result.entries[i].name,
				fs.createWriteStream(mypath + result.entries[i].name), 
				function(error,result, response){}
			);
			console.log("\"" + result.entries[i].name + "\" Downloaded!");
			//blobService.deleteBlob(mycontainer, result.entries[i].name, function(error3, response3){});
		}
	}
});
