package com.example.App.AppIW.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String path   = request.getRequestURI();
        String method = request.getMethod();

        boolean isPublic =
                path.startsWith("/api/auth")
                        || (path.equals("/api/users") && method.equals("POST"))
                        || method.equals("OPTIONS");

        if (isPublic) {
            chain.doFilter(request, response);
            return;
        }

        // Para el resto, verificar que haya sesión activa
        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (!loggedIn) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"success\":false,\"message\":\"No autorizado\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}