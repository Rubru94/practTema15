package ruben.practTema15;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByAuthor(Author author);
}