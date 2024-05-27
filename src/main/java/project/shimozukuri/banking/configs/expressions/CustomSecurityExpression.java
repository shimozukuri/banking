package project.shimozukuri.banking.configs.expressions;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("cse")
@RequiredArgsConstructor
public class CustomSecurityExpression {

    public boolean canAccess(String username) {
        return username.equals(getPrincipal());
    }

    private String getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return (String) authentication.getPrincipal();
    }
}
