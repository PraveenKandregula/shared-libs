import groovy.json.JsonSlurper

def call(body) 
{
    def config =[:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    def git_repo_url
    def yml_file_name
    def ymlData

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
	    ymlData = readFile "${WORKSPACE}/${config.yml_file_name}"
	    //step{
   		//ymlData = readYaml file: "${WORKSPACE}/${config.yml_file_name}"
	    //}
	    echo ymlData
	}
	stage('Build'){
	    //echo ymlData.get('build').get('projectFolder')
	    //echo ymlData.get('build').get('buildCommand')
	    dir("${WORKSPACE}/project"){
		sh 'mvn clean test'
	    }
	}
	stage('Database'){
	    dir("${WORKSPACE}/database"){
		sh 'mvn clean test -Dscope=FlywayMigration'
	    }
	}
	stage('Deploy'){
	    dir("${WORKSPACE}/database"){
	        sh 'mvn clean install'
	    }
	}
	stage('Test'){
	    echo "Test stage"
	    parallel performance: {
		sh 'mvn clean test -Dscope=performance'
	    }, regression: {
		sh 'mvn clean test -Dscope=regression; exit 1'
	    }, integration: {
		sh 'mvn clean test -Dscope=integration'
	    }
	}
    }
    //post{
    //	success{
    //	    echo "Successful"
    //	}
    //	failure{
    //	    echo "Failure"
    //	    echo "Send email"
    //	}
    //}
}
