package sk.ness.academy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.ArticleWithOutComments;
import sk.ness.academy.dto.Author;
import sk.ness.academy.dto.AuthorStats;
import sk.ness.academy.exception.ApiRequestException;
import sk.ness.academy.service.ArticleService;
import sk.ness.academy.service.AuthorService;
import sk.ness.academy.service.CommentService;

@RestController
public class BlogController {

  @Resource
  private ArticleService articleService;

  @Resource
  private AuthorService authorService;

  @Resource
  private CommentService commentService;

  // ~~ Article
  @RequestMapping(value = "articles", method = RequestMethod.GET)
  public List<ArticleWithOutComments> getAllArticles() {
    return this.articleService.findAll();
  }

  @RequestMapping(value = "articles/{articleId}", method = RequestMethod.GET)
  public Article getArticle(@PathVariable final Integer articleId) {
    try {
      return this.articleService.findByID(articleId);
    } catch (Exception e) {
      throw new ApiRequestException("Could not find article");
    }
  }

  @DeleteMapping(value = "articles/{articleId}")
  public void deleteArticle(@PathVariable final Integer articleId) {
    try {
      this.articleService.deleteByID(articleId);
    } catch (Exception e) {
      throw new ApiRequestException("Could not delete article");
    }
  }

  @RequestMapping(value = "articles/search/{searchText}", method = RequestMethod.GET)
  public List<ArticleWithOutComments> searchArticle(@PathVariable final String searchText) {
    return this.articleService.findArticlesWithTxT(searchText);
  }

  @RequestMapping(value = "articles", method = RequestMethod.PUT)
  public void addArticle(@RequestBody final Article article) {
    this.articleService.createArticle(article);
  }

  // ~~ Author
  @RequestMapping(value = "authors", method = RequestMethod.GET)
  public List<Author> getAllAuthors() {
    return this.authorService.findAll();
  }

  @RequestMapping(value = "authors/stats", method = RequestMethod.GET)
  public List<AuthorStats> authorStats() {
    return this.authorService.authorStats();
  }

  // ~~ Comment
  @RequestMapping(value = "comments", method = RequestMethod.GET)
  public List<Comment> getComment() {
      return this.commentService.findAllArticlesComments();
  }

  @RequestMapping(value = "comments", method = RequestMethod.PUT)
  public void addComment(@RequestBody final Comment comment) {
    this.commentService.createComment(comment);
  }

  @RequestMapping(value = "comments/{commentID}", method = RequestMethod.GET)
  public Comment getComment(@PathVariable final Integer commentID) {
    try {
      return this.commentService.findById(commentID);
    } catch(Exception e) {
      throw new ApiRequestException("Could not find comment");
    }
  }

  @DeleteMapping(value = "comments/{commentID}")
  public void deleteComment(@PathVariable final Integer commentID) {
    try {
      this.commentService.deleteCommentWithId(commentID);
    } catch (Exception e) {
      throw new ApiRequestException("Could not delete comment");
    }
  }
}
