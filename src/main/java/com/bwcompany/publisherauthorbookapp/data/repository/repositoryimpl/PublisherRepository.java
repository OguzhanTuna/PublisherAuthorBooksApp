package com.bwcompany.publisherauthorbookapp.data.repository.repositoryimpl;

import com.bwcompany.publisherauthorbookapp.data.entity.Publisher;
import com.bwcompany.publisherauthorbookapp.data.repository.IPublisherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Repository
public class PublisherRepository implements IPublisherRepository {
    //static veri elemanları SpringBOOT tarafından injection işlemine tabi tutulmaz.
    @Value("${sql.postgresql.commands.findall}")
    private String m_findAllSql;

    @Value("${sql.postgresql.commands.findbyname}")
    private String m_findByNameSql;

    @Value("${sql.postgresql.commands.save}")
    private String m_saveSql;

    @Value("${sql.postgresql.commands.delete}")
    private String m_deleteSql;
    //...

    public void deleteByExecuteUpdate(String sql, int id) throws SQLException
    {
        Connection connection = this.getConnectionAndGet().get();

        try(connection){
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(m_deleteSql);
            ps.setInt(1, id);

            ps.executeUpdate();

            connection.commit();
        }catch (SQLException ex){
            connection.rollback();
            System.out.println(ex.getMessage());
        }
    }

    private OptionalInt saveByExecuteUpdateResultKey(String sql, Object...objs) throws SQLException
    {
         Connection connection = this.getConnectionAndGet().get();

        try(connection){
            connection.setAutoCommit(false);

            //PrepareStatement parametreli kullanımdır. Sql Injection gibi istenmeyen durumların önüne geçer.
            PreparedStatement ps = connection.prepareStatement(m_saveSql, Statement.RETURN_GENERATED_KEYS);
            for(int i = 0; i < objs.length; ++i){
                ps.setObject(i + 1, objs[i]);
            }

            ps.executeUpdate();

            ResultSet resultSetForKey = ps.getGeneratedKeys();

            //* sorgularında ResultSet bir kere "next()" yapılmış halde gelir. Fakat * sorgusu yapmaz isek
            //ilerlemiş halde gelmez bizim bir kere "next()" metodunu çağırıp ilerletmemiz gerekir.
            resultSetForKey.next();

            int id = resultSetForKey.getInt(1);

            connection.commit();

            return OptionalInt.of(id);
        }catch (SQLException ex){
            connection.rollback();
            System.out.println(ex.getMessage());
        }

        return OptionalInt.empty();
    }

    private List<Publisher> getResultByExecuteQueryResultList(String sql, List<Publisher> publishers) throws SQLException
    {
        Connection connection = this.getConnectionAndGet().get();

        //Try-with resources. Java 7 den sonra "Auto Closeable" ile birlikte gelmiştir.
        try(connection){
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            this.fillListByResultSet(rs, publishers);

            System.out.println("SEX");

            connection.commit();
        }catch (SQLException ex){
            connection.rollback();
            System.out.println(ex.getMessage());
        }

        return publishers;
    }

    private List<Publisher> getResultByExecuteQueryResultListWithParams(String sql, List<Publisher> publishers, Object...params) throws SQLException
    {
        Connection connection = this.getConnectionAndGet().get();

        try(connection){
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(m_findByNameSql);

            for(int i = 0; i < params.length; ++i){
                ps.setObject(i, params[i]);
            }

            ResultSet rs = ps.executeQuery();

            this.fillListByResultSet(rs, publishers);

            connection.commit();
        }catch (SQLException ex){
            connection.rollback();
            System.out.println(ex.getMessage());
        }

        return publishers;
    }

    private Optional<Connection> getConnectionAndGet()
    {
        Connection connection = null;

        try{
            //DriverManager Runtime da Reflection ile Postgresql JDBC Driver JAR' ına bakar. Ve ilgili class' ı bulur.
            //Biz burda bunu yazıyoruz.
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/publisherapp?currentSchema=publisher",
                    "postgres", "Dolukutu1");

            return Optional.of(connection);
        }catch (ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }

        return Optional.empty();
    }

    private void fillListByResultSet(ResultSet rs, List<Publisher> publishers) throws SQLException
    {
        while (rs.next()){
            Publisher publisher = new Publisher();

            publisher.setId(rs.getInt("id"));
            publisher.setName(rs.getString("name"));
            publisher.setExplanation(rs.getString("explanation"));

            publishers.add(publisher);
        }

        rs.close();
    }

    @Override
    public void delete(Publisher entity)
    {
        try{
            this.deleteByExecuteUpdate(m_deleteSql, entity.getId());
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Iterable<Publisher> findAll()
    {
        List<Publisher> publishers = new ArrayList<>();

        try{
            publishers = this.getResultByExecuteQueryResultList(m_findAllSql, publishers);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return publishers;
    }

    @Override
    public Iterable<Publisher> findByName(String name)
    {
        List<Publisher> publishers = new ArrayList<>();

        try{
            publishers = this.getResultByExecuteQueryResultListWithParams(m_findByNameSql, publishers, name);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return publishers;
    }

    @Override
    public <E extends Publisher> E save(E entity)
    {
        try{
            int result = this.saveByExecuteUpdateResultKey(m_saveSql, entity.getName(), entity.getExplanation()).getAsInt();

            entity.setId(result);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

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
    public void delete(Iterable<? extends Publisher> entities)
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
    public Optional<Publisher> findById(Integer integer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E extends Publisher> E saveAll(Iterable<E> entites)
    {
        throw new UnsupportedOperationException();
    }
}
