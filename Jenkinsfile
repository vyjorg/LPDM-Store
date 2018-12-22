def storekey
pipeline {
    agent any
    tools {
        maven 'Apache Maven 3.5.2'
    }
    stages {
        stage('Load Key') {
            steps {
                script {
                    configFileProvider([configFile(fileId: '2bd4e734-a03f-4fce-9015-aca988614b4e', variable: 'lpdm')]) {
                        def props = readJSON text: '${lpdm}'
                        storekey = '${props.store}'
                        echo '${storekey}'
                    }
                }
            }
        }
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
                sh 'docker run -d --name LPDM-StoreMS -p 28084:28084 --link LPDM-StoreDB --restart always --memory-swappiness=0  -e "JAVA_TOOL_OPTIONS=-Djasypt.encryptor.password=${storekey}" vyjorg/lpdm-store:latest'
            }
        }
    }
}
