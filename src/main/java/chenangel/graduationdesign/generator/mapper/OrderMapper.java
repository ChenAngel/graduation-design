package chenangel.graduationdesign.generator.mapper;

import chenangel.graduationdesign.generator.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("select * from orders where order_rid = #{rid}")
    List<Order> selectbyrid(@Param("rid")Integer rid);
}
