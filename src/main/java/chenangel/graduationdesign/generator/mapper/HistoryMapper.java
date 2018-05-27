package chenangel.graduationdesign.generator.mapper;

import chenangel.graduationdesign.generator.model.BorrowHistory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryMapper {
    @Select("select * from books_has_readers where readers_id = #{readers_id}")
    List<BorrowHistory> selectbyrid(@Param("readers_id")Integer readers_id);
}
