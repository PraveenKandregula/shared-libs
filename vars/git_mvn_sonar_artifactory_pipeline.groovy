import groovy.json.JsonSlurper
def call(body) 
{
    def config =[:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    def scm_url
    node{
	stage('scm-checkout'){
            gitCheckout.call("${config.scm_url}")
	}
    }
}
