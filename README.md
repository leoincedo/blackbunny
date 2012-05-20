## Blackbunny는? 

* 게임 및 어플리케이션 서버 개발을 위한 경량 프레임워크 입니다.

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

        //!  Injector 통한 객체 생성, 생성단계에서 의존성 주입.
        Injector.createComponent( JavaScript.class );
        Injector.createComponent( SampleDAO.class ).initDB();

        distributor = new NetMessageDistributor();
        //! 해당 패키지의 모든 핸들러를 자동으록 찾아서 등록 및 의존성 주입.
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
