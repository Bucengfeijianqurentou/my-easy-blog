package com.gb.myeasyblog;

import io.jsonwebtoken.io.Decoders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyEasyBlogApplicationTests {

    @Test
    void contextLoads() {
        byte[] keyBytes = Decoders.BASE64.decode("你好世界");
    }

}
