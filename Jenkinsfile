pipeline {
    agent { label 'ubuntu20' }

    stages {
        stage('Delete workspace before build starts') {
            steps {
                echo 'Deleting workspace'
                deleteDir()
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'master',
                url: 'https://github.com/shimozukuri/banking.git'

                sh 'pwd'
                sh 'ls -la'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'docker compose up -d'
                sh 'docker ps'
                sh 'docker stop $(docker ps -a -q)'
            }
        }
    }
}