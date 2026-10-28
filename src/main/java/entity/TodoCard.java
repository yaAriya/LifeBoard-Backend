package entity;


import enums.CompletionCardStatus;

import java.time.LocalDateTime;

public class TodoCard extends Card {
    private CompletionCardStatus status;

    public TodoCard(){}

    public TodoCard(long id, String name, LocalDateTime createdAt, CompletionCardStatus status, User user){
        super(id, name, createdAt, user);
        this.status = status;
    }

    @Override
    public String toString(){
        return getId() + "," + getName() + "," + getCreatedAt() + "," + status + "," + getUser();
    }
}
