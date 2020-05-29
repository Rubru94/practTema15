package ruben.practTema15;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Post {
	
	public interface Elemental {
	}

	public interface CommentAtt {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Elemental.class)
	private long id;
		
	@JsonView(Elemental.class)
	private String title;
	
	@JsonView(Elemental.class)
	private String content;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="post")
	@JsonView(CommentAtt.class)
	private List<Comment> comments = new ArrayList<>();
		
	public Post() {

	}

	public Post(String title, String content) {
		super();
		//List<Comment> comments = new ArrayList<>();
		//comments.add(new Comment(new Author("Me",25),"First Comment"));
		this.title = title;
		this.content = content;
		this.comments = new ArrayList<>(); 
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
	public void removeComment(int position) {
		this.comments.remove(position); 
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Post [titulo=" + title + ", content=" + content + ", comments=" + /*comments.toString() +*/ "]";
	}

}
