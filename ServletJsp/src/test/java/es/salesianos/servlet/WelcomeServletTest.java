package es.salesianos.servlet;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import es.salesianos.service.Service;

@RunWith(MockitoJUnitRunner.class)
public class WelcomeServletTest {

	@Spy
	MockHttpRequest req;		

	@Mock
	HttpServletResponse resp;

	Service service = new Service();
	
	MockedWelcomeServlet servlet = new MockedWelcomeServlet();
	
	
	@Before
	public void setup() {
		servlet.setService(service);
		Mockito.when(req.getParameter("dob")).thenReturn("2000-12-10");
		Mockito.when(req.getParameter("name")).thenReturn("john doe");
	}
	
	@Test
	public void testDoPost() throws ServletException, IOException {
		servlet.doPost(req, resp);
		assertEquals("19", req.getAttribute("age"));
	}
	
	class MockedWelcomeServlet extends WelcomeServlet{
		
		protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		}
		
	}
	
	abstract class MockHttpRequest implements HttpServletRequest{
		private final Map<String, Object> attribute = new HashMap<String, Object>();


	    public Object getAttribute(String name)
	    {
	    	return attribute.get(name);
	    }
	    
	    public void setAttribute(String key, Object value)
	    {
	        attribute.put(key,value);
	    }
	
	}

}
