package sk.ness.academy.dao;

import java.util.List;

import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticleWithOutComments;

public interface ArticleDAO {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  /** Returns all available {@link Article}s */
	  List<ArticleWithOutComments> findAll();

	  /** Persists {@link Article} into the DB */
	  void persist(Article article);

    void deleteByID(Integer articleId);

    List<ArticleWithOutComments> findArticlesWithTxT(String searchText);
}
