#!/bin/sh 
$1 = $(mvn -q \
        -Dexec.executable="echo" \
        -Dexec.args='${project.version}' \
        --non-recursive \
        org.codehaus.mojo:exec-maven-plugin:1.3.1:exec)