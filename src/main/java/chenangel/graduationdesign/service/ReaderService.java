package chenangel.graduationdesign.service;

import chenangel.graduationdesign.generator.model.BorrowHistory;
import chenangel.graduationdesign.generator.model.Reader;

import java.util.List;

public interface ReaderService {
    public Reader login(String readerid,String password);

    public boolean addReader(String filepath,String filename);

    public boolean regist(String readerid,
                          String readername,
                          String readerclass,
                          String borrowhistory,
                          String sex,
                          String birthday,
                          String identification,
                          String tel,
                          String password);

    public boolean delReader(Integer id);

    public List<Reader> searchAll();

    public Reader searchById(Integer id);

    public List<BorrowHistory> searchHistoryById(Integer readerid);

    public Reader searchInfoById(Integer readerid);

    public boolean resetps(Integer id,String password);

}
