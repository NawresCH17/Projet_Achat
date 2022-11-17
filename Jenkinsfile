pipeline {
    agent any

    tools {
        
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2_HOME"
    }
    environment {
        dockerImage = ''
	registryCredential= 'dockerhub_id'
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.1.19:8081"
        NEXUS_REPOSITORY = "maven-nexus-repo"
        NEXUS_CREDENTIAL_ID = "nexus_user_credentials"
    }
    stages {
        stage('GIT'){
            steps{
                echo 'Getting Project from GIT';
                git branch:"master",
                url:'https://github.com/NawresCH17/Projet_Achat.git'
            }
        }
        stage('CLEAN'){
            steps{
                sh 'mvn clean '
            }
        }
        stage ('COMPILE') {
            steps {
               sh 'mvn compile'
            }
        }
        stage ('Test') {
            steps {
               sh 'mvn test'
            }
        }
        stage('SONARQUBE'){
            steps{
               sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }
        stage('Package'){
            steps{
               sh 'mvn package'
            }
        }
        /*stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }    
        }*/
         stage('Build image') {
          steps {
            script {dockerImage = docker.build("nawreschouari/achat_devops:latest")}
          }
        }
	stage('Push image'){
 	    steps {
 	           docker.withRegistry( '', registryCredential ) { 
			   dockerImage .push
 			
        	}
             }
       }
      
        stage('Building image docker-compose') {
          steps {
           sh 'docker-compose up -d'
          }
        }	
    }
}
