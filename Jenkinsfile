pipeline {
    agent any

    tools {
        maven "Maven"
    }

    environment {
        DOCKER_HUB_REPO = 'asmaaba/musicmanager'
    }

    stages {
        stage('Clone Repo') {
            steps {
                git branch: 'main', url: 'https://github.com/asmaabarj/MusicManager
            }
        }

        stage('Build Artifact') {
            steps {
                dir('musique') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Prepare Docker Build') {
            steps {
                script {
                    // Créer le Dockerfile s'il n'existe pas
                    writeFile file: 'Dockerfile', text: '''FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY musique/target/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]'''
                }
            }
        }

        stage('Docker Build') {
            steps {
                bat "docker build -t ${DOCKER_HUB_REPO}:latest ."
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-token', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        bat 'echo %DOCKER_PASSWORD%| docker login -u %DOCKER_USERNAME% --password-stdin'
                        bat "docker push ${DOCKER_HUB_REPO}:latest"
                    }
                }
            }
        }
    }

    post {
        always {
            bat 'docker logout'
        }
    }
}