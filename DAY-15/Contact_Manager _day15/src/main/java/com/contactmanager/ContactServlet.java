package com.contactmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactManager contactManager;
    private Gson gson;
    
    public void init() throws ServletException {
        contactManager = ContactManager.getInstance();
        gson = new Gson();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        
        try {
            if (id != null && !id.isEmpty()) {
                contact contact = contactManager.getContact(id);
                if (contact != null) {
                    out.print(gson.toJson(contact));
                } else {
                    response.setStatus(404);
                    out.print("{\"error\":\"Contact not found\"}");
                }
            } else {
                List<contact> contacts = contactManager.getAllContacts();
                out.print(gson.toJson(contacts));
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        try {
            Map<String, String> params = parseRequestBody(request);
            
            String name = params.get("name");
            String email = params.get("email");
            String phone = params.get("phone");
            String address = params.get("address");
            
            System.out.println("Received - Name: " + name + ", Email: " + email);
            
            if (name == null || name.trim().isEmpty()) {
                response.setStatus(400);
                out.print("{\"error\":\"Name is required\"}");
                return;
            }
            
            contact contact = new contact();
            contact.setName(name);
            contact.setEmail(email != null ? email : "");
            contact.setPhone(phone != null ? phone : "");
            contact.setAddress(address != null ? address : "");
            
            String id = contactManager.addContact(contact);
            
            response.setStatus(201);
            out.print(gson.toJson(contact));
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        
        try {
            if (id == null || id.trim().isEmpty()) {
                response.setStatus(400);
                out.print("{\"error\":\"ID required\"}");
                return;
            }
            
            Map<String, String> params = parseRequestBody(request);
            
            contact contact = new contact();
            contact.setName(params.get("name"));
            contact.setEmail(params.get("email"));
            contact.setPhone(params.get("phone"));
            contact.setAddress(params.get("address"));
            
            boolean updated = contactManager.updateContact(id, contact);
            
            if (updated) {
                out.print(gson.toJson(contact));
            } else {
                response.setStatus(404);
                out.print("{\"error\":\"Contact not found\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        
        try {
            if (id == null || id.trim().isEmpty()) {
                response.setStatus(400);
                out.print("{\"error\":\"ID required\"}");
                return;
            }
            
            boolean deleted = contactManager.deleteContact(id);
            
            if (deleted) {
                out.print("{\"success\":true}");
            } else {
                response.setStatus(404);
                out.print("{\"error\":\"Not found\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    private Map<String, String> parseRequestBody(HttpServletRequest request) throws IOException {
        Map<String, String> params = new HashMap<>();
        BufferedReader reader = request.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        
        String bodyStr = body.toString();
        System.out.println("Raw body: " + bodyStr);
        
        if (bodyStr != null && !bodyStr.isEmpty()) {
            String[] pairs = bodyStr.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    String key = java.net.URLDecoder.decode(keyValue[0], "UTF-8");
                    String value = java.net.URLDecoder.decode(keyValue[1], "UTF-8");
                    params.put(key, value);
                }
            }
        }
        
        return params;
    }
}
