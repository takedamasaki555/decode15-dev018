<?php
  require_once "azure-sdk-for-php/WindowsAzure/WindowsAzure.php";
  require_once "vendor/autoload.php"; 
  
  use WindowsAzure\Common\ServicesBuilder;
  use WindowsAzure\Common\ServiceException;
    
  $mycontainer = "images";
  $mypath = "c:\\demo\\AzureStorageSDK\\images\\";
  
  $connectionString = "DefaultEndpointsProtocol=https;AccountName=dev018storage;AccountKey=q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg==";
  
  // Create blob REST proxy.
  $blobRestProxy = ServicesBuilder::getInstance()->createBlobService($connectionString);
  
  try {
    // List blobs.
    $blob_list = $blobRestProxy->listBlobs($mycontainer);
    $blobs = $blob_list->getBlobs();
    echo "\nDownload by PHP\n";
    foreach($blobs as $blob)
    {
      $dl_blob = $blobRestProxy->getBlob($mycontainer, $blob->getName());
      $fp = fopen($mypath.$blob->getName(), "w"); 
      fputs($fp, stream_get_contents($dl_blob->getContentStream(),-1)); 
      fclose($fp); 
      echo "\"".$blob->getName()."\" Downloaded!\n";
      $blobRestProxy->deleteBlob($mycontainer, $blob->getName());
    }
    
  } catch(ServiceException $e){
    $code = $e->getCode();
    $error_message = $e->getMessage();
    echo $code.": ".$error_message;
  }
?>