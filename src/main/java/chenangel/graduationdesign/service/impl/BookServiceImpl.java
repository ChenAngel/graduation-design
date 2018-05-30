package chenangel.graduationdesign.service.impl;

import chenangel.graduationdesign.generator.mapper.BookMapper;
import chenangel.graduationdesign.generator.mapper.HistoryMapper;
import chenangel.graduationdesign.generator.mapper.OrderMapper;
import chenangel.graduationdesign.generator.model.Book;
import chenangel.graduationdesign.generator.model.Typecount;
import chenangel.graduationdesign.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import untils.Kmaens;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private HistoryMapper historyMapper;

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
    public boolean changeBook(String bookname, String type, String writer, String press, String pressdate, String remark, String isbn, String location, Integer borrowacount, Integer nowaccount, Double price,Integer totalaccount,Integer id) {
        return bookMapper.updatebook(bookname, type, writer, press, pressdate, remark, isbn, location, borrowacount,totalaccount, nowaccount, id,price)==1;
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

    @Override
    public boolean borrowbook(Integer bid, Integer rid, String uuid,Integer aid) {
        String status = "borrowing";
        int sign1 = orderMapper.updateorder(uuid,status,aid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addtime = simpleDateFormat.format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,+1);
        String returntime = simpleDateFormat.format(calendar.getTime());
        int sign2 = historyMapper.insertrecord(bid,rid,addtime,returntime);
        return sign1==1&&sign2==1;
    }

    @Override
    public Book searchByName(String bookname) {
        return bookMapper.fieldselect("bookname",bookname).get(0);
    }

    @Override
    public boolean returnbook(Integer bid, Integer rid, String uuid, Integer aid) {
        String status = "ok";
        int sign1 = orderMapper.updateorder(uuid,status,aid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,+1);
        String returntime = simpleDateFormat.format(calendar.getTime());
        int sign2 = historyMapper.insertrecord(bid,rid,"before",returntime);
        return sign1==1&&sign2==1;
    }

    @Override
    public List<Book> push() {
        Kmaens kmaens = new Kmaens();
        List<Typecount> typecounts = bookMapper.typeaccount();
        Map<String,Integer> map = new HashMap();
        for (Typecount typemap:typecounts) {
            map.put(typemap.getType(),typemap.getTotal());
        }
        List<Integer> idlist = kmaens.getPopularBookId(bookMapper.selectall(),bookMapper.allborrowacount(),map);
        List<Book> pushlist = new LinkedList<>();
        for (Integer id:idlist) {
            Book book = bookMapper.selectbyid(id);
            pushlist.add(book);
        }
        return pushlist;
    }
}
