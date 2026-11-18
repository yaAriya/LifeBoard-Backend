package validator;

import entity.Card;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CardValidator implements Validator<Card> {
    @Override
    public boolean registerValidate(Card card) {
        return registerValidateId(card.getId()) && validateName(card.getName())
                && validateCreatedAtTime(card.getCreatedAt());
    }

    @Override
    public boolean validate(Card card) {
        return validateId(card.getId()) && validateName(card.getName())
                && validateCreatedAtTime(card.getCreatedAt());
    }

    private boolean registerValidateId(Long id) {
        return id == null;
    }

    private boolean validateId(Long id) {
        return id != null && id > 0;
    }

    private boolean validateName(String name) {
        return name != null && name.length() < 15;
    }

    private boolean validateContent(String content) {
        return content != null && content.length() < 500;
    }

    private boolean validateCreatedAtTime(LocalDateTime time) {
        return time != null && !time.isAfter(LocalDateTime.now());
    }
}
