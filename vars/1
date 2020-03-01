String call(mavenGoal){
    def server = Artifactory.server('artifactory')
    def rtMaven = Artifactory.newMavenBuild()
    //rtMaven.deployer server: server, releaseRepo: 'example-repo-local', snapshotRepo: 'example-repo-local'
    //rtMaven.deployer.artifactDeploymentPatterns.addInclude("*.jar")
    rtMaven.tool = 'maven 3.6.3'
    print pwd()
    //def buildInfo = rtMaven.run pom: './pom.xml', goals: mavenGoal
    rtMaven.run pom: './pom.xml', goals: 'package'
    //rtMaven.deployer.deployArtifacts buildInfo
    //server.publishBuildInfo buildInfo
    sh 'tree'
}
