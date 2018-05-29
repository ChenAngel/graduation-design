package chenangel.graduationdesign.controller;

import chenangel.graduationdesign.admin.MySession;
import chenangel.graduationdesign.service.AdminService;
import chenangel.graduationdesign.service.BookService;
import chenangel.graduationdesign.service.OrderService;
import chenangel.graduationdesign.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/{adminname}/{password}/login")
    @ResponseBody
    public String login(@PathVariable("adminname")String adminname,
                        @PathVariable("password")String password){
        Integer aid = adminService.login(adminname, password);
        MySession.setAttr("aid",aid);
        if (aid!=null) {
            return "登录成功";
        }else{
            return "登录失败，账号或者密码错误";
        }
    }

    @RequestMapping("/recentborrow")
    @ResponseBody
    public Map recentborrow(){
        Map map = orderService.rencentBorrow();
        return map;
    }

    @RequestMapping("/bookcount")
    @ResponseBody
    public Map bookcount(){
        Map map = bookService.typeCount();
        return map;
    }

    @RequestMapping("/hotbook")
    @ResponseBody
    public List<Map> hotbook(){
        return bookService.hotestbookTop10();
    }

}
