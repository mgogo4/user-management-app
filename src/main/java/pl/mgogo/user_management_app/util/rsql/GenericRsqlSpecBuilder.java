/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.util.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by baeldung.com as a part of the REST Example Project Query Language tutorial series.
 *
 * @author baeldung.com
 * @see
 * <a href="https://github.com/eugenp/tutorials/tree/master/spring-rest-query-language">https://github.com/eugenp/tutorials/tree/master/spring-rest-query-language</a>
 */
class GenericRsqlSpecBuilder<T> {

    Specification<T> createSpecification(final LogicalNode logicalNode) {

        List<Specification<T>> specs = logicalNode.getChildren()
                .stream()
                .map(this::createSpecification)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Specification<T> result = specs.get(0);
        if (logicalNode.getOperator() == LogicalOperator.AND) {
            for (int i = 1; i < specs.size(); i++) {
                result = Specification.where(result).and(specs.get(i));
            }
        } else if (logicalNode.getOperator() == LogicalOperator.OR) {
            for (int i = 1; i < specs.size(); i++) {
                result = Specification.where(result).or(specs.get(i));
            }
        }

        return result;
    }

    Specification<T> createSpecification(final ComparisonNode comparisonNode) {
        return Specification.where(new GenericRsqlSpecification<T>(comparisonNode.getSelector(),
                comparisonNode.getOperator(), comparisonNode.getArguments()));
    }

    private Specification<T> createSpecification(final Node node) {
        if (node instanceof LogicalNode) {
            return createSpecification((LogicalNode) node);
        }
        if (node instanceof ComparisonNode) {
            return createSpecification((ComparisonNode) node);
        }
        return null;
    }
}
