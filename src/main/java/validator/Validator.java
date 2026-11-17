package validator;

import entity.User;

public interface Validator<T> {
    boolean registerValidate(User user);

    boolean validate(T entity);

}
