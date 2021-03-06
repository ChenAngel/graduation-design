package chenangel.graduationdesign.service.impl;

import chenangel.graduationdesign.generator.mapper.BookMapper;
import chenangel.graduationdesign.generator.mapper.OrderMapper;
import chenangel.graduationdesign.generator.model.Book;
import chenangel.graduationdesign.generator.model.Order;
import chenangel.graduationdesign.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BookMapper bookMapper;

    /**
     * 传入读者id和书本id，若书本剩余量大于0，则允许其进入预约队列，反之返回null
     * 两个返回null的情况，一个是书本余量不足，一个是插入操作失败
     * @param rid
     * @param bid
     * @return
     */
    @Override
    public String yuyue(Integer rid, Integer bid) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String uuid = UUID.randomUUID().toString();
        Book book= bookMapper.selectbyid(bid);
        String bookname = book.getBookname();
        if (book.getNowaccount()<=0) {
            return null;
        }else{
            int sign = orderMapper.insertorder(uuid, simpleDateFormat.format(new Date()),bookname,rid);
            return sign==0?null:uuid;
        }

    }

    @Override
    public List<Order> saerchByRid(Integer rid) {
        return orderMapper.selectbyrid(rid);
    }

    /**
     * 搜索order表，寻找对应的rid的ok状态
     * @param rid
     * @return
     */
    @Override
    public List<Order> searchNowBorrow(Integer rid) {
        List<Order> orders = orderMapper.selectbyidandstatus(rid);
        if (orders!=null) {
            for (Order order:orders) {
                String addtime = order.getAddtime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                try {
                    Date adddate = simpleDateFormat.parse(addtime);
                    calendar.setTime(adddate);
                    calendar.add(Calendar.MONTH,+1);
                    Date returndate = calendar.getTime();
                    order.setReturntime(simpleDateFormat.format(returndate));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return orders;
        }
        return null;
    }

    @Override
    public Map rencentBorrow() {
        List<Map> mapList = orderMapper.selectrecentborrow();
        Map result = new HashMap();
        List<String> dateList = new LinkedList<>();
        List<Long> totalList = new LinkedList<>();
        for (Map temmap:mapList) {
            dateList.add((String) temmap.get("time"));
            totalList.add((Long) temmap.get("total"));
        }
        result.put("datelist",dateList);
        result.put("countlist",totalList);
        return result;
    }

    @Override
    public boolean delByUuid(String uuid) {
        return orderMapper.delbyuuid(uuid)==1;
    }

    @Override
    public Order findByRidandBookname(Integer rid, String bookname) {
        return orderMapper.selectbyridbookname(rid, bookname);
    }
}
