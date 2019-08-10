package com.kobobook.www.admin.web;

import com.kobobook.www.admin.service.DeliveryService;
import com.kobobook.www.admin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders")
@AllArgsConstructor
public class OrderRestController {

    private DeliveryService deliveryService;

    @PostMapping(value = "/start",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> startOrder(Integer[] orderIdList) {
        deliveryService.startOrder(orderIdList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
