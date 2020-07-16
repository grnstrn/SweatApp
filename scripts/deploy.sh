#!/usr/bin/env bash
mvn clean package
echo 'Copy files...'
scp -i ~/.ssh/sweat-app.pem \
target/SweatApp-1.0-SNAPSHOT.jar \
    ec2-user@3.121.98.65:/home/ec2-user/
    echo 'Restart server...'
    ssh -i ~/.ssh/sweat-app.pem ec2-user@3.121.98.65 << EOF
    pgrep java | xargs kill -9
    nohup java -jar SweatApp-1.0-SNAPSHOT.jar > log.txt &
EOF
echo 'Bye'