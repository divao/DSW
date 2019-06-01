
package br.ufscar.dc.dsw.locadora.bean;

import java.util.Date;

public class Locacao {
    private String cpf;
    private String cnpj;
    private Date data;
    private int horario;

    public Locacao() {
    }

    
    
    public Locacao(String cpf, String cnpj, Date data, int horario) {
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.data = data;
        this.horario = horario;
    }
    
    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }
    
    
}
