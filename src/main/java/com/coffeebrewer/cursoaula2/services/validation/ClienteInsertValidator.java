package com.coffeebrewer.cursoaula2.services.validation;

import com.coffeebrewer.cursoaula2.domain.Cliente;
import com.coffeebrewer.cursoaula2.domain.enums.TipoCliente;
import com.coffeebrewer.cursoaula2.dto.ClienteNewDTO;
import com.coffeebrewer.cursoaula2.repositories.ClienteRepository;
import com.coffeebrewer.cursoaula2.resources.exception.FieldMessage;
import com.coffeebrewer.cursoaula2.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo() == TipoCliente.PESSOAFISICA.getCod() && !BR.isValidSsn(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("CpfOuCnpj", "CPF inválido"));
        }
        if (objDto.getTipo() == TipoCliente.PESSOAJURIDICA.getCod() && !BR.isValidTfn(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));
        }

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email","Email já existente"));

        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}