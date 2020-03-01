String call(scm_url){
    checkout changelog: false, 
             poll: false, 
             scm: [$class: 'GitSCM', 
                   branches: [[name: '*/master']], 
                   doGenerateSubmoduleConfigurations: false, 
                   extensions: [], 
                   submoduleCfg: [], 
                   userRemoteConfigs: [[url: "${scm_url}"]]
                  ]    
}

