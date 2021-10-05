package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url);
        System.out.println("message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close" + url);
    }

    @PostConstruct
    public void init() {
        //스프링 생성 및 의존관계 끝나고 초기화 callback
        connect();
        call("초기화 연결 메세지");

    }

    @PreDestroy
    public void close() {
        //소멸 전 callback
        disconnect();
    }
}
