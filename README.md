## Blackbunny는? 

* 게임 및 어플리케이션 서버 개발을 위한 경량 프레임워크 입니다.
* Game and Application Server Develoment Lightweight Framework

## 특징
* 고성능 Network NIO ( Netty 사용 )
* 간편한 DB Mapper 제공 ( Mybatis 사용 )
* 의존성 주입 ( Dependency Injection )
* 동적 자바 스크립트 코드 연동

## 필요사항
+ Java 1.5이상, Apache Maven 3.x

## 빌드

    mvn package

# 서버 실행

    java -cp blackbunny-server-0.1-SNAPSHOT-jar-with-dependencies.jar org.blackbunny.server.SampleServer

# 클라이언트 실행

    java -cp blackbunny-client-0.1-SNAPSHOT-jar-with-dependencies.jar org.blackbunny.client.SampleClient localhost 27932 bot1


## 샘플 코드
* [SampleServer.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/SampleServer.java)
* [SampleDBMapper.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/data/SampleDBMapper.java)
* [SampleDAO.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/data/SampleDAO.java)
* [GetUser.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/handlers/GetUser.java)
* [GetUserList.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/handlers/GetUserList.java)
* [sample.js](blackbunny/blob/master/blackbunny-server/src/main/resources/sample.js)
* [SampleClientHandler.java](blackbunny/blob/master/blackbunny-client/src/main/java/org/blackbunny/client/SampleClientHandler.java)



