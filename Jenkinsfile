pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/qagoggins/Java-Selenium-Framework.git' // Replace with your actual repository URL
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}