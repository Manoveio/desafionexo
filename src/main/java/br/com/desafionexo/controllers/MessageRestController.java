package br.com.desafionexo.controllers;



import br.com.desafionexo.model.MessageVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;


@RestController
public class MessageRestController {


    @GET
    @RequestMapping("/home")
    @Produces("application/json")
    public String home(){
        return "Hello!";
    }



    @POST
    @RequestMapping("/message")
    @Produces("application/json")
    @Consumes("application/json")
    public String message(MessageVO messageVO) {

        return null;
    }

    @POST
    @RequestMapping("/message/{0}")
    @Produces("application/json")
    public MessageVO message(Integer id) {

        return new MessageVO();
    }
/*
    @PUT
    @RequestMapping("/message/{0}")
    @Consumes("application/json")
    public Integer messagePut(MessageVO messageVO) {

         return 0;
    }*/





}
