import groovy.json.JsonSlurper

def call(body) 
{
    def config =[:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    def git_repo_url
    def yml_file_name

    node{
	stage('Checkout'){
	    //echo "Checking out ${config.git_repo_url}"
	    echo "Current workspace ${WORKSPACE}"
	    //checkout scm: [$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: git_repo_url]]]
	    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, userRemoteConfigs: [[credentialsId: 'scm_credential', url: config.git_repo_url]]])
	}
	stage('Parse yml file'){
	    fileStatus = fileExists config.yml_file_name
	    if (fileStatus)
		echo "${config.yml_file_name} is found"
	    else{
		echo "${config.yml_file_name} is not found"
	    }	        
	    def ymlData = readFile "${WORKSPACE}/${config.yml_file_name}"
	    //echo ymlData
	}
	stage('Build'){
	    echo ymlData['build']['projectFolder']
	    echo ymlData['build']['buildCommand']
	}
    }
}
