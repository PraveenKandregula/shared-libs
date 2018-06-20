import groovy.json.JsonSlurper

def call(body) {
    // evaluate the body block, and collect configuration into the object
   def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    def test 

    node 
    {
        stage('Test')
        {
	      println "${config.test}"
        }
    }
}
