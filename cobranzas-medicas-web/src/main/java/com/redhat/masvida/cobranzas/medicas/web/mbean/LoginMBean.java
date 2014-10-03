package com.redhat.masvida.cobranzas.medicas.web.mbean;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean
@SessionScoped
public class LoginMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7367318979370070426L;
	private static final Logger LOG = LoggerFactory
			.getLogger(LoginMBean.class);

	private String username;
	private String password;
	private boolean isAdminHO = false;
	private boolean isUserHO = false;
	
	//Getters and Setters
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
	
	public boolean isAdminHO() {
		return isAdminHO;
	}
	public void setAdminHO(boolean isAdminHO) {
		this.isAdminHO = isAdminHO;
	}
	public boolean isUserHO() {
		return isUserHO;
	}
	public void setUserHO(boolean isUserHO) {
		this.isUserHO = isUserHO;
	}
	
	public String login() {
		//Request para Iniciar Sesión
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		try {
			String role = "";
			request.login(username, password);
			
			//Seteando los roles
			if(request.isUserInRole("AdminHO")){
				isAdminHO = true;
				role="AdminHO";
			}
			
			if(request.isUserInRole("UserHO")){
				isUserHO = true;
				role="UserHO";
			}
			
			Principal principal = request.getUserPrincipal();
			
			
			LOG.info("Usuario: "+principal.getName()+" con rol: "+role+" iniciando sesión...");
		    
			
			return "/index.xhtml?faces-redirect=true";
			
		} catch (ServletException e) {
			// TODO Customizar excepción usuario no existente.
			e.printStackTrace();
		}
		return "/error.xhtml?faces-redirect=true";
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}
	
}