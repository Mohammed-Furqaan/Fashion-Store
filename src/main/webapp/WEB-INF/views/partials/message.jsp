<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String successMessage =
            (String) request.getAttribute("success");

    String errorMessage =
            (String) request.getAttribute("error");
%>

<% if(successMessage != null){ %>

    <div class="message success-message">

        <%= successMessage %>

    </div>

<% } %>

<% if(errorMessage != null){ %>

    <div class="message error-message">

        <%= errorMessage %>

    </div>

<% } %>