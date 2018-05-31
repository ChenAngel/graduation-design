package chenangel.graduationdesign.controller;

import chenangel.graduationdesign.admin.MySession;
import chenangel.graduationdesign.generator.mapper.BookMapper;
import chenangel.graduationdesign.generator.model.Book;
import chenangel.graduationdesign.generator.model.BorrowHistory;
import chenangel.graduationdesign.generator.model.Order;
import chenangel.graduationdesign.generator.model.Reader;
import chenangel.graduationdesign.service.BookService;
import chenangel.graduationdesign.service.OrderService;
import chenangel.graduationdesign.service.ReaderService;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import untils.FileUtil;
import untils.ScriptUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;

    //spring mvc设置命名空间的注解
    @RequestMapping(value = "/{readerid}/{password}/login", method = RequestMethod.POST)
    //spring mvc设置以json形式返回返回值的注解
    @ResponseBody
    public String login(@PathVariable("readerid") String readerid,//取url路径中的参数
                        @PathVariable("password") String password) throws Exception{
        Reader reader = readerService.login(readerid, password);//使用service层的登录方法，完成对数据库等逐层的迭代操作
        if (reader!=null) {
            MySession.setAttr("rid",reader.getId());//以session保存用户登录状态
            MySession.setAttr("readerid",reader.getReaderid());
            return "登录成功";//判定结果返回
        }else {
            return "用户名或密码错误";
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

    @RequestMapping(value = "/{password1}/{password2}/resetps", method = RequestMethod.POST)
    @ResponseBody
    public String resetps(@PathVariable("password1") String password1,
                          @PathVariable("password2") String password2) throws Exception{
        Integer rid = (Integer) MySession.getAttr("rid");
        if (!password1.equals(password2)) {
            return "两次输入的密码不一致";
        }
        boolean sign = readerService.resetps(rid,password1);
        if (sign) {
            return "修改成功";
        }else{
            return "修改失败，未知错误";
        }
    }

    @RequestMapping(value = "/searchborrowing", method = RequestMethod.POST)
    @ResponseBody
    public List<Order> borrowing() throws Exception{
        List<Order> orders = orderService.searchNowBorrow((Integer) MySession.getAttr("rid"));
        return orders;
    }

    @RequestMapping(value = "/{field}/{value}/fieldsearch", method = RequestMethod.POST)
    @ResponseBody
    public Map fieldsearch(@PathVariable("field")String field,
                           @PathVariable("value")String value) throws Exception{
        Map result = new HashMap();
        List<Reader> readers = readerService.fieldSearch(field, value);
        result.put("readerlist",readers);
        result.put("size",readers.size());
        return result;
    }

    @RequestMapping(value = "/changeinfo", method = RequestMethod.POST)
    @ResponseBody
    public String changeinfo(@RequestParam("password")String password,
                             @RequestParam("readerid")String readerid,
                             @RequestParam("readername")String readername,
                             @RequestParam("readerclass")String readerclass,
                             @RequestParam("tel")String tel,
                             @RequestParam("sex")String sex,
                             @RequestParam("id")Integer id,
                             @RequestParam("birthday")String birthday,
                             @RequestParam("identification")String identification) throws Exception{
        boolean sign = readerService.changeinfo(id,readerid,password,readername,readerclass,sex,birthday,identification,tel);
        if (sign) {
            return "修改成功";
        }else{
            return "修改失败，未知错误";
        }
    }

    @RequestMapping(value = "/{id}/searchbyid", method = RequestMethod.POST)
    @ResponseBody
    public Reader searchbyid(@PathVariable("id")Integer id) throws Exception{
        return readerService.searchById(id);
    }

    @RequestMapping(value = "/returnback", method = RequestMethod.POST)
    @ResponseBody
    public String returnbook(@RequestParam("bid")Integer bid,
                             @RequestParam("rid")Integer rid) throws Exception{
        Integer aid = (Integer) MySession.getAttr("aid");
        Reader reader = readerService.searchById(rid);
        boolean sign = readerService.returnbook(bid,reader.getId(),aid);
        if (sign) {
            return "归还成功";
        }else{
            return "归还失败，未知错误";
        }
    }

    @RequestMapping(value = "/saerchorderbyrid", method = RequestMethod.POST)
    @ResponseBody
    public List<BorrowHistory> searchbyrid(@RequestParam("rid")Integer rid) throws Exception{
        List<BorrowHistory> borrowHistories = readerService.searchhistorybyreaderid(rid);
        return borrowHistories;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public String logout() throws Exception{
        MySession.removeAttr("rid");
        return "注销成功";
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadWarcFile(@RequestParam("pic") MultipartFile file,
                                 HttpServletRequest request){

        String contentType = file.getContentType();
        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        String originalname = file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("uploadfile/");
        boolean sign = false;
        try {
            FileUtil.uploadFile(file.getBytes(),filePath, fileName);
            List<Reader> readers = FileUtil.addreaderbyexcel(filePath + "/" + fileName);
            sign = readerService.addReader(readers);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail to upload";
        }

        //返回json
        if (sign) {
            return "上传成功且保存完毕";
        }else{
            return "保存失败，未知错误";
        }

    }
}
