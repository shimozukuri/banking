package project.shimozukuri.banking.configs.expressions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import project.shimozukuri.banking.services.impls.UserServiceImpl;

@Getter
@Setter
public class CustomMethodSecurityExpressionRoot
        extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;
    private HttpServletRequest request;
    private UserServiceImpl userService;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean canAccess(String username) {
        return username.equals(this.getPrincipal());
    }

    @Override
    public Object getThis() {
        return target;
    }
}
