package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.PapelDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Locadora;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@SessionScoped
public class LocadoraBean {

    private Locadora locadora;
    private String cidade;

    public String lista() {
        return "admin/indexLocadora.xhtml";
    }

    public String lista2() {
        return "anonymous/lista.xhtml";
    }

    public String cadastra() {
        locadora = new Locadora();
        return "formLocadora.xhtml";
    }

    public String edita(Long id) {
        LocadoraDAO dao = new LocadoraDAO();
        locadora = dao.get(id);
        return "formLocadora.xhtml";
    }

    public String salva() {
        LocadoraDAO dao = new LocadoraDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PapelDAO papelDAO = new PapelDAO();
        Usuario u = new Usuario();
        u.setEmail(locadora.getEmail());
        u.setSenha(new BCryptPasswordEncoder().encode(locadora.getSenha()));
        u.setAtivo(true);

        Papel p = papelDAO.get(Long.valueOf(3));

        if (locadora.getId() == null) {
            dao.save(locadora);
            usuarioDAO.save(u);
            u.getPapel().add(p);
            usuarioDAO.update(u);
        } else {
            dao.update(locadora);
            usuarioDAO.save(u);
            u.getPapel().add(p);
            usuarioDAO.update(u);
        }
        return "indexLocadora.xhtml";
    }

    public String delete(Locadora locadora) {
        LocadoraDAO dao = new LocadoraDAO();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        List<Usuario> usuarios = usuarioDAO.getAllPorEmail(locadora.getEmail());

        for (Usuario u : usuarios) {
            u.setAtivo(false);
            usuarioDAO.update(u);
        }
        try{
            dao.delete(locadora);
        }catch(PersistenceException e){
            mensagemErro("Não foi possível remover essa locadora! Há referências associadas a ela!");
            e.printStackTrace();
        }
        
        return "indexLocadora.xhtml";
    }
    public void mensagemErro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(":form:msgErro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensagem));
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Locadora> getlocadoras() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        return dao.getAll();
    }

    public List<String> getCidades() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        List<Locadora> locadoras = dao.getAll();
        ArrayList<String> cidades = new ArrayList();
        int i = 0;
        for (Locadora l : locadoras) {
            if (!cidades.contains(l.getCidade())) {
                cidades.add(l.getCidade());
            }
        }
        return cidades;
    }

    public List<Locadora> getPorCidade() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        System.out.println(cidade);
        return dao.getPorCidade(cidade);
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Locadora getLocadora() {
        return locadora;
    }

    public void validarSenha(FacesContext context,
            UIComponent toValidate,
            Object obj) {

        String value = (String) obj;

        if (value.length() < 8) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A senha deve ter no mínimo 8 dígitos!!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }

    public void validarCNPJ(FacesContext context,
            UIComponent toValidate,
            Object obj) {

        LocadoraDAO dao = new LocadoraDAO();

        String value = (String) obj;
        List<Locadora> loc = dao.getAll();
        List<String> cnpjs = new ArrayList<>();

        for(Locadora c : loc){
            cnpjs.add(c.getCnpj());
        }

        if (!isCNPJ(value.replace(".", "").replace("-", "").replace("/", ""))) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Digite um CNPJ válido!");
            context.addMessage(toValidate.getClientId(context), message);
        } else if (cnpjs.contains(value) && (locadora.getId() == null || !locadora.getCnpj().equals(value))) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "CNPJ já cadastrado!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }

    public void validarEmail(FacesContext context,
            UIComponent toValidate,
            Object obj) {

        LocadoraDAO dao = new LocadoraDAO();
        String value = (String) obj;
        List<Locadora> loc = dao.getAll();
        List<String> emails = new ArrayList<>();

        for(Locadora c: loc){
            emails.add(c.getEmail());
        }

        if (!isValidEmailAddressRegex(value)) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Digite um email válido!");
            context.addMessage(toValidate.getClientId(context), message);
        } else if (emails.contains(value) && (locadora.getId() == null || !locadora.getEmail().equals(value))) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email já cadastrado!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }

    public static boolean isCNPJ(String CNPJ) {
// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

// Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}
