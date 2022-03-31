package sk.ness.academy.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import sk.ness.academy.dto.Author;
import sk.ness.academy.dto.AuthorStats;

@Repository
public class AuthorHibernateDAO implements AuthorDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Author> findAll() {
    return this.sessionFactory.getCurrentSession().createSQLQuery("select distinct a.author as name from articles a ")
        .addScalar("name", StringType.INSTANCE)
        .setResultTransformer(new AliasToBeanResultTransformer(Author.class)).list();
  }

 /* @Override
  public List<AuthorStats> getAllAuhtorsStats() {
    return  this.sessionFactory.getCurrentSession().createSQLQuery("select author, count(*) as count from articles group by author")
            .addScalar("author", StringType.INSTANCE)
            .addScalar("count", IntegerType.INSTANCE)
            .setResultTransformer(new AliasToBeanResultTransformer(AuthorStats.class)).list();
  }
  */
 @Override
 public List<AuthorStats> getAllAuhtorsStats() {
   return  this.sessionFactory.getCurrentSession().createSQLQuery("select author, count(*) as count from articles group by author")
           .addScalar("author", StringType.INSTANCE)
           .addScalar("count", IntegerType.INSTANCE)
           .list();
 }


}

