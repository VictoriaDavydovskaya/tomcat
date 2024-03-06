package com.andersen.servlets;

        import com.andersen.model.User;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.List;
        import java.util.concurrent.CopyOnWriteArrayList;

public class ListServlet extends HttpServlet {

    private final static String index = "test_list.jsp";

    private List<User> users;

    @Override
    public void init() {
        users = new CopyOnWriteArrayList<>();
        users.add(new User("Sam", "Smith", 10));
        users.add(new User("John", "Cooper",  37));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //req.setAttribute("users", users);
        req.getRequestDispatcher(index).forward(req, resp);
    }

}