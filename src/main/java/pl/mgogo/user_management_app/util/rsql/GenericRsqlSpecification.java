/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.util.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by baeldung.com as a part of the REST Example Project Query Language tutorial series.
 *
 * @author baeldung.com
 * @see
 * <a href="https://github.com/eugenp/tutorials/tree/master/spring-rest-query-language">https://github.com/eugenp/tutorials/tree/master/spring-rest-query-language</a>
 */
class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private transient ComparisonOperator operator;
    private List<String> arguments;

    GenericRsqlSpecification(final String property, final ComparisonOperator operator, final List<String> arguments) {
        super();
        this.property = property;
        this.operator = operator;
        this.arguments = arguments;
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        final List<Object> args = castArguments(root);
        final Object argument = args.get(0);
        switch (RsqlSearchOperation.getSimpleOperator(operator)) {
            case EQUAL:
                if (argument instanceof String) {
                    return builder.like(root.get(property), argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNull(root.get(property));
                } else {
                    return builder.equal(root.get(property), argument);
                }

            case NOT_EQUAL:
                if (argument instanceof String) {
                    return builder.notLike(root.get(property), argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNotNull(root.get(property));
                } else {
                    return builder.notEqual(root.get(property), argument);
                }

            case GREATER_THAN:
                return builder.greaterThan(root.get(property), argument.toString());

            case GREATER_THAN_OR_EQUAL:
                return builder.greaterThanOrEqualTo(root.get(property), argument.toString());

            case LESS_THAN:
                return builder.lessThan(root.get(property), argument.toString());

            case LESS_THAN_OR_EQUAL:
                return builder.lessThanOrEqualTo(root.get(property), argument.toString());

            case IN:
                return root.get(property).in(args);

            case NOT_IN:
                return builder.not(root.get(property).in(args));

            default:
                return null;
        }
    }

    private List<Object> castArguments(final Root<T> root) {

        final Class<?> type = root.get(property).getJavaType();

        return arguments.stream().map(arg -> {
            if (type.equals(Integer.class)) {
                return Integer.parseInt(arg);
            } else if (type.equals(Long.class)) {
                return Long.parseLong(arg);
            } else {
                return arg;
            }
        }).collect(Collectors.toList());
    }

}
