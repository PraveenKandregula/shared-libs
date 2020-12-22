import groovy.json.JsonSlurper
def call(body) 
{
    def config =[:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    def git_repo_url

    node{
	stage('Checkout'){
	    echo "Checking out ${git_repo_url}"
	    //checkout scm: [$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: git_repo_url]]]
	    checkout([$class: 'GitSCM', doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'scm_credential', refspec: '+refs/heads/master:refs/remotes/origin/master', url: git_repo_url]]])
	}
	
    }
}
