String call(){
    def server = Artifactory.server('artifactory')
    def buildInfo = Artifactory.newBuildInfo()
    def uploadSpec = """{
	                 "files": [
                                    {
	                            "pattern": "target/*.jar",
		                    "target": "maven-shared-libs-test/maven-shared-libs-test/${currentBuild.displayName}/",
      		                    "recursive": "true",
	                            }
                                  ]	
                     }"""
    server.upload spec: uploadSpec, buildInfo: buildInfo
    server.publishBuildInfo buildInfo
}
