pipeline {
    agent any
    environment {
        JAVA_HOME = 'C:/Program Files/openjdk-17.0.2_windows-x64_bin/jdk-17.0.2'
        MAVEN_HOME = 'C:/Program Files/Maven'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
        WEBLOGIC_HOME = 'D:/Weblogic/Oracle/Middleware/Oracle_Home'  // Path to WebLogic installation
        APP_NAME = 'detailing'  // Application name
        WAR_PATH = 'detailing/detailing/target/detailing-0.0.1-SNAPSHOT.war'  // Path to the WAR file
        WLST_SCRIPT = 'deploy.py'  // Python script for deployment
        WL_USERNAME = 'weblogic'  // WebLogic username
        WL_PASSWORD = 'password'  // WebLogic password
        WL_HOST = 'localhost'  // WebLogic host
        WL_PORT = '7001'  // WebLogic port
        WL_TARGET = 'AdminServer'  // WebLogic target
    }
    stages {
        stage('Checkout SCM') {
            steps {
                echo 'Checking out code from SCM...'
                checkout scm
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
        stage('Start Node Manager') {
            steps {
                echo 'Starting Node Manager...'
                bat """
                    cd /d ${WEBLOGIC_HOME}/user_projects/domains/base_domain/bin
                    startNodeManager.cmd
                """
            }
        }
        stage('Deploy to WebLogic') {
            steps {
                echo 'Deploying application to WebLogic...'
                // Generate WLST deployment script dynamically
                script {
                    writeFile file: "${WLST_SCRIPT}", text: """
                        connect('${WL_USERNAME}', '${WL_PASSWORD}', 't3://${WL_HOST}:${WL_PORT}')
                        deploy('${APP_NAME}', '${env.WORKSPACE}/${WAR_PATH}', targets='${WL_TARGET}')
                        exit()
                    """
                }
                // Execute WLST script
                bat """
                    cd /d ${WEBLOGIC_HOME}/common/bin
                    wlst.cmd ${WLST_SCRIPT}
                """
            }
        }
        stage('Verify Deployment') {
            steps {
                echo 'Verifying deployment...'
                // Add commands to verify if the deployment was successful
                bat """
                    curl -I http://${WL_HOST}:${WL_PORT}/${APP_NAME}
                """
            }
        }
    }
    post {
        always {
            echo 'Pipeline execution completed.'
        }
        success {
            echo 'Build and deployment were successful!'
        }
        failure {
            echo 'Build or deployment failed!'
        }
    }
}
