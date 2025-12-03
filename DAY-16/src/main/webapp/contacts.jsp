<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.contactmanager.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Manager</title>
   <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <header>
        <div class="header-content">
            <h1><i class="fas fa-address-book"></i> Contact Manager</h1>
            <p>Organize and manage your contacts efficiently</p>
        </div>
    </header>

    <div class="container">
        <%
            String message = (String) request.getAttribute("message");
            String messageType = (String) request.getAttribute("messageType");
            Contact editContact = (Contact) request.getAttribute("editContact");
            boolean isEditing = editContact != null;
            
            if (message != null) {
        %>
            <div class="alert alert-<%= messageType %>">
                <i class="fas fa-<%= "success".equals(messageType) ? "check-circle" : "exclamation-circle" %>"></i>
                <span><%= ContactHelper.escapeHtml(message) %></span>
            </div>
        <%
            }
        %>

        <!-- Add/Edit Contact Form -->
        <div class="form-container">
            <h2>
                <i class="fas fa-<%= isEditing ? "edit" : "user-plus" %>"></i>
                <%= isEditing ? "Edit Contact" : "Add New Contact" %>
            </h2>
            <form action="ContactServlet" method="post">
                <input type="hidden" name="action" value="<%= isEditing ? "update" : "add" %>">
                <% if (isEditing) { %>
                    <input type="hidden" name="id" value="<%= editContact.getId() %>">
                <% } %>
                
                <div class="form-group">
                    <label for="name">
                        <i class="fas fa-user"></i> Full Name *
                    </label>
                    <input type="text" id="name" name="name" 
                           value="<%= isEditing ? ContactHelper.escapeHtml(editContact.getName()) : "" %>" 
                           placeholder="Enter full name" required>
                </div>
                
                <div class="form-group">
                    <label for="email">
                        <i class="fas fa-envelope"></i> Email Address
                    </label>
                    <input type="email" id="email" name="email" 
                           value="<%= isEditing ? ContactHelper.escapeHtml(editContact.getEmail()) : "" %>" 
                           placeholder="example@email.com">
                </div>
                
                <div class="form-group">
                    <label for="phone">
                        <i class="fas fa-phone"></i> Phone Number
                    </label>
                    <input type="tel" id="phone" name="phone" 
                           value="<%= isEditing ? ContactHelper.escapeHtml(editContact.getPhone()) : "" %>" 
                           placeholder="1234567890">
                </div>
                
                <div class="form-group">
                    <label for="address">
                        <i class="fas fa-map-marker-alt"></i> Address
                    </label>
                    <textarea id="address" name="address" 
                              placeholder="Enter address"><%= isEditing ? ContactHelper.escapeHtml(editContact.getAddress()) : "" %></textarea>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn btn-success">
                        <i class="fas fa-<%= isEditing ? "save" : "plus" %>"></i>
                        <%= isEditing ? "Update Contact" : "Add Contact" %>
                    </button>
                    <% if (isEditing) { %>
                        <a href="contacts.jsp" class="btn btn-secondary">
                            <i class="fas fa-times"></i> Cancel
                        </a>
                    <% } %>
                </div>
            </form>
        </div>

        <!-- Contacts List -->
        <div class="contacts-section">
            <%
                List<Contact> contacts = ContactDAO.getAllContacts(session);
            %>
            <h2>
                <span class="section-title">
                    <i class="fas fa-users"></i> My Contacts
                </span>
                <span class="contact-count"><%= contacts.size() %></span>
            </h2>
            
            <% if (contacts.isEmpty()) { %>
                <div class="no-contacts">
                    <i class="fas fa-address-book"></i>
                    <p>No contacts yet. Add your first contact above!</p>
                </div>
            <% } else { %>
                <div class="contacts-grid">
                    <% for (Contact contact : contacts) { 
                        String avatarColor = ContactHelper.getAvatarColor(contact.getName());
                        String initials = ContactHelper.getInitials(contact.getName());
                    %>
                        <div class="contact-card">
                            <div class="contact-avatar" style="background: <%= avatarColor %>;">
                                <%= initials %>
                            </div>
                            
                            <div class="contact-info">
                                <h3><%= ContactHelper.escapeHtml(contact.getName()) %></h3>
                                
                                <% if (!ContactHelper.isEmpty(contact.getEmail())) { %>
                                    <p>
                                        <i class="fas fa-envelope"></i>
                                        <span><%= ContactHelper.escapeHtml(contact.getEmail()) %></span>
                                    </p>
                                <% } %>
                                
                                <% if (!ContactHelper.isEmpty(contact.getPhone())) { %>
                                    <p>
                                        <i class="fas fa-phone"></i>
                                        <span><%= ContactHelper.escapeHtml(ContactHelper.formatPhone(contact.getPhone())) %></span>
                                    </p>
                                <% } %>
                                
                                <% if (!ContactHelper.isEmpty(contact.getAddress())) { %>
                                    <p>
                                        <i class="fas fa-map-marker-alt"></i>
                                        <span><%= ContactHelper.escapeHtml(contact.getAddress()) %></span>
                                    </p>
                                <% } %>
                            </div>
                            
                            <div class="contact-actions">
                                <a href="ContactServlet?action=edit&id=<%= contact.getId() %>" 
                                   class="btn-icon btn-edit" title="Edit">
                                    <i class="fas fa-edit"></i>
                                </a>
                                <form action="ContactServlet" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= contact.getId() %>">
                                    <button type="submit" class="btn-icon btn-delete" 
                                            onclick="return confirm('Are you sure you want to delete this contact?');" 
                                            title="Delete">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </form>
                            </div>
                        </div>
                    <% } %>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>