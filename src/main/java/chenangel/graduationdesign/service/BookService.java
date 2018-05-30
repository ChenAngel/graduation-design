package chenangel.graduationdesign.service;

import chenangel.graduationdesign.generator.model.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    public boolean addBook(String bookname,
                           String type,
                           String writer,
                           String press,
                           String pressdate,
                           String remark,
                           String isbn,
                           String location,
                           Integer borrowacount,
                           Integer nowaccount,
                           Integer totalaccount,
                           Double price);

    public boolean delBookById(Integer id);

    public boolean changeBook(String bookname,
                              String type,
                              String writer,
                              String press,
                              String pressdate,
                              String remark,
                              String isbn,
                              String location,
                              Integer borrowacount,
                              Integer nowaccount,
                              Double price,
                              Integer totalaccount,
                              Integer id);

    public List<Book> fieldSearch(String field, String value);

    public List<Book> searchAll();

    public Book searchById(Integer id);

    public Map typeCount();

    public List<Map> hotestbookTop10();

    public boolean borrowbook(Integer bid,Integer rid,String uuid,Integer aid);

    public Book searchByName(String bookname);

    public boolean returnbook(Integer bid,Integer rid,String uuid,Integer aid);

    public List<Book> push();
}
