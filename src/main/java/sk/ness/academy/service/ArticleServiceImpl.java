package sk.ness.academy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import sk.ness.academy.dao.ArticleDAO;
import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticleWithOutComments;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

  @Resource
  private ArticleDAO articleDAO;

  @Override
  public Article findByID(final Integer articleId) {
	  return this.articleDAO.findByID(articleId);
  }

  @Override
  public List<ArticleWithOutComments> findAll() {
	  return this.articleDAO.findAll();
  }

  @Override
  public void createArticle(final Article article) {
	  this.articleDAO.persist(article);
  }

  @Override
  public void ingestArticles(final String jsonArticles) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      //mapper.writeValueAsString(jsonArticles);
      List<Article> articles = List.of(mapper.readValue(jsonArticles, Article[].class));
      articles.forEach(a -> articleDAO.persist(a));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteByID(Integer articleId) {
    this.articleDAO.deleteByID(articleId);
  }

  @Override
  public List<ArticleWithOutComments> findArticlesWithTxT(String searchText) {
    return this.articleDAO.findArticlesWithTxT(searchText);
  }

}


