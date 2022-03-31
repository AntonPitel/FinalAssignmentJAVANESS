package sk.ness.academy.service;

import sk.ness.academy.domain.Comment;

import java.util.List;

public interface CommentService {

    Comment findById(Integer commentId);

    List<Comment> findAllArticlesComments();

    abstract void createComment(Comment comment);

    void deleteCommentWithId(Integer commentId);

    List<Comment> findAll();
}