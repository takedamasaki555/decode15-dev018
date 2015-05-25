<?php
  require_once "azure-sdk-for-php/WindowsAzure/WindowsAzure.php";
  require_once "vendor/autoload.php"; 
  
  use WindowsAzure\Common\ServicesBuilder;
  use WindowsAzure\Common\ServiceException;
    
  $mycontainer = "images";
  $myblob = "php.jpg";
  $myfile = "c:\\demo\\AzureStorageSDK\\AzureStorageSDKDemo.jpg";
  $connectionString = "DefaultEndpointsProtocol=https;AccountName=dev018storage;AccountKey=q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg==";

   try {
    // Create blob REST proxy.
    $blobRestProxy = ServicesBuilder::getInstance()->createBlobService($connectionString);

    //Upload blob
    $blobRestProxy->createBlockBlob($mycontainer, $myblob, fopen($myfile, "r"));
    fclose($myfile);
    
    print "\n\"".$myblob."\" is Uploaded by PHP!\n";
  } catch(ServiceException $e){
    $code = $e->getCode();
    $error_message = $e->getMessage();
    echo $code.": ".$error_message;
  }
?>