package com.metacritic.services;

import com.metacritic.domain.Comment;
import com.metacritic.domain.User;
import com.metacritic.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService {
    private final CommentRepository  commentRepository;


    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return (List<Comment>) commentRepository.findAll();
    }

    public Comment findById(Long id) {
        return commentRepository.findCommentById(id);
    }
    public Comment findByContent(String content) {
        return commentRepository.findCommentByContent(content);
    }
    public Comment findByUser(User user) {
        return commentRepository.findCommentByUser(user);
    }
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

}
