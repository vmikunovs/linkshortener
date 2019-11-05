FROM openjdk:8
ADD link-shortener.jar link-shortener.jar
#ADD wait-for-it.sh .
#RUN ["chmod", "+x", "./wait-for-it.sh"]
#EXPOSE 8080
#ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "link-shortener.jar"]
ENTRYPOINT ["java", "-jar", "link-shortener.jar"]