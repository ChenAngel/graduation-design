package chenangel.graduationdesign.controller;

import chenangel.graduationdesign.admin.MySession;
import chenangel.graduationdesign.generator.mapper.BookMapper;
import chenangel.graduationdesign.generator.model.BorrowHistory;
import chenangel.graduationdesign.generator.model.Order;
import chenangel.graduationdesign.generator.model.Reader;
import chenangel.graduationdesign.service.BookService;
import chenangel.graduationdesign.service.OrderService;
import chenangel.graduationdesign.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import untils.ScriptUtil;

import java.util.List;

@Controller
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{readerid}/{password}/login", method = RequestMethod.POST)
    @ResponseBody
    public String yuyue(@PathVariable("readerid") String readerid,
                        @PathVariable("password") String password) throws Exception{
        Reader reader = readerService.login(readerid, password);
        if (reader!=null) {
            MySession.setAttr("rid",reader.getId());
            MySession.setAttr("readerid",reader.getReaderid());
            return "登录成功";
        }else {
            return "登录失败";
        }
    }

    @RequestMapping(value = "/borrowhistory", method = RequestMethod.POST)
    @ResponseBody
    public List<BorrowHistory> searchforhistory() throws Exception{
        List<BorrowHistory> borrowHistories = readerService.searchHistoryById((Integer)MySession.getAttr("rid"));
        if (borrowHistories==null) {
            return null;
        }
        for (BorrowHistory bs:borrowHistories) {
            bs.setBookname(bookService.searchById(bs.getBooks_id()).getBookname());
        }
        return borrowHistories;
    }

    @RequestMapping(value = "/myinfo", method = RequestMethod.POST)
    @ResponseBody
    public Reader myinfo() throws Exception{
        Reader info = readerService.searchById((Integer) MySession.getAttr("rid"));
        return info;
    }

    @RequestMapping(value = "/searchorders", method = RequestMethod.POST)
    @ResponseBody
    public List<Order> orderinfo() throws Exception{
        List<Order> orders = orderService.saerchByRid((Integer) MySession.getAttr("rid"));
        return orders;
    }
}
