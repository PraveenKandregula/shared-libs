String call(){
    def server = Artifactory.server('artifactory')
    def uploadSpec = """{
	                 "files": [{
	                            "pattern": "target/\\*.jar",
		                    "target": "example-repo-local/${currentBuild.displayName}",
      		                    "recursive": "true",
		                    "regexp": "true"
	                          }]	
                     }"""
    def buildInfo = server.upload(spec: uploadSpec)
    server.publishBuildInfo(buildInfo)
}
