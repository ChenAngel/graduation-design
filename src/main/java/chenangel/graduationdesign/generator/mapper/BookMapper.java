package chenangel.graduationdesign.generator.mapper;

import chenangel.graduationdesign.generator.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    @Select("select * from books")
    List<Book> selectall();

    @Insert("insert into books values(null,#{bookname},#{type},#{writer},#{press},#{pressdate},#{remark},#{price},#{isbn},#{borrowacount},#{nowaccount},#{location})")
    int insertbook(@Param("bookname") String bookname,
                   @Param("type") String type,
                   @Param("writer") String writer,
                   @Param("press") String press,
                   @Param("pressdate") String pressdate,
                   @Param("remark") String remark,
                   @Param("isbn") String isbn,
                   @Param("location") String location,
                   @Param("borrowacount") Integer borrowacount,
                   @Param("nowaccount") Integer nowaccount,
                   @Param("price") Double price);

    @Delete("delete from books where id = #{id}")
    int delbyid(@Param("id") Integer id);

    @Delete("delete from books where bookname = #{bookname}")
    int delbyname(@Param("bookname") String bookname);

    @Update("update books set bookname = #{bookname},type = #{type},writer = #{writer},press = #{press},pressdate = #{pressdate},remark = #{remark},price = #{price},borrowacount = #{borrowacount},nowaccount = #{nowaccount},location = #{location}" +
            "where isbn = #{isbn}")
    int updatebook(@Param("bookname") String bookname,
                   @Param("type") String type,
                   @Param("writer") String writer,
                   @Param("press") String press,
                   @Param("pressdate") String pressdate,
                   @Param("remark") String remark,
                   @Param("isbn") String isbn,
                   @Param("location") String location,
                   @Param("borrowacount") Integer borrowacount,
                   @Param("nowaccount") Integer nowaccount,
                   @Param("price") Double price);

    @Select("select * from books where ${field} = #{value}")
    List<Book> fieldselect(@Param("field") String field,
                           @Param("value") String value);

    @Select("select * from books where id = #{id}")
    Book selectbyid(@Param("id") Integer id);
}
