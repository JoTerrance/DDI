package es.salesianos.servlet;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import es.salesianos.model.User;
import es.salesianos.repository.Repository;

public class EditServlet extends HttpServlet {
	Repository repo = new Repository();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("id");
		User userFormulario = new User();
		userFormulario.setName(name);
		User search = repo.search(userFormulario);
		req.setAttribute("editableUser", search);
		redirect(req, resp);
	}
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/edit.jsp");
		dispatcher.forward(req,resp);
	}
	
}
