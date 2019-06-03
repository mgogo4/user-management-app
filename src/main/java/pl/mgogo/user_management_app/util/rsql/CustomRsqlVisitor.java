/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.util.rsql;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by baeldung.com as a part of the REST Example Project Query Language tutorial series.
 *
 * @author baeldung.com
 * @see
 * <a href="https://github.com/eugenp/tutorials/tree/master/spring-rest-query-language">https://github.com/eugenp/tutorials/tree/master/spring-rest-query-language</a>
 */
public class CustomRsqlVisitor<T> implements RSQLVisitor<Specification<T>, Void> {

    private GenericRsqlSpecBuilder<T> builder;

    public CustomRsqlVisitor() {
        builder = new GenericRsqlSpecBuilder<>();
    }

    @Override
    public Specification<T> visit(final AndNode node, final Void param) {
        return builder.createSpecification(node);
    }

    @Override
    public Specification<T> visit(final OrNode node, final Void param) {
        return builder.createSpecification(node);
    }

    @Override
    public Specification<T> visit(final ComparisonNode node, final Void params) {
        return builder.createSpecification(node);
    }

}
