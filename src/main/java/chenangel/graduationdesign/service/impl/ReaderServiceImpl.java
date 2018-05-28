package chenangel.graduationdesign.service.impl;

import chenangel.graduationdesign.generator.mapper.HistoryMapper;
import chenangel.graduationdesign.generator.mapper.ReaderMapper;
import chenangel.graduationdesign.generator.model.BorrowHistory;
import chenangel.graduationdesign.generator.model.Reader;
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

    @Override
    public Reader login(String readerid, String password) {
        return readerMapper.check(readerid, password);
    }

    /**
     * 本方法用于xml或者excel入库
     * @param filepath
     * @param filename
     * @return
     */
    @Override
    public boolean addReader(String filepath, String filename) {
        return false;
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
    public Reader searchInfoById(Integer readerid) {
        return readerMapper.selectbyid(readerid);
    }
}
