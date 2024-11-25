pipeline {
    agent any
    
    environment {
        JAVA_HOME = 'C:/Program Files/openjdk-17.0.2_windows-x64_bin/jdk-17.0.2'  // Adjust with your Java path
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    
    stages {
        // Stage for checking out the code from Git
        stage('Checkout SCM') {
            steps {
                checkout scm  // This checks out the code from the Git repository
            }
        }

        // Stage to check the current directory (for debugging)
        stage('Check Directory') {
            steps {
                script {
                    bat 'echo %CD%'  // This will print the current directory to the console for debugging
                }
            }
        }
        
        // Stage for building the project using Maven
        stage('Build Project') {
            steps {
                script {
                    // If the pom.xml is located at the root of the repository, remove the 'dir' block
                    // If the pom.xml is inside a subdirectory, replace 'path/to/project' with the actual subdirectory name
                    dir('') {  // This means running in the root directory where pom.xml is located
                        bat '"C:/Program Files/Maven/bin/mvn" clean install'  // Adjust Maven command if needed
                    }
                }
            }
        }

        // Stage for deploying to WebLogic (this is skipped if earlier stages fail)
        stage('Deploy to WebLogic') {
            steps {
                echo 'Deploying to WebLogic...'
                // Add WebLogic deployment steps here
            }
        }
    }

    post {
        always {
            echo 'This will always run after all stages'
        }
        success {
            echo 'Deployment succeeded'
        }
        failure {
            echo 'Deployment failed'
        }
    }
}
