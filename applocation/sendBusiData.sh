
configorgid="$1";

deployDir="$HOME/apache-tomcat-7.0.55_8200/webapps/Aris"
export deployDir

for file in ${deployDir}/WEB-INF/lib/*;
do CP=${CP}:$file;
done

CLASSPATH=".:${deployDir}/WEB-INF/classes:${CP}"
export CLASSPATH

echo ${CLASSPATH}

java -Xms1024m -Xmx2048m -XX:MaxPermSize=2048m -Dfile.encoding=UTF-8 -DETLDataFile=true -DtaskName=data$configorgid -classpath ${CLASSPATH} com.ai.aris.server.webservice.service.BusiDataService $configorgid
