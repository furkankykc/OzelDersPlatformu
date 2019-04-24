package Utility;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "loginFilter", urlPatterns = {"faces/admin/*"})
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if (SessionUtils.isAdmin() != null
                    && (reqURI.indexOf("/login.xhtml") >= 0
                    || (SessionUtils.isAdmin())
                    || reqURI.indexOf("/frontend/") >= 0
                    || reqURI.contains("javax.faces.resources"))) {
                System.out.println("BEN FİLTRECİYİM FİLTRE YAPARIM");
                chain.doFilter(request, response);

            } else if (ses.getAttribute("userid") != null) {
                 System.out.println("BEN FİLTRECİYİM FİLTRE 2YAPARIM");
                resp.sendRedirect(reqt.getContextPath() + "/faces/index.xhtml");
            } else {
                 System.out.println("BEN FİLTRECİYİM FİLTRE YAPARI3M");
                resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

}
