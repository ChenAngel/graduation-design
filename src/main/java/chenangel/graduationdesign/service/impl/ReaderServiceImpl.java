package chenangel.graduationdesign.service.impl;

import chenangel.graduationdesign.generator.mapper.HistoryMapper;
import chenangel.graduationdesign.generator.mapper.OrderMapper;
import chenangel.graduationdesign.generator.mapper.ReaderMapper;
import chenangel.graduationdesign.generator.model.Book;
import chenangel.graduationdesign.generator.model.BorrowHistory;
import chenangel.graduationdesign.generator.model.Reader;
import chenangel.graduationdesign.service.BookService;
import chenangel.graduationdesign.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService{
    @Autowired
    private ReaderMapper readerMapper;
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BookService bookService;

    @Override
    public Reader login(String readerid, String password) {
        return readerMapper.check(readerid, password);
    }

    /**
     * 本方法用于xml或者excel入库
     * @param readers
     * @return
     */
    @Override
    public boolean addReader(List<Reader> readers) {
        Integer count = 0;
        for (Reader reader:readers) {
            count += readerMapper.insertreader(reader.getReaderid(),reader.getReadername(),reader.getReaderclass(),reader.getBorrowhistory(),reader.getBirthday(),reader.getIdentification(),reader.getTel(),reader.getSex(),reader.getPassword());
        }
        return count==readers.size();
    }

    /**
     *
     * @param readerid
     * @param readername
     * @param readerclass
     * @param borrowhistory
     * @param sex
     * @param tel
     * @param birthday
     * @param identification
     * @param password
     * @return
     */
    @Override
    public boolean regist(String readerid, String readername, String readerclass, String borrowhistory, String sex, String birthday, String identification, String tel, String password) {
        return readerMapper.insertreader(readerid, readername, readerclass, borrowhistory, sex, birthday, identification, tel, password)!=0;
    }

    @Override
    public boolean delReader(Integer id) {
        return readerMapper.delete(id)==1;
    }

    @Override
    public List<Reader> searchAll() {
        return readerMapper.selectall();
    }

    @Override
    public Reader searchById(Integer id) {
        return readerMapper.selectbyid(id);
    }

    @Override
    public List<BorrowHistory> searchHistoryById(Integer readerid) {
        List<BorrowHistory> borrowHistories = historyMapper.selectbyrid(readerid);
        return borrowHistories;
    }

    @Override
    public Reader searchInfoById(String readerid) {
        return readerMapper.selectbynameid(readerid);
    }

    @Override
    public boolean resetps(Integer id, String password) {
        return readerMapper.updatepassword(id,password)!=0;
    }

    @Override
    public List<Reader> fieldSearch(String field, String value) {
        return readerMapper.fieldselect(field, value);
    }

    @Override
    public boolean changeinfo(Integer id, String readerid, String password, String readername, String readerclass,  String sex, String birthday, String identification, String tel) {
        return readerMapper.updateinfo(readerid,readername,readerclass,birthday,identification,tel,id,sex,password)==1;
    }

    @Override
    public boolean returnbook(Integer bid, Integer rid, Integer aid) {
        Book book = bookService.searchById(bid);
        String uuid = orderMapper.selectbyridbookname(rid,book.getBookname()).getOrder_uuid();
        Boolean sign1 = orderMapper.updateorder(uuid,"ok",aid)==1;
        Boolean sign2 = bookService.changeBook(book.getBookname(),book.getType(),book.getWriter(),book.getPress(),book.getPressdate(),book.getRemark(),book.getIsbn(),book.getLocation(),book.getBorrowacount(),book.getNowaccount()+1,book.getPrice(),book.getTotalaccount(),book.getId());
        return sign1&&sign2;
    }

    @Override
    public List<BorrowHistory> searchhistorybyreaderid(Integer rid) {
        List<BorrowHistory> borrowHistoryList = historyMapper.selectbyrid(rid);
        for (BorrowHistory history:borrowHistoryList) {
            history.setBookname(bookService.searchById(history.getBooks_id()).getBookname());
        }
        return borrowHistoryList;
    }
}
