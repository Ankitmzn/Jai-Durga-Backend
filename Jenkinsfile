pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:/Program Files/Maven'
        JAVA_HOME = 'C:/Program Files/openjdk-17.0.2_windows-x64_bin/jdk-17.0.2'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
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
                    dir('path/to/project') { // Ensure this is the directory containing the pom.xml
                        bat "\"${MAVEN_HOME}/bin/mvn\" clean install"
                    }
                }
            }
        }

        stage('Deploy to WebLogic') {
            steps {
                script {
                    def warFile = findFiles(glob: '**/target/*.war')[0].path
                    bat "copy \"${warFile}\" \"${WEBLOGIC_HOME}/user_projects/domains/base_domain/autodeploy/\""
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
