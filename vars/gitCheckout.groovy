String call(){
    checkout changelog: false, 
             poll: false, 
             scm: [$class: 'GitSCM', 
                   branches: [[name: '*/master']], 
                   doGenerateSubmoduleConfigurations: false, 
                   extensions: [], 
                   submoduleCfg: [], 
                   userRemoteConfigs: [[url: "${config.scm_url}"]]
                  ]    
}

