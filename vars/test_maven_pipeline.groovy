import groovy.json.JsonSlurper
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.DumperOptions
import static org.yaml.snakeyaml.DumperOptions.FlowStyle.BLOCK

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
	    if fileExists "${config.yml_file_name}"
		echo "${config.yml_file_name} is found"
	    else{
		echo ""${config.yml_file_name} is not found"
	    }	        
	    def ymlData = readFile "${WORKSPACE}/${config.yml_file_name}"
	    echo ymlData
	}
    }
}
