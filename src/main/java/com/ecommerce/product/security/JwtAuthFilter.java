// package com.ecommerce.product.security;


// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// import java.io.IOException;

// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// @Component
// @RequiredArgsConstructor
// public class JwtAuthFilter extends OncePerRequestFilter {

//     private final JwtUtil jwtUtil;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         String header = request.getHeader("Authorization");

//         if (header == null || !header.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = header.substring(7);

//         if (!jwtUtil.isTokenValid(token)) {
//             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
//             return;
//         }

//         String email = jwtUtil.extractEmail(token);
//         request.setAttribute("email", email);

//         filterChain.doFilter(request, response);
//     }
// }
