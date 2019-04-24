package br.ufscar.dc.dsw.locadora.bean;


public class Locadora {
    
    private String nome, cnpj, email, senha, cidade;

    public Locadora(String cnpj, String nome, String email, String senha, String cidade) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    
    
}
