package week4.project1.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import week4.project1.service.MyService;

@Service
public class MySecondServiceImpl implements MyService {
    @Override
    public String getData(String id) {
        return "hello from service 2, id = " + id;
    }
}
