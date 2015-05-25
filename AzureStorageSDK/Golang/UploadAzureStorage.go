package main

import(
  "fmt"
  "os"
  "github.com/loldesign/azure"
)

func main() {
	var mycontainer = "images"
  var myblob = "golang.jpg"
  var myfile = "c:\\demo\\AzureStorageSDK\\AzureStorageSDKDemo.jpg"
  
  var accountName = "dev018storage"
  var secret = "q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg=="

  blob := azure.New(accountName, secret)
  file, err := os.Open(myfile)

  if err != nil {
    fmt.Println(err)
  }

  res, err := blob.FileUpload(mycontainer, myblob, file)

  if err != nil {
    fmt.Println(err)
  }

  fmt.Printf("\n%s\n\"%s\" Uploaded by Golang\n", res.Status, myblob)
}