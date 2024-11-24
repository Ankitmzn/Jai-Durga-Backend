
pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:/Program Files/Maven'
        JAVA_HOME = 'C:/Program Files/Java/jdk-17'
        WEBLOGIC_HOME = 'D:/Weblogic/Oracle/Middleware/Oracle_Home'
        DEPLOY_PATH = 'D:/Weblogic/Oracle/Middleware/user_projects/domains/base_domain/servers/AdminServer/tmp/_WL_user'
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
                    bat "\"${MAVEN_HOME}/bin/mvn\" clean install"
                }
            }
        }

        stage('Deploy to WebLogic') {
            steps {
                script {
                    // Copy the WAR file to the WebLogic deploy folder
                    bat "copy target\\*.war ${WEBLOGIC_HOME}/user_projects/domains/base_domain/autodeploy/"
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment succeeded.'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}
