package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CowsayService;

@RestController
@RequestMapping("/cowsay")
public class CowsayController {

    private final CowsayService service;

    public CowsayController(CowsayService service){
        this.service = service;
    }

    @PostMapping
    public String postMensagem(@RequestBody Map<String, String> body){
        String id = body.get("id");
        String msg= body.get("msg");
        service.salvarMensagem(id, msg);
        return "Mensagem Salva!";
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getMensagem(@PathVariable String id){
        return service.buscaMensagemAleatoria(id);
    }
}
