package cn.itcast.web.servlet;


import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String checkCode = request.getParameter("checkCode");

        String checkCode_session = (String) request.getSession().getAttribute("checkCode_session");
        request.getSession().removeAttribute("checkCode_session");
        if (!checkCode_session.equalsIgnoreCase(checkCode)) {
            request.setAttribute("login_msg", "验证码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();

        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(loginUser);

        UserService service = new UserServiceImpl();
        User login_user = service.login(loginUser);
        if (login_user != null){
            request.getSession().setAttribute("user" , login_user);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else {
            request.setAttribute("login_msg", "用户名或密码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
