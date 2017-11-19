import groovy.json.JsonSlurper
def call(body) 
{
    def config =[:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    node
    {
	stage('Build')
	{
		print "Build stage steps here..."
	}
	stage('Test')
	{
		print "Test stage steps here..."
	}
	stage('Dev')
	{
		print "Dev stage steps here..."
	}
	stage('Preprod')
	{
		print "Preprod stage steps here..."
	}
	stage('Manual approval')
	{
		input 'Can this be promoted to Prod?'
	}
	stage('Prod')
	{
		print "Prod stage steps here..."
	}
    }
}
