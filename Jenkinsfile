pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:/Program Files/openjdk-17.0.2_windows-x64_bin/jdk-17.0.2'
        MAVEN_HOME = 'C:/Program Files/Maven'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
        WL_HOME = 'D:/Weblogic/Oracle/Middleware/Oracle_Home'
        DOMAIN_HOME = 'D:/Weblogic/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain'
        WLST_PATH = 'D:/Weblogic/Oracle/Middleware/Oracle_Home/common/bin/wlst.cmd'
        ADMIN_SERVER_HOST = 'localhost'
        ADMIN_SERVER_PORT = '7001'
        MANAGED_SERVER_PORT = '7003'
        USERNAME = 'weblogic'
        PASSWORD = 'Highmark@123'
        APP_PATH = 'path/to/your/war/or/app'
    }

    stages {
        stage('Checkout SCM') {
            steps {
                echo 'Checking out SCM...'
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
                script {
                    echo 'Building project using Maven...'
                    dir('detailing/detailing') {
                        bat '"C:/Program Files/Maven/bin/mvn" clean install'
                    }
                }
            }
        }

        stage('Start Node Manager') {
            steps {
                script {
                    echo 'Starting Node Manager...'
                    timeout(time: 5, unit: 'MINUTES') {
                        bat """
                        cd /d D:/Weblogic/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/bin
                        start /b startNodeManager.cmd > nodemanager.log 2>&1
                        """
                    }
                }
            }
        }

        stage('Deploy to WebLogic') {
            steps {
                script {
                    echo 'Deploying to WebLogic...'
                    bat """
                    ${WLST_PATH} <<EOF
connect('${USERNAME}', '${PASSWORD}', 't3://${ADMIN_SERVER_HOST}:${ADMIN_SERVER_PORT}')
deploy('MyNodeApp', '${APP_PATH}', targets='new_ManagedServer_1')
start('new_ManagedServer_1')
disconnect()
exit()
EOF
                    """
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                script {
                    echo 'Verifying deployment...'
                    bat """
                    curl http://localhost:${MANAGED_SERVER_PORT}/your_app_endpoint
                    """
                }
            }
        }

        stage('Stop Node Manager') {
            steps {
                script {
                    echo 'Stopping Node Manager...'
                    timeout(time: 5, unit: 'MINUTES') {
                        bat """
                        cd /d D:/Weblogic/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/bin
                        start /b stopNodeManager.cmd
                        """
                    }
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
