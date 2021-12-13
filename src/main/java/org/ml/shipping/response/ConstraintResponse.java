package org.ml.shipping.response;

import java.util.List;

public class ConstraintResponse {
    private List<String> errors;

    public ConstraintResponse(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
