package validation;

import javax.xml.bind.ValidationException;

public interface Validator<T>
{
    public boolean validate(T obj) throws ValidationException;
}
