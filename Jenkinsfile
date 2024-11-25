pipeline {
agent any

environment {
JAVA_HOME = 'C:/Program Files/openjdk-17.0.2_windows-x64_bin/jdk-17.0.2' // Adjust according to your Java path
MAVEN_HOME = 'C:/Program Files/Maven' // Path to Maven
PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
WL_HOME = 'D:/Weblogic/Oracle/Middleware/Oracle_Home' // Path to WebLogic
DOMAIN_HOME = 'D:/Weblogic/Oracle/Middleware/user_projects/domains/base_domain' // Path to WebLogic Domain
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

stage('Deploy to WebLogic') {
steps {
script {
// Deploy the artifact to WebLogic
bat """${WL_HOME}/user_projects/domains/base_domain/bin/startWebLogic.cmd"""
// Replace this with your actual WebLogic deployment script, if applicable
bat """${WL_HOME}/user_projects/domains/base_domain/bin/stopWebLogic.cmd"""
// Optionally use WebLogic Deploy Tool or WLST (WebLogic Scripting Tool)
// For simplicity, we're starting/stopping WebLogic as part of the deployment process here.
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
