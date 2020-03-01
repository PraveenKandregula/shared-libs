String call(mavenStep){
    sh'''
      cd my-app
      pwd
      $mavenStep
    '''
}
