package ruben.practTema15;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ruben.practTema15.Post;

@Service
public class PostsService{
	
	private Map<Long, Post> posts = new ConcurrentHashMap<>();
	private AtomicLong lastId = new AtomicLong();
	
	public PostsService() {
		super();
		this.posts = new ConcurrentHashMap<>();
		this.lastId = new AtomicLong();
	}
	
	Map<Long, Post> getPostMap(){
		return posts;
	}
	
	List<Post> getPostList(){
		return new ArrayList<Post>(posts.values());
	}
	
	long createNewId(){
		return lastId.incrementAndGet();
	}
	
	Post getPost(int position) {
		for (Long key : posts.keySet()) {
			if(key==position)
				return posts.get(key);
		}  
    return null;
	}
}