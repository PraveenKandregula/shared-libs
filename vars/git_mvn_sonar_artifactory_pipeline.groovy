import groovy.json.JsonSlurper
def call(body) 
{
    def config =[:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    def scm_url
    def ismvnProject
    def mavenStep
    node{
	stage('scm-checkout'){
            gitCheckout.call("${config.scm_url}")
	}
        stage('mvn-build'){
            script{
                if ("${config.ismvnProject}")
		    mvnBuild.call("${config.mavenStep}")
                else
                    print "This is not maven project"
            }
        }
    }
}
