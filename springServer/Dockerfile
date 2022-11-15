FROM maven:3.8.3-openjdk-17
#FROM mysql
#
#ENV MYSQL_ROOT_PASSWORD 1q2w3e4r
#ADD jsp_backup.sql /docker-entrypoint-initdb.d
#
#EXPOSE 3306

#RUN mysql -h localhost -u root -p1q2w3e4r -e "create database shopping_cart"

COPY . .
RUN mvn clean install

CMD mvn spring-boot:run