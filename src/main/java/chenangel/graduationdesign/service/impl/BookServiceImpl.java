package chenangel.graduationdesign.service.impl;

import chenangel.graduationdesign.generator.mapper.BookMapper;
import chenangel.graduationdesign.generator.model.Book;
import chenangel.graduationdesign.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean addBook(String bookname, String type, String writer, String press, String pressdate, String remark, String isbn, String location, Integer borrowacount, Integer nowaccount, Integer totalaccount, Double price) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addtime = simpleDateFormat.format(new Date());
        return bookMapper.insertbook(bookname, type, writer, press, pressdate, remark, isbn, location, addtime,borrowacount, nowaccount,totalaccount, price)==1;
    }

    @Override
    public boolean delBookById(Integer id) {
        return bookMapper.delbyid(id)==1;
    }

    @Override
    public boolean changeBook(String bookname, String type, String writer, String press, String pressdate, String remark, String isbn, String location, Integer borrowacount, Integer nowaccount, Double price) {
        return bookMapper.updatebook(bookname, type, writer, press, pressdate, remark, isbn, location, borrowacount, nowaccount, price)==1;
    }

    @Override
    public List<Book> fieldSearch(String field, String value) {
        return bookMapper.fieldselect(field,value);
    }

    @Override
    public List<Book> searchAll() {
        return bookMapper.selectall();
    }

    @Override
    public Book searchById(Integer id) {
        return bookMapper.selectbyid(id);
    }

    @Override
    public Map typeCount() {
        List<Map> mapList = bookMapper.typecount();
        List<String> typeList = new LinkedList<>();
        List<Long> totalList = new LinkedList<>();
        Map result = new HashMap();
        for (Map temmap:mapList) {
            typeList.add((String) temmap.get("type"));
            totalList.add((Long) temmap.get("total"));
        }
        result.put("typelist",typeList);
        result.put("totallist",totalList);
        return result;
    }

    @Override
    public List<Map> hotestbookTop10() {
        List<Map> mapList = bookMapper.hotestTop10();
        return mapList;
    }
}
