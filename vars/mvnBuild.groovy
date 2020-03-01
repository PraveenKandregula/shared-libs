String call(mavenGoal){
    def server = Artifactory.server('artifactory')
    def rtMaven = Artifactory.newMavenBuild()
    rtMaven.deployer server: server, releaseRepo: 'example-repo-local', snapshotRepo: 'example-repo-local'
    rtMaven.deployer.artifactDeploymentPatterns.addInclude("*.jar")
    rtMaven.tool = 'maven'
    def buildInfo = rtMaven.run pom: 'maven-example/pom.xml', goals: mavenGoal
    rtMaven.deployer.deployArtifacts buildInfo
    server.publishBuildInfo buildInfo
}
