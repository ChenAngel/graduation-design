package chenangel.graduationdesign.controller;

import chenangel.graduationdesign.admin.MySession;
import chenangel.graduationdesign.generator.model.Book;
import chenangel.graduationdesign.service.BookService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chenangel.graduationdesign.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/book"})
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(
            value = {"/addbook"},
            method = {RequestMethod.POST}
    )
    public String addbook(@RequestParam("isbn") String isbn,
                          @RequestParam("bookname") String bookname,
                          @RequestParam("type") String type,
                          @RequestParam("writer") String writer,
                          @RequestParam("press") String press,
                          @RequestParam("pressdate") String pressdate,
                          @RequestParam("remark") String remark,
                          @RequestParam("nowaccount") Integer nowaccount,
                          @RequestParam("borrowacount") Integer borrowacount,
                          @RequestParam("totalaccount") Integer totalaccount,
                          @RequestParam("location") String location,
                          @RequestParam("price") Double price) {

        String msg = new String();
        try {
            boolean sign = this.bookService.addBook(bookname,type,writer,press,pressdate,remark,isbn,location,borrowacount,nowaccount,totalaccount,price);
            if (sign) {
                msg = "添加成功";
            } else {
                msg = "添加失败，未知错误";
            }
        } catch (Exception var13) {
            msg = "添加失败，参数错误";
        }

        return msg;
    }

    @RequestMapping({"/delbyid"})
    public String delBookById(@RequestParam("id") Integer id) {
        new String();
        boolean sign = this.bookService.delBookById(id);
        String msg;
        if (sign) {
            msg = "删除成功";
        } else {
            msg = "删除失败，未知错误";
        }

        return msg;
    }


    @RequestMapping(
            value = {"/changeinfo"},
            method = {RequestMethod.POST}
    )
    public String changebook(@RequestParam("isbn") String isbn,
                             @RequestParam("bookname") String bookname,
                             @RequestParam("type") String type,
                             @RequestParam("writer") String writer,
                             @RequestParam("press") String press,
                             @RequestParam("pressdate") String pressdate,
                             @RequestParam("remark") String remark,
                             @RequestParam("location") String location,
                             @RequestParam("nowaccount") Integer nowaccount,
                             @RequestParam("borrowacount") Integer borrowacount,
                             @RequestParam("price") Double price) {
        new String();
        boolean sign = this.bookService.changeBook(bookname,type,writer,press,pressdate,remark,isbn,location,borrowacount,nowaccount,price);
        String msg;
        if (sign) {
            msg = "修改成功";
        } else {
            msg = "修改失败，未知错误";
        }

        return msg;
    }

    @RequestMapping(
            value = {"/{field}/{value}/fieldsearch"},
            produces = {"application/json"}
    )
    public Map fieldsearch(@PathVariable("field") String field, @PathVariable("value") String value) {
        List<Book> bookList = this.bookService.fieldSearch(field, value);
        Integer size = bookList.size();
        Map map = new HashMap();
        map.put("booklist", bookList);
        map.put("size", size);
        return map;
    }

    @RequestMapping(
            value = {"/searchall"},
            produces = {"application/json"}
    )
    public Map searchall() {
        List<Book> bookList = this.bookService.searchAll();
        Integer size = bookList.size();
        Map map = new HashMap();
        map.put("booklist", bookList);
        map.put("size", size);
        return map;
    }

    @RequestMapping(value = "/{id}/yuyue",method = RequestMethod.POST)
    public String yuyue(@PathVariable("id") Integer bid) throws Exception{
        Integer rid = (Integer) MySession.getAttr("rid");
        String uuid = orderService.yuyue(rid,bid);
        if (uuid!=null) {
            return uuid;
        }else {
            return "预约失败";
        }
    }
}

