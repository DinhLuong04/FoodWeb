package com.DinhLuong.FoodDelivery.security;

import com.DinhLuong.FoodDelivery.Config.ApiEndpoints;
import com.DinhLuong.FoodDelivery.Ultil.JwtUltil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomFillterJWT extends OncePerRequestFilter {

    private static final List<String> PUBLIC_API_LIST =  ApiEndpoints.getPublicEndpoints();
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private JwtUltil jwtUltil;

    @Autowired
    private UserDetailsService userDetailsService;

    @SuppressWarnings("deprecation")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
   

                String path = request.getRequestURI();

                boolean isPublic = PUBLIC_API_LIST.stream().anyMatch(pattern -> pathMatcher.match(pattern, path));
            
                boolean isWebSocket = "Upgrade".equalsIgnoreCase(request.getHeader("Connection")) &&
                                      "websocket".equalsIgnoreCase(request.getHeader("Upgrade"));
            
                if (isPublic || isWebSocket) {
                    System.out.println("Bỏ qua xác thực với API công khai hoặc WebSocket: " + path);
                    filterChain.doFilter(request, response);
                    return;
                }

        String token = getTokenFromHeader(request);

        if (token == null) {
            sendJsonError(response, HttpServletResponse.SC_UNAUTHORIZED, "Thiếu token, vui lòng đăng nhập!");
            return;
        }

        try {
            String username = jwtUltil.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!jwtUltil.validateToken(token, userDetails)) {
                sendJsonError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token không hợp lệ hoặc đã hết hạn!");
                return;
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (ExpiredJwtException e) {
            sendJsonError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token đã hết hạn, vui lòng đăng nhập lại!");
            return;
        } catch (SignatureException e) {
            sendJsonError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token không hợp lệ!");
            return;
        } catch (Exception e) {
            sendJsonError(response, HttpServletResponse.SC_FORBIDDEN, "Lỗi xác thực token!");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private void sendJsonError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = "{ \"error\": \"" + message + "\", \"status\": " + status + " }";

        response.getWriter().write(json);
    }

}
