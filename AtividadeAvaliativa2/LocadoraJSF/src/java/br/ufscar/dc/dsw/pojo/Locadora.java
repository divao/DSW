package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Locadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private String nome;
    private String email;
    private String senha;
    private String cidade;
    
    @OneToMany(mappedBy = "locadora", fetch = FetchType.LAZY)
    private List<Locacao> locacoes;

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (!(obj instanceof Locadora))
		return false;
	Locadora other = (Locadora) obj;
	return other.nome.equals(this.nome);
    }
    
    @Override
    public String toString() {
        return nome;
    }
}
