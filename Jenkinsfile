pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:/Program Files/openjdk-17.0.2_windows-x64_bin/jdk-17.0.2'
        MAVEN_HOME = 'C:/Program Files/Maven'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
        GIT_REPO = 'https://github.com/Ankitmzn/Jai-Durga-Backend.git'
        GIT_BRANCH = 'main'
         WAR_PATH = 'detailing/detailing/target/detailing-0.0.1-SNAPSHOT.war'  // Path to the WAR file
        DEPLOY_SCRIPT = 'D:\\WeblogicScripts\\deploy.ps1'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: "${GIT_BRANCH}", url: "${GIT_REPO}"
            }
        }

       stage('Build Project') {
            steps {
                dir('detailing/detailing') {
                    echo 'Building the project using Maven...'
                    bat """
                        "${MAVEN_HOME}/bin/mvn" clean install
                    """
                }
            }
        }

        stage('Copy WAR') {
            steps {
                script {
                    def warPath = "${env.WORKSPACE}\\${WAR_FILE}".replaceAll('/', '\\\\')
                    bat "copy ${warPath} D:\\WeblogicScripts\\Upload\\"
                }
            }
        }

        stage('Deploy to WebLogic') {
            steps {
                bat "powershell -File ${DEPLOY_SCRIPT}"
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
