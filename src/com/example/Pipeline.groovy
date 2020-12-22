package com.example

class Pipeline {
    def script
    def configurationFile

    //Pipeline(script, configurationFile) {
    //    this.script = script
    //    this.configurationFile = configurationFile
    //}

    Pipeline(steps) {
	this.steps=steps
	print this
    }
    def execute() {
	//System.out.println $script
	//System.out.println $configurationFile
	//echo "Script: ${script.dump()}"
	//echo "Config file: ${configurationFile.dump()}"
	print "This is execute method"
    }
}
