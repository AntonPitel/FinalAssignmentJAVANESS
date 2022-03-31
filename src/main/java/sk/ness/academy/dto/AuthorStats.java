package sk.ness.academy.dto;

public class AuthorStats {

	  private String authorName;
	  private Long articleCount;

	  public String getAuthorName() {
	    return this.authorName;
	  }

	  public void setAuthorName(final String authorName) {
	    this.authorName = authorName;
	  }

	  public int getArticleCount() {
	    return Math.toIntExact(this.articleCount);
	  }

	public AuthorStats(String authorName, Long articleCount) {
		this.authorName = authorName;
		this.articleCount = Long.valueOf(articleCount);
	}

	public void setArticleCount(final Long articleCount) {
	    this.articleCount = Long.valueOf(articleCount);


	  }

	}
