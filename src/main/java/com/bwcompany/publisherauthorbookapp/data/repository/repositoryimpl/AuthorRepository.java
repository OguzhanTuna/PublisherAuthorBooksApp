package com.bwcompany.publisherauthorbookapp.data.repository.repositoryimpl;

import com.bwcompany.publisherauthorbookapp.data.entity.Author;
import com.bwcompany.publisherauthorbookapp.data.repository.IAuthorRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements IAuthorRepository {
    //Artık DBMS üzerinden veritabanına bağlantı için, içerisinde veritabanına bağlantı araçları içeren Template pattern' a
    //uygun şekilde yapılmış olan Spring' in "JdbcTemplate" sınıfını kullanıyoruz. SpringBOOT client-server mantığıyla çalışan
    //Database Management System' a ilişkin bağlantı bilgilerini "application.properties" dosyası parse ettikten sonra okur
    //Ve bağlantıyı gerçekleştirir.
    //Final veri elemanları "derleyici optimizasyonundan kurtulmak" için tanımlanır ve "Always applicable" dır.
    //Final veri elemanlarına eğer non-static final ise "constructor, non-static initializer ve bildirim anında" atama yapılabilir.
    //Final veri elemanı eğer static final ise "static initializer ve bildirim anında" atama yapılabilir.
    private final JdbcTemplate m_jdbcTemplate;
    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    private static final String m_findAllSql = "select * from t_author";
    private static final String m_findByNameSql = "select * from t_author where t_author.name = ?";
    private static final String m_deleteByIdSql = "delete from t_author where t_author.id = ?";
    private static final String m_saveSql = "insert into t_author (name, explanation, publisher_id) values (:name, :explanation, :publisherId)";
    //...

    private List<Author> fillListByResultSet(ResultSet rs, List<Author> authors) throws SQLException
    {
        //* sorgularında sorgu sonucu dönen ResultSet interfacesini destekleyen bir class' ın nesnesinin adresi bir kere "next()" metodu
        //çağırılmış şekilde gelir. Bu yüzden do-while kullanmak ilk data yı almak için mühimdir.

        while(rs.next()){
            Author author = new Author();

            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            author.setExplanation(rs.getString("explanation"));
            author.setPublisherId(rs.getInt("publisher_id"));

            authors.add(author);
        }

        rs.close();

        return authors;
    }

    private class RowMapperImpl implements RowMapper<Author>{
        //...

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            Author author = new Author();

            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            author.setExplanation(rs.getString("explanation"));
            author.setPublisherId(rs.getInt("publisher_id"));

            return author;
        }
    }

    //Constructor Injection(No @Autowired annotation since Spring 4.3)
    public AuthorRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_jdbcTemplate = jdbcTemplate;
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void delete(Author entity)
    {
        Object[] args = {entity.getId()};

        m_jdbcTemplate.update(m_deleteByIdSql, args);
    }

    @Override
    public Iterable<Author> findAll()
    {
        List<Author> authors = new ArrayList<>();

        m_jdbcTemplate.query(m_findAllSql, (ResultSet rs) -> this.fillListByResultSet(rs, authors));

        return authors;
    }

    @Override
    public Iterable<Author> findByName(String name)
    {
        //return m_jdbcTemplate.query(m_findByNameSql, new RowMapperImpl());
        List<Author> authors = new ArrayList<>();

        m_jdbcTemplate.query(m_findByNameSql, new Object[]{name}, (ResultSet rs) -> this.fillListByResultSet(rs, authors));

        return authors;
    }

    @Override
    public <E extends Author> E save(E entity)
    {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        m_namedParameterJdbcTemplate.update(m_saveSql, new BeanPropertySqlParameterSource(entity), keyHolder, new String[]{"id"});

        int primaryKey = keyHolder.getKey().intValue();

        entity.setId(primaryKey);

        return entity;
    }

    //////////
    @Override
    public long count()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Iterable<? extends Author> entities)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Integer integer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existById(Integer integer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Author> findById(Integer integer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E extends Author> E saveAll(Iterable<E> entites)
    {
        throw new UnsupportedOperationException();
    }
}
