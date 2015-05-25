from azure.storage import BlobService 

mycontainer = 'images'
mypath = 'c:\\demo\\AzureStorageSDK\\images\\'

blob_service = BlobService(
    account_name='dev018storage', 
    account_key='q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg=='
)

blobs = blob_service.list_blobs(mycontainer)
print "\nDownload by Python\n"
for blob in blobs:
    blob_service.get_blob_to_path(mycontainer, blob.name, mypath+blob.name)
    print("\""+blob.name+"\" Downloaded!\n")
    blob_service.delete_blob(mycontainer, blob.name) 