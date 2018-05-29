package chenangel.graduationdesign.generator.mapper;

import chenangel.graduationdesign.generator.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Select("select * from orders where order_rid = #{rid} and status = 'borrowing'")
    List<Order> selectbyidandstatus(@Param("rid")Integer rid);

    @Select("SELECT DATE_FORMAT(addtime,'%Y/%m/%d') as time,count(*) as total " +
            "from orders where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= addtime " +
            "group by DATE_FORMAT(addtime,'%Y %m %d')")
    List<Map> selectrecentborrow();

    @Delete("delete from orders where order_uuid = #{uuid}")
    int delbyuuid(@Param("uuid")String uuid);

    @Select("select * from orders where order_rid = #{rid} and bookname = #{bookname}")
    Order selectbyridbookname(@Param("rid")Integer rid,@Param("bookname")String bookname);
}
