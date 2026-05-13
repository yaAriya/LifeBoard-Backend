package entity;

import enums.CardStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class TodoCard extends Card {
    private CardStatus status;

    public TodoCard(){}

    public TodoCard(long id, String name, LocalDateTime createdAt, CardStatus status, User user){
        super(id, name, createdAt, user);
        this.status = status;
    }

    @Override
    public String toString(){
        return getId() + "," + getName() + "," + getCreatedAt() + "," + status + "," + getUser();
    }
}
