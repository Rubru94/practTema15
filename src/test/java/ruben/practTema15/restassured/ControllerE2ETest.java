package ruben.practTema15.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import ruben.practTema15.Post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerE2ETest {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@LocalServerPort
    int port;
	
	@BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

	@Test
	public void createPost() throws Exception {

		AtomicLong lastPostId = new AtomicLong();
		Post post = new Post("Post Test", "Content Test");
		post.setId(lastPostId.incrementAndGet());
		
		System.out.print(objectMapper.writeValueAsString(post) + "_" + post.getContent() + "_");
		
		given().
			contentType("application/json").
			body(objectMapper.writeValueAsString(post)).
		when().
			post("/post").
		then().
			statusCode(201).
			body("title", equalTo("Post Test")).
			body("content", equalTo("Content Test"));
	}
	
}
