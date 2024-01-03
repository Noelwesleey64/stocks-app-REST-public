package com.javaspringboot.stocks_app_rest_api.user.securityconfig;

import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
//The UserDetails interface provides core user information, such as the username,
// password, authorities (roles), and account status
public class UserRegistrationDetails implements UserDetails {
      private String userName;
      private String password;

      private boolean isEnabled;

      private List<GrantedAuthority> authorities;

      //constructor to set the vales of our UserRegistrationDetails object parameters
    //by passing values from a UserTbl object
    public UserRegistrationDetails(UserTbl user) {

        //Set the email of this object to the one passed from user
        this.userName = user.getEmail();

        //Set the password of this object to the one passed from user
        this.password = user.getPassword();

        //Set the isEnabled of this object to the one passed from user
        this.isEnabled = user.isEnabled();

        //The code assigns a value to a field named authorities of type Collection<? extends GrantedAuthority>,
        // which represents the roles or permissions granted to the user
        this.authorities = Arrays.stream(user.getRole()
                // splits the string returned by the getRole() method of the user object into an array of strings,
                        // using the comma as the delimiter.
                .split(","))
                //takes a string as a parameter and returns an object with that string as the authority.
                //This means that each string in the stream, such as "USER" or "ADMIN",
                // is converted into a SimpleGrantedAuthority object with that string as the authority.
                .map(SimpleGrantedAuthority::new)
                //This returns a list of SimpleGrantedAuthority objects, which is assigned to the authorities field.
                .collect(Collectors.toList());

    }

    //returns a list authorities granted to the user, such as roles or permissions.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
