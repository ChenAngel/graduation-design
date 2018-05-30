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
import untils.ScriptUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String searchbyid(@RequestParam("bid")Integer bid,
                             @RequestParam("readerid")String readerid) throws Exception{
        Integer aid = (Integer) MySession.getAttr("aid");
        Reader reader = readerService.searchInfoById(readerid);
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

    @RequestMapping("/addreaderbyexcel")
    @ResponseBody
    public String addreaderbyexcel(@RequestParam("")){
        jxl.Workbook readwb = null;
        try
        {
            //构建Workbook对象, 只读Workbook对象
            //直接从本地文件创建Workbook
            InputStream instream = new FileInputStream("F:/红楼人物.xls");
            readwb = Workbook.getWorkbook(instream);
            //Sheet的下标是从0开始
            //获取第一张Sheet表
            Sheet readsheet = readwb.getSheet(0);
            //获取Sheet表中所包含的总列数
            int rsColumns = readsheet.getColumns();
            //获取Sheet表中所包含的总行数
            int rsRows = readsheet.getRows();
            //获取指定单元格的对象引用
            for (int i = 0; i < rsRows; i++) {
                for (int j = 0; j < rsColumns; j++) {
                    Cell cell = readsheet.getCell(j, i);
                    System.out.print(cell.getContents() + " ");
                }
                System.out.println();
            }
            //利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄
            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File(
                    "F:/红楼人物1.xls"), readwb);
            //读取第一张工作表
            jxl.write.WritableSheet ws = wwb.getSheet(0);
            //获得第一个单元格对象
            jxl.write.WritableCell wc = ws.getWritableCell(0, 0);
            //判断单元格的类型, 做出相应的转化
            if (wc.getType() == CellType.LABEL) {
                Label l = (Label) wc;
                l.setString("新姓名");
            }
            //写入Excel对象
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readwb.close();
        }
    }
}
