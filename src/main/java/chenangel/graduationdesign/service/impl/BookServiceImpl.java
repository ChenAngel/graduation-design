package chenangel.graduationdesign.service.impl;

import chenangel.graduationdesign.generator.mapper.BookMapper;
import chenangel.graduationdesign.generator.model.Book;
import chenangel.graduationdesign.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean addBook(String bookname, String type, String writer, String press, String pressdate, String remark, String isbn, String location, Integer borrowacount, Integer nowaccount, Double price) {
        return bookMapper.insertbook(bookname, type, writer, press, pressdate, remark, isbn, location, borrowacount, nowaccount, price)==1;
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
}
