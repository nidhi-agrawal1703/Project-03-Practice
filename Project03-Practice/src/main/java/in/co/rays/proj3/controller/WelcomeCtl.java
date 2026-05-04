package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.util.ServletUtility;

/**
 * @author Nidhi
 *
 */
@WebServlet(name="WelcomeCtl",urlPatterns= {"/WelcomeCtl"})
public class WelcomeCtl extends BaseCtl{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}

}
