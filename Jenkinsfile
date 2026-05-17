pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        
        stage('Build Docker Image') {
            steps {
                echo 'Building Spring Boot Docker Image...'
                sh 'docker build -t employee-api-image:latest .'
            }
        }
        
        stage('Deploy Backend Container') {
            steps {
                echo 'Stopping and removing old container...'
                sh 'docker rm -f employee-api || true'
                
                echo 'Starting new container...'
                sh '''
                docker run -d \
                  --name employee-api \
                  --network employee-net \
                  -p 9090:8080 \
                  -e SPRING_DATASOURCE_URL=jdbc:mysql://employee-mysql:3306/employee_new \
                  -e SPRING_DATASOURCE_USERNAME=root \
                  -e SPRING_DATASOURCE_PASSWORD=Ccpbpms@2021 \
                  -e SPRING_JPA_HIBERNATE_DDL_AUTO=update \
                  --restart unless-stopped \
                  employee-api-image:latest
                '''
            }
        }
    }
}
