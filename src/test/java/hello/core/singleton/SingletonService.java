package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    //생성자를 private로 막아놓음. 외부에서 new keyword로 생성불가.
    private SingletonService(){}

    public void service() {
        System.out.println("Singleton Service");
    }
}