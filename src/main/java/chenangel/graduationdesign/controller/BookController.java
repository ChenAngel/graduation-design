package chenangel.graduationdesign.controller;

import chenangel.graduationdesign.admin.MySession;
import chenangel.graduationdesign.generator.model.Book;
import chenangel.graduationdesign.generator.model.Order;
import chenangel.graduationdesign.generator.model.Reader;
import chenangel.graduationdesign.service.BookService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chenangel.graduationdesign.service.OrderService;
import chenangel.graduationdesign.service.ReaderService;
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
    @Autowired
    private ReaderService readerService;

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
                             @RequestParam("totalaccount") Integer totalaccount,
                             @RequestParam("id") Integer id,
                             @RequestParam("price") Double price) {
        boolean sign = this.bookService.changeBook(bookname,type,writer,press,pressdate,remark,isbn,location,borrowacount,nowaccount,price,totalaccount,id);
        String msg =  null;
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

    //预约controller
    @RequestMapping(value = "/{id}/yuyue",method = RequestMethod.POST)//只允许post形式访问，返回json格式数据
    public String yuyue(@PathVariable("id") Integer bid) throws Exception{//路径取参
        Integer rid = (Integer) MySession.getAttr("rid");//从session中获取当前登录的读者ID
        String uuid = orderService.yuyue(rid,bid);//操作业务逻辑层的预约方法
        if (uuid!=null) {//结果返回，成功返回预约单号，失败则返回预约失败
            return uuid;
        }else {
            return "预约失败";
        }
    }

    @RequestMapping(value = "/{id}/searchbyid")
    public Book saerchbyid(@PathVariable("id") Integer bid){
        Book book = bookService.searchById(bid);
        return book;
    }

    @RequestMapping(value = "/jieyue")
    public String  jieyue(@RequestParam("readerid") String  readerid,
                          @RequestParam("bid") Integer  bid){
        Integer aid = (Integer) MySession.getAttr("aid");
        Reader reader = readerService.searchInfoById(readerid);
        Book book = bookService.searchById(bid);
        if (book.getNowaccount()<1) {
            return "借阅操作失败，书本存量不足";
        }
        bookService.changeBook(book.getBookname(),book.getType(),book.getWriter(),book.getPress(),book.getPressdate(),book.getRemark(),book.getIsbn(),book.getLocation(),book.getBorrowacount()+1,book.getNowaccount()-1,book.getPrice(),book.getTotalaccount(),book.getId());
        Order order = orderService.findByRidandBookname(reader.getId(),book.getBookname());
        boolean sign = bookService.borrowbook(book.getId(),reader.getId(),order.getOrder_uuid(),aid);
        if (sign) {
            return "借阅操作已完成";
        }else{
            return "借阅操作出错，未知错误";
        }
    }

    @RequestMapping(value = "/guihuan")
    public String  guihuan(@RequestParam("readerid") String  readerid,
                          @RequestParam("bid") Integer  bid){
        Integer aid = (Integer) MySession.getAttr("aid");
        Reader reader = readerService.searchInfoById(readerid);
        Book book = bookService.searchById(bid);
        bookService.changeBook(book.getBookname(),book.getType(),book.getWriter(),book.getPress(),book.getPressdate(),book.getRemark(),book.getIsbn(),book.getLocation(),book.getBorrowacount(),book.getNowaccount()+1,book.getPrice(),book.getTotalaccount(),book.getId());
        Order order = orderService.findByRidandBookname(reader.getId(),book.getBookname());
        boolean sign = bookService.returnbook(book.getId(),reader.getId(),order.getOrder_uuid(),aid);
        if (sign) {
            return "归还操作已完成";
        }else{
            return "归还操作出错，未知错误";
        }
    }

    @RequestMapping(
            value = {"/push"},
            produces = {"application/json"}
    )
    public List<Book> push() {
        return bookService.push();
    }
}

