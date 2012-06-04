## Blackbunny는? 

* 게임 및 어플리케이션 서버 개발을 위한 경량 프레임워크 입니다.
* Lightweight Server Framework for Game and Application

## 특징
* 고성능 Network NIO ( Netty 사용 )
* DB Mapper 제공 ( Mybatis 사용 )
* Dependency Injection 제공
* 자바 스크립트 코드 연동

## 필요사항
* Java 1.5이상, Apache Maven 3.x

## 메뉴얼

* [빌드 및 실행 방법](blackbunny/wiki/빌드-및-실행-방법)

## 샘플 코드
* [SampleServer.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/SampleServer.java)
* [SampleDBMapper.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/data/SampleDBMapper.java)
* [SampleDAO.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/data/SampleDAO.java)
* [GetUser.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/handlers/GetUser.java)
* [GetUserList.java](blackbunny/blob/master/blackbunny-server/src/main/java/org/blackbunny/server/handlers/GetUserList.java)
* [sample.js](blackbunny/blob/master/blackbunny-server/src/main/resources/sample.js)
* [SampleClientHandler.java](blackbunny/blob/master/blackbunny-client/src/main/java/org/blackbunny/client/SampleClientHandler.java)


## 패킷 데이터 구조

    +-------------+------------+----------------------+
    | 2byte       | 2byte      | variable             |
    +-------------+------------+----------------------+
    | PACKET_SIZE | PACKET_ID  | DATA_SECTION         |
    +-------------+------------+----------------------+

## 패킷의 데이터 단위

    BYTE    1byte
    INTEGER 4byte
    DOUBLE  8byte
    LONG    8byte
    SHORT   2byte
    FLOAT   4byte
    STRING  2byte + length (UTF-8)









