package br.com.superacaobikes.admin.domain.enums;

public enum TipoPlano {
    DEBITO(1, "Débito"),
    CREDITO(2, "Crédito");

    private Integer cod;
    private String descricao;

    TipoPlano(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static TipoPlano toEnum(Integer cod) {
        if (cod == null) return null;

        for (TipoPlano x : TipoPlano.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Código inválido" + cod);
    }
}


