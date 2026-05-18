pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        
        stage('Build & Tag Image') {
            steps {
                echo 'Building Spring Boot Docker Image...'
                sh 'docker build -t registry.azaken.com/employee-api-image:latest .'
            }
        }

        stage('Push to Private Registry') {
            steps {
                echo 'Pushing to registry.azaken.com...'
                sh 'docker push registry.azaken.com/employee-api-image:latest'
            }
        }
        
        stage('Deploy via Docker Compose') {
            steps {
                echo 'Pulling new image and redeploying backend...'
                sh '''
                cd /opt/employee-deployment
                docker-compose pull employee-api
                docker-compose up -d --no-deps employee-api
                '''
            }
        }
    }
}
