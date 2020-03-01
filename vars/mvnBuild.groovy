String call(mavenGoal){
    //steps{
        //script{
            sh'''
             cd my-app
             pwd
             $mavenGoal
            '''
        //}
    //}
}
