package chenangel.graduationdesign.generator.mapper;

import chenangel.graduationdesign.generator.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookMapper {
    @Select("select * from books")
    List<Book> selectall();

    @Insert("insert into books values(null,#{bookname},#{type},#{writer},#{press},#{pressdate},#{remark},#{price},#{isbn},#{borrowacount},#{nowaccount},#{location},#{addtime},#{totalaccount})")
    int insertbook(@Param("bookname") String bookname,
                   @Param("type") String type,
                   @Param("writer") String writer,
                   @Param("press") String press,
                   @Param("pressdate") String pressdate,
                   @Param("remark") String remark,
                   @Param("isbn") String isbn,
                   @Param("location") String location,
                   @Param("addtime") String addtime,
                   @Param("borrowacount") Integer borrowacount,
                   @Param("nowaccount") Integer nowaccount,
                   @Param("totalaccount") Integer totalaccount,
                   @Param("price") Double price);

    @Delete("delete from books where id = #{id}")
    int delbyid(@Param("id") Integer id);

    @Delete("delete from books where bookname = #{bookname}")
    int delbyname(@Param("bookname") String bookname);

    @Update("update books set bookname = #{bookname},type = #{type},writer = #{writer},press = #{press},pressdate = #{pressdate},remark = #{remark},price = #{price},borrowacount = #{borrowacount},nowaccount = #{nowaccount},location = #{location},totalaccount = #{totalaccount},isbn = #{isbn}" +
            "where id = #{id}")
    int updatebook(@Param("bookname") String bookname,
                   @Param("type") String type,
                   @Param("writer") String writer,
                   @Param("press") String press,
                   @Param("pressdate") String pressdate,
                   @Param("remark") String remark,
                   @Param("isbn") String isbn,
                   @Param("location") String location,
                   @Param("borrowacount") Integer borrowacount,
                   @Param("totalaccount") Integer totalaccount,
                   @Param("nowaccount") Integer nowaccount,
                   @Param("id") Integer id,
                   @Param("price") Double price);

    @Select("select * from books where ${field} = #{value}")
    List<Book> fieldselect(@Param("field") String field,
                           @Param("value") String value);

    @Select("select * from books where id = #{id}")
    Book selectbyid(@Param("id") Integer id);

    @Select("select count(*) as total,type from books group by type")
    List<Map> typecount();

    @Select("select isbn,bookname,writer,borrowacount from books order by borrowacount desc limit 0,10")
    List<Map> hotestTop10();
}
