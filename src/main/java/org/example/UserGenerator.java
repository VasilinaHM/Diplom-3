package org.example;
import static org.example.Utils.randomString;
public class UserGenerator {
    public static org.example.User randomUser() {
        return new org.example.User(randomString(6) + "@yandex.ru", randomString(8), randomString(10));
    }
}
