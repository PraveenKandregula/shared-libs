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
	    //echo "Checking out ${config.git_repo_url}"
	    echo "Current workspace ${WORKSPACE}"
	    //checkout scm: [$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: git_repo_url]]]
	    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, userRemoteConfigs: [[credentialsId: 'scm_credential', url: config.git_repo_url]]])
	}
	stage('Parse config.yml'){
	    def ymlData = readYaml file:"${WORKSPACE}/config.yml"
	    echo ymlData
	}
    }
}
