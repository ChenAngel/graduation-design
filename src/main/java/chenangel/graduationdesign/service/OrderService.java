package chenangel.graduationdesign.service;

import chenangel.graduationdesign.generator.model.Order;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface OrderService {
    public String  yuyue(Integer rid,Integer bid);
    public List<Order> saerchByRid(Integer rid);

    public List<Order> searchNowBorrow(Integer rid);
}
