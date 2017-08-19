package org.uengine.social.common.security;

import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;

/**
 * Created by uengine on 2017. 8. 3..
 * TODO: should be moved to metaworks4 module
 */
public class SecurityEvaluationContextExtension extends EvaluationContextExtensionSupport {

    @Override
    public String getExtensionId() {
        return "security";
    }

    @Override
    public SecurityExpressionRoot getRootObject() {

        return new SecurityExpressionRoot("jyjang") {};
    }
}