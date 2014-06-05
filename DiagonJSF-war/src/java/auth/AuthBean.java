/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import entity.Usuario;
import dao.UsuarioFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gonzalo
 */
@Named(value = "authBean")
@RequestScoped
public class AuthBean {
    @EJB
    private UsuarioFacade usuarioFacade;
    private String username, password;

    /**
     * Creates a new instance of AuthBean
     */
    public AuthBean() {
    }
    
    public String Login(){
        Usuario user = new Usuario();
        if(!username.isEmpty() && !password.isEmpty()){
            user = usuarioFacade.findUsuarioByDNI(username);
            if(user != null && user.getPassword().equals(password)){
                /*** El login es correcto, doy acceso ***/
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.getExternalContext().getSessionMap().put("usuario", username).put("rol", "1111");
                return "index.xhtml";
            }
        }
        return "login.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
