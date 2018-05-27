package untils;

import chenangel.graduationdesign.generator.mapper.BookMapper;
import chenangel.graduationdesign.generator.model.BorrowHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class ScriptUtil {

    @Autowired
    private static BookMapper bookMapper;

    public static List<BorrowHistory> borrowHistoriyGetBookname(List<BorrowHistory> borrowHistories){
        for (BorrowHistory borrowHistory:borrowHistories) {
            borrowHistory.setBookname(bookMapper.selectbyid(borrowHistory.getBooks_id()).getBookname());
        }
        return borrowHistories;
    }
}
