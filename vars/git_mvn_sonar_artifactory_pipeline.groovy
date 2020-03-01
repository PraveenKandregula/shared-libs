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
    node{
	stage('scm-checkout'){
            gitCheckout.call("${config.scm_url}")
	}
        stage('mvn-build'){
            script{
                if ("${config.ismvnProject}")
		    mvnBuild.call("${config.mavenGoal}")
                else
                    print "This is not maven project"
            }
        }
    }
}
