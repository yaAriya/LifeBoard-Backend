package validator;

import entity.Device;
import entity.User;
import enums.Language;
import enums.Theme;
import enums.Timezone;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserValidator implements Validator<User> {
    @Override
    public boolean registerValidate(User user) {
        return registerValidateId(user.getId()) && validateName(user.getName()) && validateAge(user.getAge()) && validateEmail(user.getEmail()) &&
                validatePassword(user.getPassword()) && validateTheme(user.getTheme()) && validateLanguage(user.getLanguage()) &&
                validateTimezone(user.getTimezone()) && validateDevices(user.getDevices());
    }

    @Override
    public boolean validate(User user) {
        return validateId(user.getId()) && validateName(user.getName()) && validateAge(user.getAge()) && validateEmail(user.getEmail()) &&
                validatePassword(user.getPassword()) && validateTheme(user.getTheme()) && validateLanguage(user.getLanguage()) &&
                validateTimezone(user.getTimezone()) && validateDevices(user.getDevices());
    }

    private boolean registerValidateId(Long id) {
        return id == null;
    }

    private boolean validateId(Long id) {
        return id != null || id > 0;
    }

    private boolean validateName(String name) {
        return name != null;
    }

    private boolean validateAge(int age) {
        return age >= 0 && age <= 120;
    }

    private boolean validateEmail(String email) {
        return email != null;
    }

    private boolean validatePassword(String password) {
        return password != null
                && password.length() >= 8;
    }

    private boolean validateTheme(Theme theme) {
        return theme != null;
    }

    private boolean validateLanguage(Language language) {
        return language != null;
    }

    private boolean validateTimezone(Timezone timezone) {
        return timezone != null;
    }

    private boolean validateDevices(List<Device> devices) {
        if (devices == null) {
            return false;
        } else if (devices.size() > 10) {
            return false;
        }
        Set<Long> set = new HashSet();
        for (Device d : devices) {
            if (d == null) {
                return false;
            }
            if (!set.add(d.getId())) {
                return false;
            }
        }
        return true;
    }
}
