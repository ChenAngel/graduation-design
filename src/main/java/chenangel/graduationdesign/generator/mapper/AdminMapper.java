package chenangel.graduationdesign.generator.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    @Select("select id from admins where adminname = #{adminname} and password = #{password}")
    Integer check(@Param("adminname") String adminname, @Param("password") String password);


}
