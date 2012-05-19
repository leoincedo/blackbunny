## Blackbunny는? 게임/APP 서버 개발을 위한 초경량 프레임워크 입니다.

* 안정적인 서버를 최대한 빠른시간안에 구현이 가능 하도록 기본 프레임워크를 제공 합니다.
* 많은 경험을 보유한 개발자가 아니더라도 간단한 코드 만으로 서버를 개발 하는 것이 가능 합니다.

## 특징
* DB Mapper 제공
* 의존성 주입 ( Dependency Injection )
* 자바스크립트 지원
* 대용량 서비스

## TODO
* 분산 처리 지원
* 모니터링 지원

## 필요사항
+ Java 1.5이상
+ Apache Maven


## 예제
```java
package org.blackbunny.server;

class SampleServer implements NetHandler
{
    public static final Logger logger = LoggerFactory.getLogger( SampleServer.class );

    NetMessageDistributor distributor;
    NetController netController;

    public void onConnected(Session session) {
        logger.info( "onConnected : {}", session );
    }

    public void onClosed(Session session) {
        logger.info( "onClosed : {}", session );
    }

    public void messageReceived(NetMessage message) {
        logger.info( "onMessage : {}", message.getSession() );
        distributor.execute( message );
    }

    public void exceptionCaught(Session session, Throwable cause) {
        logger.info( "exceptionCaught : {}", session, cause );
    }

    public void start() throws Exception
    {
        logger.info( "starting.." );

        Injector.createComponent(JavaScript.class);
        Injector.createComponent( SampleDAO.class ).initDB();

        distributor = new NetMessageDistributor();
        distributor.scanPackages( "org.blackbunny.server.handlers" );

        netController = new SimpleNetController( this );
        netController.bind( 27932 );

        Thread.sleep( Integer.MAX_VALUE );

    }

    public static void main( String[] args ) throws Exception {

        SampleServer server = new SampleServer();
        server.start();

    }

}
```