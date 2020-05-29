package ruben.practTema15;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

import ruben.practTema15.Post.Elemental;

@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Elemental.class)
	private long id;

	@JsonView(Elemental.class)
	private String name;
	
	private int age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Author(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public Author() {}

	@Override
	public String toString() {
		return "Author [name=" + name + ", age=" + age + "]";
	}
}
