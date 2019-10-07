/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 738634
 */
public class ShoppingListServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       getStuff(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getStuff(request, response);
    }
    
    protected void getStuff(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("valid");
        ArrayList<String> items = (ArrayList)session.getAttribute("list");
        String action = request.getParameter("action");

        if(action != null)
        {
            if(action.equals("register"))
            {
                String userName = request.getParameter("username");
                if(userName == null||userName.equals(""))
                {
                    response.sendRedirect("/shoppinglist");
                    return;
                }
                else
                {
                   session.setAttribute("valid", userName);
                   response.sendRedirect("/shoppinglist");
                   return;
                }
            }
            else if(action.equals("addItem"))
            {
                if(items == null)
                {
                    items = new ArrayList<>();
                }
                items.add(request.getParameter("itemToAdd"));
                session.setAttribute("list", items);
                response.sendRedirect("/shoppinglist");
                return;
            }
            else if(action.equals("delete"))
            {
                String button = request.getParameter("addedItems");
                for(int i = 0; i < items.size(); i++)
                {
                    if(items.get(i).equals(button))
                    {
                        items.remove(button);
                    }
                }
                response.sendRedirect("/shoppinglist");
                return;
            }
            else if(action.equals("logout"))
            {
                session.invalidate();
                response.sendRedirect("/shoppinglist");
                return;
            }
        }
        
                
        if(user != null)
        {
             request.setAttribute("username", user);
             getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
             return;
        }
      
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);

    }
    
}
