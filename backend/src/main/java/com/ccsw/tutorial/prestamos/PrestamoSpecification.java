package com.ccsw.tutorial.prestamos;

import java.sql.Date;
import java.text.ParseException;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.common.date.ParseDate;
import com.ccsw.tutorial.prestamos.model.Prestamo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PrestamoSpecification implements Specification<Prestamo> {

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public PrestamoSpecification(SearchCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Prestamo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null) {
            Path<String> path = (Path<String>) getPath(root);

            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(path, criteria.getValue());
            }
        }

        if (criteria.getOperation().equalsIgnoreCase("<=") && criteria.getValue() != null) {
            Path<Date> path = (Path<Date>) getPath(root);

            Date date;
            try {

                date = ParseDate.parseStringToDate(criteria.getValue().toString());
                return builder.greaterThanOrEqualTo(path, date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (criteria.getOperation().equalsIgnoreCase(">=") && criteria.getValue() != null) {
            Path<Date> path = (Path<Date>) getPath(root);

            Date date;
            try {

                date = ParseDate.parseStringToDate(criteria.getValue().toString());
                return builder.lessThanOrEqualTo(path, date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private Path<?> getPath(Root<Prestamo> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }

}
