package chenangel.graduationdesign.generator.mapper;

import chenangel.graduationdesign.generator.model.BorrowHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryMapper {
    @Select("select * from books_has_readers where readers_id = #{readers_id}")
    List<BorrowHistory> selectbyrid(@Param("readers_id")Integer readers_id);

    @Insert("insert into books_has_readers values(#{bid},#{rid},#{addtime},#{returntime})")
    int insertrecord(@Param("bid")Integer bid,
                     @Param("rid")Integer rid,
                     @Param("addtime")String addtime,
                     @Param("returntime")String returntime);

}
