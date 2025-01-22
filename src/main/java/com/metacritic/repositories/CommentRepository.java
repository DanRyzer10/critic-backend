package com.metacritic.repositories;

import com.metacritic.domain.Comment;
import com.metacritic.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    Comment findCommentById(Long id);
    Comment findCommentByContent(String content);
    Comment findCommentByUser(User user);
}
