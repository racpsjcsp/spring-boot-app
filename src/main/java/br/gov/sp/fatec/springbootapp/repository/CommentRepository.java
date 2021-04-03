package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.springbootapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}