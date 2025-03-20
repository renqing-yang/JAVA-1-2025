package week4.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week4.project1.exception.ResourceNotFoundException;
import week4.project1.pojo.MyPojo;
import week4.project1.service.MyService;

@RestController
@RequestMapping("/endpoint1")
public class HelloController {

    //field injection, not recommended
//    @Autowired
//    private MyServie myFirstService;

    private final MyService service;

    @Autowired
    public HelloController(@Qualifier("s1") MyService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> getData(@PathVariable("id") String id, @RequestParam(defaultValue = "default p value", required = false) String myP) {
        return new ResponseEntity<>(service.getData(id) + "--- param: " + myP, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MyPojo> createData(@RequestBody MyPojo pojo) {
        return new ResponseEntity<>(pojo, HttpStatus.CREATED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleEx(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.toString(), HttpStatus.NOT_FOUND);
    }
}

