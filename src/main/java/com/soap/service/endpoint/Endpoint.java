package com.soap.service.endpoint;

import com.fowobi.spring.soap.api.bookservice.*;
import com.soap.service.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@org.springframework.ws.server.endpoint.annotation.Endpoint
public class Endpoint {

    private static final String NAMESPACE= "http://www.fowobi.com/spring/soap/api/bookservice";

    @Autowired
    private BookService service;

    @PayloadRoot(namespace = NAMESPACE, localPart = "CreateBookRequest")
    @ResponsePayload
    public CreateBookResponse save(@RequestPayload CreateBookRequest request){

        return service.save(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetBookByIdRequest")
    @ResponsePayload
    public GetBookByIdResponse save(@RequestPayload GetBookByIdRequest request){

        return service.findById(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetBooksRequest")
    @ResponsePayload
    public GetBooksResponse save(@RequestPayload GetBooksRequest request){

        return service.getAllBooks(request);
    }

}
