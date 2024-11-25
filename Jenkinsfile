pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:/Program Files/openjdk-17.0.2_windows-x64_bin/jdk-17.0.2' // Adjust according to your Java path
        MAVEN_HOME = 'C:/Program Files/Maven' // Path to Maven
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
        WL_HOME = 'D:/Weblogic/Oracle/Middleware/Oracle_Home' // Path to WebLogic
        DOMAIN_HOME = 'D:/Weblogic/Oracle/Middleware/user_projects/domains/base_domain' // Path to WebLogic Domain
        NODE_MANAGER_HOME = 'D:/Weblogic/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/nodemanager' // Node Manager Path
        WLST_PATH = 'D:/Weblogic/Oracle/Middleware/Oracle_Home/common/bin/wlst.cmd'  // Path to WLST script
        ADMIN_SERVER_HOST = 'localhost' // Admin Server Host
        ADMIN_SERVER_PORT = '7001' // Admin Server Port
        MANAGED_SERVER_PORT = '7003' // Managed Server Port (Node server port)
        USERNAME = 'weblogic' // WebLogic Admin username
        PASSWORD = 'Highmark@123' // WebLogic Admin password
        APP_PATH = 'path/to/your/nodejs/app' // Path to your Node.js application artifact (e.g., WAR/JAR/ear)
    }

    stages {
        stage('Checkout SCM') {
            steps {
                // Checkout code from GitHub
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
                script {
                    // Navigate to the directory containing the pom.xml (if needed)
                    dir('detailing/detailing') { // Replace with your project directory if required
                        // Build the project using Maven
                        bat '"C:/Program Files/Maven/bin/mvn" clean install' // Run Maven build command
                    }
                }
            }
        }

        stage('Start Node Manager') {
            steps {
                script {
                    // Start Node Manager if it is not running
                    bat """start ${NODE_MANAGER_HOME}/nodemanager.cmd"""
                    echo "Started Node Manager"
                }
            }
        }

        stage('Deploy to WebLogic') {
            steps {
                script {
                    // Use WLST to deploy the artifact to WebLogic managed server
                    bat """${WLST_PATH} -url t3://${ADMIN_SERVER_HOST}:${ADMIN_SERVER_PORT} -username ${USERNAME} -password ${PASSWORD} <<EOF
connect('${USERNAME}', '${PASSWORD}', 't3://${ADMIN_SERVER_HOST}:${ADMIN_SERVER_PORT}')
deploy('MyNodeApp', '${APP_PATH}', targets='new_ManagedServer_1')
start('new_ManagedServer_1')
EOF"""
                    echo "Deployed to WebLogic Server"
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                script {
                    // Verify if the application is running by accessing the server port (7003)
                    bat """curl http://localhost:7003/your_app_endpoint"""
                    echo "Application is running on Managed Server"
                }
            }
        }

        stage('Stop Node Manager') {
            steps {
                script {
                    // Stop Node Manager gracefully (if required)
                    bat """${NODE_MANAGER_HOME}/stopNodeManager.cmd"""
                    echo "Stopped Node Manager"
                }
            }
        }
    }

    post {
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build or deployment failed!'
        }
    }
}
