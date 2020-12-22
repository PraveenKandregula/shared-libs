package com.example

class Pipeline {
    def script
    def configurationFile

    Pipeline(script, configurationFile) {
        this.script = script
        this.configurationFile = configurationFile
    }

    def execute() {
	//System.out.println $script
	//System.out.println $configurationFile
	echo "Script: ${script.toString()}"
	echo "Config file: ${configurationFile.toString()}"
    }
}
