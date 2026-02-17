updated
##Create application.yml

spring:
  data:
    mongodb:
      uri: <link>
      database: <dbname>
      auto-index-creation: true
server:
  port: <port-number>
  servlet:
    context-path: /journal

#spring.data.mongodb.username=<set-username>
#spring.data.mongodb.password=<set-password>
