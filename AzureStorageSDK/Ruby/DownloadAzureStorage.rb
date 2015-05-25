require "azure"

mycontainer = "images"
mypath = "c:\\demo\\AzureStorageSDK\\images\\"

Azure.configure do |config|
    config.storage_account_name = "dev018storage"
    config.storage_access_key   = "q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg=="
end

azure_blob_service = Azure::BlobService.new

containers = azure_blob_service.list_containers()
print "Download by Ruby\n"
containers.each do |container|
  blobs = azure_blob_service.list_blobs(mycontainer)
  blobs.each do |blob|
    blob, content = azure_blob_service.get_blob(mycontainer,blob.name)
    myfile = mypath+blob.name
    File.open(myfile,"wb") {|f| f.write(content)}
    print "\"",blob.name,"\" Downloaded!\n"
    azure_blob_service.delete_blob(mycontainer, blob.name)
  end
end
