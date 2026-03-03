pipeline {

    agent any

    parameters {
        choice(name: 'EXECUTION', choices: ['local', 'browserstack'], description: 'Select execution mode')
        choice(name: 'PLATFORM', choices: ['android', 'ios'], description: 'Select platform')
    }

    triggers {
        cron('45 18 * * *')   // 6:45 PM daily (server time)
        githubPush()
    }






    environment {
        BROWSERSTACK_USERNAME = credentials('bs-username')
        BROWSERSTACK_ACCESS_KEY = credentials('bs-accesskey')
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Verify Environment') {
            when {
                expression { params.EXECUTION == 'local' }
            }
            steps {
                sh 'adb devices'
                sh 'echo "Make sure Appium is running on 4723"'
            }
        }




        stage('Check ADB') {
            steps {
                sh 'echo "Checking adb path..."'
                sh 'which adb'
                sh 'adb devices'
            }
        }









        stage('Run Tests') {
            steps {
                sh "mvn clean test -Dexecution=${params.EXECUTION} -Dplatform=${params.PLATFORM}"
            }
        }
    }

    post {

        always {

            junit 'target/cucumber.xml'

            publishHTML([
                reportDir: 'target',
                reportFiles: 'cucumber-report.html',
                reportName: 'Cucumber HTML Report',
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true
            ])

            archiveArtifacts artifacts: 'target/**/*.*', fingerprint: true
        }
    }
}