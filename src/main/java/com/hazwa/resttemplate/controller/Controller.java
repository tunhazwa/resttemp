package com.hazwa.resttemplate.controller;

import model.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;


@RestController
public class Controller {

    private RestTemplate restTemplate;

    public Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    public String hello() {
        return "HELLO";
    }

    @GetMapping("/all")
    public String getAll() {
        return restTemplate.getForEntity("http://localhost:8080/ticket/allTicket", String.class).getBody();
    }
    @RequestMapping(value= "/template/products", method= RequestMethod.POST)
    public String createProducts (@RequestBody Product product) {
        HttpHeaders headers= new HttpHeaders();
        headers.setAccept (Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);

        return restTemplate.exchange(
                "http://localhost:8080/ticket/addTicket", HttpMethod.POST,entity,String.class).getBody();

    }
    @RequestMapping(value="/template/products/{id}", method = RequestMethod.DELETE)
    public String deleteProduct (@PathVariable int id){
        HttpHeaders headers =new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity= new HttpEntity<Product>(headers);

        return restTemplate.exchange(
                "http://localhost:8080/ticket/" +id, HttpMethod.DELETE, entity, String.class).getBody();

    }
}
