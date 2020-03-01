String call(){
    def server = Artifactory.server('artifactory')
    def uploadSpec = """{
	                 "files": [{
	                            "pattern": "target/\\*.jar",
		                    "target": "maven-shared-libs-test/maven-shared-libs-test/${currentBuild.displayName}",
      		                    "recursive": "true",
		                    "regexp": "true"
	                          }]	
                     }"""
    server.upload spec: uploadSpec, buildInfo: buildInfo
    server.publishBuildInfo buildInfo
}
