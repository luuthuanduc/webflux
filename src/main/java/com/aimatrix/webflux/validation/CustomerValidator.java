package com.aimatrix.webflux.validation;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Constraint;
import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.Validator;
import com.aimatrix.webflux.models.CustomerModel;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomerValidator implements BaseValidator<CustomerModel> {

    private final Validator<CustomerModel> validator;

    public CustomerValidator() {
        validator = ValidatorBuilder.of(CustomerModel.class)
            .constraint(CustomerModel::companyEmail, "companyEmail", c -> c.notNull().email())
            .constraint(CustomerModel::companyName, "companyName", Constraint::notNull)
            .constraint(CustomerModel::taxId, "taxId", c -> c.pattern("").message("\"{0}\" is not a valid tax identifier"))
            .build();
    }

    @Override
    public Mono<CustomerModel> validate(CustomerModel model) {
        ConstraintViolations violations = validator.validate(model);
        if (violations.isValid()) {
            return Mono.just(model);
        } else {
            return Mono.error(new ValidationException(violations.violations()));
        }
    }

}
