def call(){
    def mvn_home = tool 'maven 3.6.3'
    withSonarQubeEnv('sonarqube'){
        sh "'${mvn_home}/bin/mvn' sonar:sonar -Dsonar.projectVersion=${currentBuild.displayName} -Dsonar.projectName='jb-hello-world-maven' -Dsonar.projectKey='jb-hello-world-maven'"
    }
}
