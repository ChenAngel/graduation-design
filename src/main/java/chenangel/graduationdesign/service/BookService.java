package chenangel.graduationdesign.service;

import chenangel.graduationdesign.generator.model.Book;

import java.util.List;

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
                              Double price);

    public List<Book> fieldSearch(String field, String value);

    public List<Book> searchAll();

    public Book searchById(Integer id);

}
