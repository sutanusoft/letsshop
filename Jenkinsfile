//DECLARATIVE

pipeline{
	agent any
	parameters{
		choice(name: "Profile", choices: ["Regression", "Purchase", "ErrorValidation"], description: "Sample multi-choice parameter")
	}
	stages{
		stage('Build'){
			steps{
				echo "Build"
				echo "Hello Arup $params.Profile"
			}
		}
		stage('Test'){
			steps{
				echo "Test"
			}
		}
		stage('Integration Test'){
			steps{
				echo "Integration Test"
			}
		}
	}
	post{
		success{
			echo 'Successfull'
		}
		failure{
			echo 'failure'
		}
	}
}