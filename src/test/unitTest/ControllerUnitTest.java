package ruben.practTema15.unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ruben.practTema15.Post;

@SpringBootTest
public class ControllerUnitTest {

	@Test
	public void createPost() throws Exception {

		Post post = new Post("Post Test", "Content");
		assertEquals(post.getTitle(),"Post Test");
	}
	
}
