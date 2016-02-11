package kperson

import udata.aws.directory.ConfigBasedS3Directory


object MyDirectory {

  var s3Bucket: String = null

}

class MyDirectory extends ConfigBasedS3Directory {

  import MyDirectory._

  override lazy val bucketName = s3Bucket

}