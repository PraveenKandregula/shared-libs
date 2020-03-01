import groovy.json.JsonSlurper
def call(body) 
{
    def config =[:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    def scm_url
    def ismvnProject
    def mavenGoal
    def sonarScan
    node{
        stage('Clean-up WORKSPACE'){
            cleanWs()
        }
	stage('scm-checkout'){
            gitCheckout.call("${config.scm_url}")
	}
        stage('mvn-build'){
            script{
                if ("${config.ismvnProject}")
		    mvnBuild.call("${config.mavenGoal}".toString())
                else
                    print "This is not maven project"
            }
        }
        stage('sonar-scan'){
            script{
                if ("${config.sonarScan}")
                    sonarScan.call()
                else
                    print "Sonar scan is not required"
            }
        }
    }
}
