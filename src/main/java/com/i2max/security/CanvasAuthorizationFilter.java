 
package com.i2max.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/**
 * Authorization filter that captures a JWT Token from session or Cookie and
 * authenticates the user based on that token 
 *
 */
public class CanvasAuthorizationFilter extends BasicAuthenticationFilter {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CanvasAuthorizationFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * @see org.springframework.security.web.authentication.www.BasicAuthenticationFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain chain)
            throws IOException, ServletException {

        final String jwtPayload = this.getJwtPayload(request);

        // Check if we have a JWT we can process
        if ((jwtPayload != null) && !"null".equals(jwtPayload)) {
            final Authentication authentication = this.extractAuthentication(jwtPayload);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // TODO: Should we renew the token?
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * Extract credentials from a jwtString
     *
     * @param jwtHeader
     *            header might be null
     * @param jwtCookie
     *            might be null, but not both
     * @return an Authentication object
     */
    private Authentication extractAuthentication(final String jwtCookie) {

        Authentication result = null;
        String user = null;
        final Collection<GrantedAuthority> roles = new ArrayList<>();
        // Now get user and claims (roles)
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(Config.PARAMS.getSecret())
                    .parseClaimsJws(jwtCookie)
                    .getBody();
            for (final Entry<String, Object> entry : claims.entrySet()) {
                if (entry.getKey().equals(SecurityConstants.USER_NAME_CLAIM)) {
                    user = String.valueOf(entry.getValue());
                } else {
                    final String roleCandidate = String.valueOf(entry.getValue());
                    if (roleCandidate.startsWith(SecurityConstants.ROLE_PREFIX)) {
                        roles.add(new CanvasGrantedAuthority(roleCandidate));
                    } else {
                        // TODO: other claims here?
                    }
                }
            }

            if (user != null) {
                result = new UsernamePasswordAuthenticationToken(user, UUID.randomUUID().toString(), roles);
            }
        } catch (final ExpiredJwtException expired) {
            this.logger.log(Level.INFO, "JWT expired", expired);
        } catch (final Exception e) {
            this.logger.log(Level.SEVERE, e.getMessage(), e);
            result = null;
        }
        return result;
    }

    /**
     * Extract the cookie we need from the request
     *
     * @param request
     *            HTTP Request
     * @return a cookie or null
     */
    private String getJwtPayload(final HttpServletRequest request) {
        String result = null;

        // First we look in the session
        final Object o = request.getSession().getAttribute(SecurityConstants.COOKIE_ATTRIBUTE);
        if ((o != null) && !"".equals(o)) {
            result = String.valueOf(o);
        } else {
            // Then in the cookies
            final Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    final Cookie currentCookie = cookies[i];
                    if (currentCookie.getName().equals(SecurityConstants.COOKIE_NAME)) {
                        result = currentCookie.getValue();
                        break;
                    }
                }
            }
        }
        return result;
    }
}
