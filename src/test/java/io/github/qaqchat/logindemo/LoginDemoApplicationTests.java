package io.github.qaqchat.logindemo;

import io.github.qaqchat.logindemo.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginDemoApplicationTests {
	@Autowired
	JwtUtil jwtUtil;

	@Test
	void contextLoads() {
	}

	@Test
	void parseTokenTest() {
		String token = jwtUtil.generateToken("test");
		System.out.println(token);
		Jwt<?, ?> result = jwtUtil.parseJwt(token);
		System.out.println(result);
		// MalformedJwtException || ExpiredJwtException
//		String errorToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzMxOTQ4MjE2LCJleHAiOjE3MzE5NDgyMjZ9._xkKvf0YnvR7vQyX_nc19AIlsNmtxuLCXjtdP4bXlFg";
//		Jwt<?, ?> error = jwtUtil.parseJwt(errorToken);
//		System.out.println(error);
	}


}
