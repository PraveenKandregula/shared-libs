import groovy.json.JsonSlurper
def call(body) {
	def config =[:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()
	node {
		stage('Checkout') {
			checkout scm: [$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: 'https://github.com/Brialius/test-maven-project.git']]]
		}
		
		
	}

