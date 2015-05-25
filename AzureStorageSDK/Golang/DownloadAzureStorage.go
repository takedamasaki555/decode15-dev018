package main

import(
  "fmt"
  "os"
  "io/ioutil"
  "github.com/loldesign/azure"
)

func main() {
	var mycontainer = "images"
  var mypath = "c:\\demo\\AzureStorageSDK\\images\\"
  
  var accountName = "dev018storage"
  var secret = "q4dLJhW3zLw8hmdjL88w0vc2KHrbjMJlXumFB0eZVpBoeks1MKlZh7+pW36fmlWmBMx+bzwvWcYMU8p4MGhbcg=="

  blob := azure.New(accountName, secret)
  blobs, err := blob.ListBlobs(mycontainer)

  if err != nil {
    fmt.Println(err)
  }
  fmt.Printf("\nDownload by Golnag\n")
  for _, file := range blobs.Itens {
    myfile := mypath + file.Name
    res, err := blob.FileDownload(mycontainer, file.Name)

    if err != nil {
      fmt.Println(err)
    }

    contents, ok := ioutil.ReadAll(res.Body)

    if ok != nil {
      fmt.Println(ok)
    }
    
    ok = ioutil.WriteFile(myfile, contents, os.ModePerm) // don't do that with large files!
    fmt.Printf("\"%s\" Downloaded!\n", file.Name)
    blob.DeleteBlob(mycontainer, file.Name)
  }
}