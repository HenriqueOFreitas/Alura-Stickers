package br.com.alura.linguagensapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping(value="/linguagens")
    public List<Linguagem> obterLinguagens() {
        List<Linguagem> linguagens = repositorio.findAll();
        return linguagens;
    }

    @PostMapping(value = "/linguagens")
    public Linguagem cadastrarLinguagem(@RequestBody Linguagem linguagem) {
        Linguagem linguagemSalva = repositorio.save(linguagem);
        return linguagemSalva;
    }

    @PutMapping(value = "/linguagem/{id}")
    public ResponseEntity<Linguagem> atualizarLinguagem(@PathVariable(value="id") String id, @RequestBody Linguagem novaLinguagem) {
        Optional<Linguagem> linguagemAtual = repositorio.findById(id);
        if(linguagemAtual.isPresent()) {
            Linguagem linguagem = linguagemAtual.get();
            linguagem.setTitle(novaLinguagem.getTitle());
            linguagem.setImage(novaLinguagem.getImage());
            linguagem.setRanking(novaLinguagem.getRanking());
            return new ResponseEntity<>(linguagem, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
