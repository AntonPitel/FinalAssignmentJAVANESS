package sk.ness.academy.service;

import org.springframework.stereotype.Service;
import sk.ness.academy.dao.ArticleHibernateDAO;
import sk.ness.academy.dao.CommentDAO;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentDAO commentDAO;


    @Override
    public Comment findById(Integer commentId) {
        return this.commentDAO.findById(commentId);
    }


    @Override
    public List<Comment> findAllArticlesComments() {
        return this.commentDAO.findAll();
    }

    @Override
    public void createComment(final Comment comment) {
        this.commentDAO.persist(comment);
    }

    @Override
    public void deleteCommentWithId(Integer commentId) {
        this.commentDAO.deleteCommentWithId(commentId);
    }

    @Override
    public List<Comment> findAll() {
        return this.commentDAO.findAll();
    }
}
