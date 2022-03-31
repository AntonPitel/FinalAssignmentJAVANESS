package sk.ness.academy.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticleWithOutComments;
import sk.ness.academy.dto.Author;

@Repository
public class ArticleHibernateDAO implements ArticleDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public Article findByID(final Integer articleId) {
    Article article = this.sessionFactory.getCurrentSession().get(Article.class, articleId);
    Hibernate.initialize(article.getComments());
    return article;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ArticleWithOutComments> findAll() {
    return this.sessionFactory.getCurrentSession().createSQLQuery("select * from articles ")
            .addScalar("id", IntegerType.INSTANCE)
            .addScalar("title", StringType.INSTANCE)
            .addScalar("text", StringType.INSTANCE)
            .addScalar("author", StringType.INSTANCE)
            .setResultTransformer(new AliasToBeanResultTransformer(ArticleWithOutComments.class)).list();
  }

  @Override
  public void persist(final Article article) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(article);
  }

  @Override
  public void deleteByID(Integer articleId) {
    Article article = (Article) this.sessionFactory.getCurrentSession().get(Article.class, articleId);
    this.sessionFactory.getCurrentSession().delete(article);
  }

  @Override
  public List<ArticleWithOutComments> findArticlesWithTxT(String searchText) {
    List<ArticleWithOutComments> searchArtTxt = findAll();
    List<ArticleWithOutComments> tempList = new ArrayList<>();
    for (ArticleWithOutComments var : searchArtTxt) {
      if (var.getText().contains(searchText) || var.getAuthor().contains(searchText) || var.getTitle().contains(searchText)) {
        tempList.add(var);
      }
      }
    return tempList;
    }
  }

