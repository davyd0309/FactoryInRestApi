package pl.dawydiuk.FactoryInRestApi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class JwtFilter extends BasicAuthenticationFilter {


    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        ofNullable(request.getHeader("Authorization"))
                .ifPresent(header -> {
                    UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header.replace("Bearer ", ""));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);                });
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey("ZG)KB01hS9LsTuv".getBytes()).parseClaimsJws(header);

        String rolesFromToken = claimsJws.getBody().get("roles").toString();
        List<GrantedAuthority> grantedAuths =
                AuthorityUtils.commaSeparatedStringToAuthorityList(rolesFromToken);
        List<SimpleGrantedAuthority> roles = grantedAuths.stream()
                .map(grantedAuthority -> new SimpleGrantedAuthority(getRoleFromString(grantedAuthority)))
                .collect(Collectors.toUnmodifiableList());
        String username = claimsJws.getBody().get("name").toString();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, roles);
        return authenticationToken;
    }

    private String getRoleFromString(GrantedAuthority grantedAuthority) {
        return grantedAuthority.getAuthority()
                                .replace("[","")
                                .replace("]","")
                                .replace("authority=", "")
                                .replace("}", "")
                                .replace("{", "");
    }
}
