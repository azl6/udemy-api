package com.udemy.gvendas.services;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.domain.Cliente;
import com.udemy.gvendas.exceptions.NotFoundException;
import com.udemy.gvendas.exceptions.SameNameException;
import com.udemy.gvendas.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("O código da cliente informado não existe no cadastro"));
    }

    public Cliente insert(Cliente cliente){
        validarClienteDuplicado(cliente);
        return clienteRepository.save(cliente);
    }

    private void validarClienteDuplicado(Cliente cliente){
        Cliente obj = clienteRepository.findByNome(cliente.getNome());

        if(obj != null && obj.getCodigo() != cliente.getCodigo()){
            throw new SameNameException(String.format("O cliente %s já está cadastrado", obj.getNome()));
        }
    }
}
