pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:/Program Files/openjdk-17.0.2_windows-x64_bin/jdk-17.0.2'
        MAVEN_HOME = 'C:/Program Files/Maven'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
        WL_HOME = 'D:/Weblogic/Oracle/Middleware/Oracle_Home'
        DOMAIN_HOME = 'D:/Weblogic/Oracle/Middleware/user_projects/domains/base_domain'
        NODE_MANAGER_HOME = 'D:/Weblogic/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/nodemanager'
        WLST_PATH = 'D:/Weblogic/Oracle/Middleware/Oracle_Home/common/bin/wlst.cmd'
        ADMIN_SERVER_HOST = 'localhost'
        ADMIN_SERVER_PORT = '7001'
        MANAGED_SERVER_PORT = '7003'
        USERNAME = 'weblogic'
        PASSWORD = 'Highmark@123'
        APP_PATH = 'path/to/your/nodejs/app'
    }

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
                script {
                    dir('detailing/detailing') {
                        bat '"C:/Program Files/Maven/bin/mvn" clean install'
                    }
                }
            }
        }

        stage('Start Node Manager') {
            steps {
                script {
                    timeout(time: 2, unit: 'MINUTES') {
                        bat """start /b ${NODE_MANAGER_HOME}/nodemanager.cmd"""
                    }
                    echo "Node Manager started."
                    bat """netstat -ano | findstr :5556"""
                }
            }
        }

        stage('Deploy to WebLogic') {
            steps {
                script {
                    bat """${WLST_PATH} -url t3://${ADMIN_SERVER_HOST}:${ADMIN_SERVER_PORT} -username ${USERNAME} -password ${PASSWORD} <<EOF
connect('${USERNAME}', '${PASSWORD}', 't3://${ADMIN_SERVER_HOST}:${ADMIN_SERVER_PORT}')
deploy('MyNodeApp', '${APP_PATH}', targets='new_ManagedServer_1')
start('new_ManagedServer_1')
EOF"""
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                script {
                    bat """curl http://localhost:${MANAGED_SERVER_PORT}/your_app_endpoint"""
                    echo "Application is running on Managed Server"
                }
            }
        }

        stage('Stop Node Manager') {
            steps {
                script {
                    bat """${NODE_MANAGER_HOME}/stopNodeManager.cmd"""
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
