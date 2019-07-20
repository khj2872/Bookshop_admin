package com.kobobook.www.admin.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/test")
@AllArgsConstructor
@Slf4j
public class RestTestController {

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> test(@PathVariable("id") String id) {

        log.info("id : " + id);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("result", "succeses");
        returnMap.put("호호호", "sjsjsjfkfkfk");

        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

}
