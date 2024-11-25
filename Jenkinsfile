pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:/Program Files/openjdk-17.0.2_windows-x64_bin/jdk-17.0.2'
        MAVEN_HOME = 'C:/Program Files/Maven'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
        GIT_REPO = 'https://github.com/Ankitmzn/Jai-Durga-Backend.git'
        GIT_BRANCH = 'main'
        WAR_FILE = 'detailing/detailing/target/detailing-0.0.1-SNAPSHOT.war' // WAR file path relative to workspace
        UPLOAD_DIR = 'D:\\WeblogicScripts\\Upload' // Directory for uploading WAR file
        DEPLOY_SCRIPT = 'D:\\WeblogicScripts\\deploy.ps1' // PowerShell script for WebLogic deployment
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out the source code...'
                git branch: "${GIT_BRANCH}", url: "${GIT_REPO}"
            }
        }

        stage('Build Project') {
            steps {
                dir('detailing/detailing') {
                    echo 'Building the project using Maven...'
                    bat """
                        "${MAVEN_HOME}\\bin\\mvn" clean install
                    """
                }
            }
        }

        stage('Copy WAR') {
            steps {
                script {
                    echo 'Copying WAR file to the WebLogic upload directory...'
                    def warPath = "${env.WORKSPACE}\\${WAR_FILE}".replaceAll('/', '\\\\')
                    def targetPath = "${UPLOAD_DIR}\\${WAR_FILE.split('/')[-1]}".replaceAll('/', '\\\\')
                    bat "copy \"${warPath}\" \"${targetPath}\""
                }
            }
        }

        stage('Deploy to WebLogic') {
            steps {
                echo 'Deploying the WAR file to WebLogic server...'
                script {
                    try {
                        bat """
                            powershell -ExecutionPolicy RemoteSigned -File "D:/WeblogicScripts/deploy.ps1"
                        """
                    } catch (Exception e) {
                        echo "Error during deployment: ${e.getMessage()}"
                        currentBuild.result = 'FAILURE' // Mark the build as failed if deployment fails
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
        success {
            echo 'Build and deployment succeeded.'
        }
        failure {
            echo 'Build or deployment failed.'
        }
        cleanup {
            echo 'Cleaning up resources.'
        }
    }
}
