package week4.project1.service.impl;

import org.springframework.stereotype.Service;
import week4.project1.exception.ResourceNotFoundException;
import week4.project1.service.MyService;

@Service("s1")
public class MyFirstServiceImpl implements MyService {

    @Override
    public String getData(String id) {
        if(id.equals("1")) {
            throw new ResourceNotFoundException("error message...");
        }
        return "hello from service 1, id = " + id;
    }
}
