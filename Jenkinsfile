node {
    def mvnHome = tool 'M3'
    stage("Preparation"){
        git 'https://github.com/Rubru94/practTema15.git'
    }
    stage("Create jar"){
        sh "'${mvnHome}/bin/mvn' clean package -DskipTests"
    }
    stage("Start app"){
        sh "cd ./target ; java -jar practTema15-0.1.0-SNAPSHOT.jar > out.log & echo \$! > pid"
    }
    stage("Test"){
        sh "'${mvnHome}/bin/mvn' test"
    }
    stage("Post"){
        archiveArtifacts '**/target/*.jar'
        junit '**/target/surefire-reports/*.xml'
        sh "kill \$(cat target/pid)"
    }
}