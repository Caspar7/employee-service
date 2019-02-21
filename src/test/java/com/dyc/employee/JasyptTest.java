package com.dyc.employee;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class JasyptTest {
    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void encrypt() {
        System.out.println(stringEncryptor.encrypt("zjp"));
        System.out.println(stringEncryptor.encrypt("123456"));
        System.out.println(stringEncryptor.encrypt("root"));
    }

    @Test
    public void decrypt(){
        System.out.println(stringEncryptor.decrypt("1E01WYZE8d+w7kWB0VOm5Q=="));
        System.out.println(stringEncryptor.decrypt("ENomjjhuKHlpdNh8YwjceQ=="));
    }

}