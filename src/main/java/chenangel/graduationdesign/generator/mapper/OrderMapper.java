package chenangel.graduationdesign.generator.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    @Insert("insert into orders values(null,#{uuid},#{rid},0,'open',#{addtime},#{bookname})")
    int insertorder(@Param("uuid")String uuid,
                    @Param("addtime")String addtime,
                    @Param("bookname")String bookname,
                    @Param("rid")Integer rid);

    @Update("update orders set order_aid = #{aid},status = #{status} where order_uuid = #{uuid}")
    int updateorder(@Param("uuid")String uuid,
                    @Param("status")String status,
                    @Param("aid")Integer aid);
}
