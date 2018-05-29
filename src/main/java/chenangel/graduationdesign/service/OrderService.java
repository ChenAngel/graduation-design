package chenangel.graduationdesign.service;

import chenangel.graduationdesign.generator.model.Order;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public String  yuyue(Integer rid,Integer bid);
    public List<Order> saerchByRid(Integer rid);

    public List<Order> searchNowBorrow(Integer rid);

    public Map rencentBorrow();

    public boolean delByUuid(String uuid);

    public Order findByRidandBookname(Integer rid,String bookname);
}
