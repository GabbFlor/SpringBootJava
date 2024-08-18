package com.apitopi.demo.controller;

import com.apitopi.demo.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

// indica que essa classe é um controlador
@RestController
// cria a rota "/clientes"
@RequestMapping("/clientes")

public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();
    // Cria um contador
    private AtomicLong counter = new AtomicLong();

    // Cria a requisição de post
    @PostMapping
    // Formata os dados pronto para sair no front-end, com status code e as informações em .json
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente)
    {
        if (cliente.getEmail() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            cliente.setId(counter.incrementAndGet());
            clientes.add(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        }
    }

    // Cria a requisição de retornar os clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clientes;
    }

    // Cria a requisição para puxar os usuários pelo id na url
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterCliente(@PathVariable Long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // cria a requisição para editar os clientes
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        for(Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                cliente.setNome(clienteAtualizado.getNome());
                cliente.setEmail(clienteAtualizado.getEmail());
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                clientes.remove(cliente);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
