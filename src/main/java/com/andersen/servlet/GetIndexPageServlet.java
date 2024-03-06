package com.andersen.servlet;

import com.andersen.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GetIndexPageServlet extends HttpServlet {

    private final static String index = "index.jsp";

    private List<User> users;

    @Override
    public void init() throws ServletException {
        users = new CopyOnWriteArrayList<User>();
        users.add(new User("Sam", "Smith", 10));
        users.add(new User("John", "Cooper",  37));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("users", users);
        req.getRequestDispatcher(index).forward(req, resp);
    }

}