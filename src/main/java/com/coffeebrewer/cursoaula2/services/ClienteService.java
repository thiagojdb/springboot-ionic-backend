package com.coffeebrewer.cursoaula2.services;

import com.coffeebrewer.cursoaula2.domain.Cidade;
import com.coffeebrewer.cursoaula2.domain.Cliente;
import com.coffeebrewer.cursoaula2.domain.Endereco;
import com.coffeebrewer.cursoaula2.domain.enums.TipoCliente;
import com.coffeebrewer.cursoaula2.dto.ClienteDTO;
import com.coffeebrewer.cursoaula2.dto.ClienteNewDTO;
import com.coffeebrewer.cursoaula2.repositories.ClienteRepository;
import com.coffeebrewer.cursoaula2.repositories.EnderecoRepository;
import com.coffeebrewer.cursoaula2.services.exceptions.DataIntegrityException;
import com.coffeebrewer.cursoaula2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id){

        Optional<Cliente> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException( "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;

    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }


    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
        }
    }

    public List<Cliente> findAll(){
        return repo.findAll();

    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }


    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public Cliente fromDTO(ClienteNewDTO objDTO){
       Cliente cli1 = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
       Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
       Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli1,cid);
       cli1.getEnderecos().add(end);
       cli1.getTelefones().add(objDTO.getTelefone1());
       if (objDTO.getTelefone2() != null){
           cli1.getTelefones().add(objDTO.getTelefone2());
       }
       if (objDTO.getTelefone3() != null){
           cli1.getTelefones().add(objDTO.getTelefone3());
       }



       return cli1;
    }

    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null );
    }
}
