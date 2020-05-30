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
    stage("Test"){
        sh "sleep 10; mvn test"
    }
    stage("Post"){

        sh "docker-compose logs"
        sh "docker-compose logs > all-logs.txt"
        archive "all-logs.txt"

        sh "docker-compose logs application > application-logs.txt"
        archive "application-logs.txt"

        sh "docker-compose logs mysqldb > mysqldb-logs.txt"
        archive "mysqldb-logs.txt"

        sh "docker-compose down"
        junit '**/target/surefire-reports/*.xml'
    }
}