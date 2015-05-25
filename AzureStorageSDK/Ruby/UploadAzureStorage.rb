require "azure"

mycontainer = "images"
myblob = "ruby.jpg"
myfile = "c:\\demo\\AzureStorageSDK\\AzureStorageSDKDemo.jpg"

Azure.configure do |config|
    config.storage_account_name = "dev018storage"
    config.storage_access_key   = "q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg=="
end

azure_blob_service = Azure::BlobService.new

content = File.open(myfile, "rb") { |file| file.read }
blob = azure_blob_service.create_block_blob(mycontainer, myblob, content)
print "\n\"",blob.name,"\" Uploaded by Ruby!\n"