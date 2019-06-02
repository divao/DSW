package br.ufscar.dc.dsw.bean;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.PapelDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@SessionScoped
public class ClienteBean {

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public String lista() {
        return "admin/indexCliente.xhtml";
    }

    public String cadastra() {
        cliente = new Cliente();
        return "formCliente.xhtml";
    }

    public String edita(Long id) {
        ClienteDAO dao = new ClienteDAO();
        cliente = dao.get(id);
        return "formCliente.xhtml";
    }

    public String salva() {
        ClienteDAO dao = new ClienteDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PapelDAO papelDAO = new PapelDAO();
        Usuario u = new Usuario();
        u.setEmail(cliente.getEmail());
        u.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
        u.setAtivo(true);
        Papel p = papelDAO.get(Long.valueOf(2));

        if (cliente.getId() == null) {
            dao.save(cliente);
            usuarioDAO.save(u);
            u.getPapel().add(p);
            usuarioDAO.update(u);

        } else {
            dao.update(cliente);
            List<Usuario> usuario = usuarioDAO.getAllPorEmail(cliente.getEmail());
            if (!usuario.isEmpty()) {
                usuario.get(0).setEmail(cliente.getEmail());
                usuario.get(0).setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
                usuarioDAO.update(usuario.get(0));
            } else {
                usuarioDAO.save(u);
                u.getPapel().add(p);
                usuarioDAO.update(u);
            }
        }
        return "indexCliente.xhtml";
    }

    public String delete(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        List<Usuario> usuarios = usuarioDAO.getAllPorEmail(cliente.getEmail());

        for (Usuario u : usuarios) {
            u.setAtivo(false);
            usuarioDAO.update(u);
        }

        try {
            dao.delete(cliente);
        } catch (PersistenceException e) {
            mensagemErro("Não foi possível remover esse cliente! Há referências associadas a ele!");
            e.printStackTrace();
        }
        return "indexCliente.xhtml";
    }

    public void mensagemErro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(":form:msgErro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensagem));
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Cliente> getClientes() throws SQLException {
        ClienteDAO dao = new ClienteDAO();
        return dao.getAll();
    }

    public void validarEmail(FacesContext context,
            UIComponent toValidate,
            Object obj) {

        ClienteDAO dao = new ClienteDAO();
        String value = (String) obj;
        List<Cliente> cli = dao.getAll();
        List<String> emails = new ArrayList<>();

        cli.forEach((c) -> {
            emails.add(c.getEmail());
        });

        if (!isValidEmailAddressRegex(value)) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Digite um email válido!");
            context.addMessage(toValidate.getClientId(context), message);
        } else if (emails.contains(value) && (cliente.getId() == null || !cliente.getEmail().equals(value))) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email já cadastrado!");
            context.addMessage(toValidate.getClientId(context), message);
        }
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

    public void validarCPF(FacesContext context,
            UIComponent toValidate,
            Object obj) {

        ClienteDAO dao = new ClienteDAO();

        String value = (String) obj;
        List<Cliente> cli = dao.getAll();
        List<String> cpfs = new ArrayList<>();

        cli.forEach((c) -> {
            cpfs.add(c.getCpf());
        });

        

        if (!isCPF(value.replace(".", "").replace("-", ""))) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Digite um CPF válido!");
            context.addMessage(toValidate.getClientId(context), message);
        } else if (cpfs.contains(value) && (cliente.getId() == null || !cliente.getCpf().equals(value))) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "CPF já cadastrado!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }

    public void validarDataNascimento(FacesContext context,
            UIComponent toValidate,
            Object obj) {

        String dataTexto = (String) obj;

        Date data = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            format.setLenient(false);
            data = format.parse(dataTexto);

            if (data.after(new Date())) {
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Data Futura!");
                context.addMessage(toValidate.getClientId(context), message);
            }

        } catch (ParseException e) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Data Inválida!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }

    public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
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
