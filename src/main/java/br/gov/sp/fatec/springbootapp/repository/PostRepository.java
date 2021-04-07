package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.springbootapp.entity.Post;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    // public List<Post> findByNomePostIgnoringCase(String titulo);
}