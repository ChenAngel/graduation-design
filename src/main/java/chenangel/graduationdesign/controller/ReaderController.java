package chenangel.graduationdesign.controller;

import chenangel.graduationdesign.admin.MySession;
import chenangel.graduationdesign.generator.model.BorrowHistory;
import chenangel.graduationdesign.generator.model.Reader;
import chenangel.graduationdesign.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import untils.ScriptUtil;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @RequestMapping(value = "/{readerid}/{password}/login", method = RequestMethod.POST)
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
    public List<BorrowHistory> searchforhistory() throws Exception{
        List<BorrowHistory> borrowHistories = ScriptUtil.borrowHistoriyGetBookname(readerService.searchHistoryById((Integer)MySession.getAttr("rid")));
        return borrowHistories;
    }
}
