package com.javaspringboot.stocks_app_rest_api.user.token;

import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private String token;

    private Date expirationTime;

    private static final int EXPIRATION_TIME =1;

    //The @OneToOne annotation specifies that this is a one-to-one relationship,
    @OneToOne

    //@JoinColumn annotation indicates that the current entity is the owner of the relationship,
    // and it defines the name of the foreign key column in the current entity’s table that references the primary key column of the UserTbl entity’s table.
    @JoinColumn(name = "user_id")
    private UserTbl user;

    //Constructor for token, user
    //and also set expiration time when the object is created
    public ConfirmationToken(String token, UserTbl user) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpirationTime();
    }

    //Constructor for token only
    public ConfirmationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpirationTime();
    }

    //method that returns a Date object that is EXPIRATION_TIME minutes later than the current time.
    public Date getTokenExpirationTime() {

        //creates a Calendar object that represents the current date and time in the default time zone and locale.
        Calendar calendar = Calendar.getInstance();

        //sets the calendar’s time value to the current system time in milliseconds.
        calendar.setTimeInMillis(new Date().getTime());

        // adds EXPIRATION_TIME minutes to the calendar’s time value.
        // EXPIRATION_TIME is a constant that represents the number of minutes to add.
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);

        //The calendar.getTime() method returns a Date object,
        // and the getTime() method of the Date object returns the time value in milliseconds.
        return new Date(calendar.getTime().getTime());

    }
}
