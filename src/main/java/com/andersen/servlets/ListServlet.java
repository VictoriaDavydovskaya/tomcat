package com.andersen.servlets;

        import com.andersen.model.User;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.http.HttpServlet;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;

        import java.io.IOException;
        import java.io.PrintWriter;
        import java.util.List;
        import java.util.concurrent.CopyOnWriteArrayList;

public class ListServlet extends HttpServlet {

    private final static String index = "index.jsp";

    private List<User> users;

    @Override
    public void init() throws ServletException {
        users = new CopyOnWriteArrayList<>();
        users.add(new User("Sam", "Smith", 10));
        users.add(new User("John", "Cooper",  37));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //req.setAttribute("users", users);
        //req.getRequestDispatcher(index).forward(req, resp);
        PrintWriter writer = resp.getWriter();
        for (User el: users) {
            writer.println(el.getName() + " " +
                    el.getSurname() + " " + el.getAge());
        }
    }

}