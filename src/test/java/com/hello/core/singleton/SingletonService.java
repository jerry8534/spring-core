package com.hello.core.singleton;

public class SingletonService {

    // static 영역에 객체 instance를 미리 하나 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    // public으로 열려있는 static 메서드
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private으로 막아두어서 외부에서 new 키워드로 객체 생성을 못하게 한다.
    private SingletonService() {
        System.out.println("싱글톤 객체 생성");
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
