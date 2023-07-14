package userJsp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import userJsp.connection.SingleConnection;
import userJsp.filter.Filter;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter("/Filter")
public class Filter extends HttpFilter implements javax.servlet.Filter {
    
	private static Connection connection; 
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Filter() {
        super();
      
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			chain.doFilter(request, response);
			connection.commit(); 

		} catch (Exception e) {

			try {
				connection.rollback(); 

			} catch (SQLException e1) {
				e1.printStackTrace();

			}

		}

	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		SingleConnection.getConnection();
	}

}
