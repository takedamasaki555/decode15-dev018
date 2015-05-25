from azure.storage import BlobService 

mycontainer = 'images'
myblob = 'python.jpg'
myfile = 'c:\\demo\\AzureStorageSDK\\AzureStorageSDKDemo.jpg'

blob_service = BlobService(
    account_name='dev018storage', 
    account_key='q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg=='
)

blob_service.put_block_blob_from_path(
    mycontainer,
    myblob,
    myfile,
    x_ms_blob_content_type='image/png'
)

print "\n\""+myblob+"\" Uploaded by Python!\n"