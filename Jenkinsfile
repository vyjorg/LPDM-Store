pipeline {
    agent any
    tools {
        maven 'Apache Maven 3.5.2'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/vyjorg/LPDM-Store'
            }
        }
        stage('Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                }
                failure {
                    error 'The tests failed'
                }
            }
        }
        stage('Push to DockerHub') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker stop LPDM-StoreMS || true && docker rm LPDM-StoreMS || true'
                sh 'docker pull vyjorg/lpdm-store:latest'
                configFileProvider([configFile(fileId: '6ff497d9-5e1d-4238-824e-30f24469571a', variable: 'lpdm')]) {
                    echo "${lpdm.store}"
                    sh 'docker run -d --name LPDM-StoreMS -p 28084:28084 --link LPDM-StoreDB --restart always --memory-swappiness=0  -e "JAVA_TOOL_OPTIONS=-Djasypt.encryptor.password=${lpdm.store}" vyjorg/lpdm-store:latest'
                }
            }
        }
    }
}
