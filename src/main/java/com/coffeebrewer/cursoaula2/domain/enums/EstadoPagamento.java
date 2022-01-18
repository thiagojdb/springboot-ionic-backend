package com.coffeebrewer.cursoaula2.domain.enums;

public enum EstadoPagamento {
    PENDENTE(0, "Pagamento Pendente "),
    QUITADO(1, "Pagamento Quitado"),
    CANCELADO(2, "Pagamento Cancelado");

    private Integer cod;
    private String desc;

    EstadoPagamento(Integer cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }

    public static EstadoPagamento toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (EstadoPagamento x : EstadoPagamento.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido");
    }


}