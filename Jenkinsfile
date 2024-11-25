pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:/Program Files/Maven'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/Ankitmzn/Jai-Durga-Backend.git'
            }
        }

        stage('Build Project') {
            steps {
                script {
                    // Navigate to the correct directory
                    dir('detailing/detailing') { // Update to the relative path where pom.xml is located
                        bat "\"${MAVEN_HOME}/bin/mvn\" clean install"
                    }
                }
            }
        }

        stage('Deploy to WebLogic') {
            steps {
                script {
                    // Find the WAR file dynamically and copy to WebLogic deploy directory
                    def warFile = findFiles(glob: '**/target/*.war')[0].path
                    bat "copy \"${warFile}\" \"D:/Weblogic/Oracle/Middleware/user_projects/domains/base_domain/autodeploy/\""
                }
            }
        }
    }

    post {
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Build or deployment failed!'
        }
    }
}
