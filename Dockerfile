FROM tomcat:9.0.40

COPY ./target/*.war $CATALINA_HOME/webapps

ENV postgresUsername=postgres
ENV postgresPassword=password

EXPOSE 8080