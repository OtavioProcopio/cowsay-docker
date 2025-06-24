package com.example.demo.service;


import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.demo.model.Mensagem;
import com.example.demo.repository.MensagemRepository;

@Service
public class CowsayService {
    private final MensagemRepository repo;

    public CowsayService(MensagemRepository repo){
        this.repo = repo;
    }

    public void salvarMensagem(String remetenteId, String conteudo){
        repo.save(new Mensagem(null, remetenteId, conteudo));
    }

    public String buscaMensagemAleatoria(String remetenteId){
        List<Mensagem> mensagens = repo.findByRemetenteId(remetenteId);
        if(mensagens.isEmpty()){
            return "Nenhuma mensagem encontrada para o  remetente";
        }
        Mensagem m = mensagens.get(new Random().nextInt(mensagens.size()));
        return cowsay(m.getConteudo());

    }

    private String cowsay(String conteudo) {
        return " _______\n" +
               "< " + conteudo + " >\n" +
               " -------\n" +
               "        \\   ^__^\n" +
               "         \\  (oo)\\_______\n" +
               "            (__)\\       )\\/\\\n" +
               "                ||----w |\n" +
               "                ||     ||";
    }
}
