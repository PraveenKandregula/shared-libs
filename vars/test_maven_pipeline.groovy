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
	stage('Parse ${config.yml_file_name}'){
	    fileExists "${config.yml_file_name}"
	    def ymlData = readYaml file:"${WORKSPACE}/${yml_file_name}"
	    echo ymlData
	}
    }
}
