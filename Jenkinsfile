node {
    def mvnHome = tool 'M3'

    stage("Preparation"){
        git 'https://github.com/Rubru94/practTema15.git'
    }

    stage("Create app"){
        sh "docker-compose build"
    }

    stage("Start app"){
        sh "docker-compose up -d"
    }

    stage("Unit Test"){
        sh "sleep 5; mvn test -Dtest=ControllerUnitTest"
    }

    stage("Post"){

        sh "docker-compose logs"
        sh "docker-compose logs > all-logs.txt"
        archiveArtifacts "all-logs.txt"

        sh "docker-compose logs application > application-logs.txt"
        archiveArtifacts "application-logs.txt"

        sh "docker-compose logs mysqldb > mysqldb-logs.txt"
        archiveArtifacts "mysqldb-logs.txt"

        sh "sleep 5; docker-compose down"
        junit '**/target/surefire-reports/*.xml'
    }

    stage("Create jar"){
        sh "'${mvnHome}/bin/mvn' clean package -DskipTests"
    }

    stage("Archive jar"){
        archiveArtifacts "**/target/*.jar"
    }
}