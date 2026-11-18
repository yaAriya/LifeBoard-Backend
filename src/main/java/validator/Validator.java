package validator;

public interface Validator<T> {
    boolean registerValidate(T entity);

    boolean validate(T entity);

}
