package com.coffeebrewer.cursoaula2.domain.enums;

public enum TipoCliente {

    PESSOAFISICA(0, "Pessoa Física"),
    PESSOAJURIDICA(1, "Pessoa Jurídica");

    private Integer cod;
    private String desc;

    TipoCliente(Integer codigo, String descricao){
        this.cod = codigo;
        this.desc = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDesc(){
        return desc;
    }

    public static TipoCliente toEnum(Integer cod){
        if (cod == null){
            return null;
        }for (TipoCliente  x: TipoCliente.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }throw new IllegalArgumentException("Id inválido");
    }
}
