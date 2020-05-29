node {
    stage("Build & test"){
        def mvnHome = tool 'M3'
        git 'https://github.com/Rubru94/practTema15.git'
        sh "cd . ; '${mvnHome}/bin/mvn' clean package"
    }
    stage("Post"){
        archiveArtifacts '**/practTema15/target/*.jar'
        junit '**/target/surefire-reports/*.xml'
    }
}