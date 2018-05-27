package chenangel.graduationdesign.service.impl;

import chenangel.graduationdesign.generator.mapper.AdminMapper;
import chenangel.graduationdesign.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean login(String adminname, String password) {
        return adminMapper.check(adminname, password)!=null&&adminMapper.check(adminname, password)!=0;
    }
}
