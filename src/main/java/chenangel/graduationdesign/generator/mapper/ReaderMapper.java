package chenangel.graduationdesign.generator.mapper;

import chenangel.graduationdesign.generator.model.Reader;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderMapper {
    @Select("select * from readers where readerid = #{readerid} and password = #{password}")
    Reader check(@Param("readerid") String readerid, @Param("password") String password);

    @Select("select * from readers")
    List<Reader> selectall();

    @Update("update readers set readername = #{readername},readerclass = #{readerclass},borrowhistory = #{borrowhistory},sex = #{sex},password = #{password},birthday = #{birthday},identification = #{identification},tel = #{tel} where readerid = #{readerid}")
    int updateinfo(@Param("readerid") String readerid,
                   @Param("readername") String readername,
                   @Param("readerclass") String readerclass,
                   @Param("borrowhistory") String borrowhistory,
                   @Param("birthday") String birthday,
                   @Param("identification") String identification,
                   @Param("tel") String tel,
                   @Param("sex") String sex,
                   @Param("password") String password);

    @Select("select * from readers where readerid = #{readerid}")
    Reader selectbynameid(@Param("readerid") String readerid);

    @Select("select * from readers where id = #{id}")
    Reader selectbyid(@Param("id") Integer id);

    @Insert("insert into readers values(null,#{readerid},#{password},#{readername},#{readerclass},#{borrowhistory},#{sex},#{birthday},#{identification},#{tel})")
    int insertreader(@Param("readerid") String readerid,
                     @Param("readername") String readername,
                     @Param("readerclass") String readerclass,
                     @Param("borrowhistory") String borrowhistory,
                     @Param("birthday") String birthday,
                     @Param("identification") String identification,
                     @Param("tel") String tel,
                     @Param("sex") String sex,
                     @Param("password") String password);

    @Delete("delete from readers where id = #{id}")
    int delete(@Param("id") Integer id);
}
