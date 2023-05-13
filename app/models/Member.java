package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model
{
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Station> stations = new ArrayList<Station>();

    public Member(String firstname, String lastname, String email, String password)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getFirstname(){return firstname;}

    public String getLastname(){return lastname;}

    public String getEmail(){return email;}


    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }


    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    public boolean updateMember(String newFirstname, String newLastname, String newEmail, String newPassword, String confirmNewPassword){

        boolean success = false;

        if((newFirstname != null) && (!newFirstname.equals(""))){
            setFirstname(newFirstname);
            success = true;
        }

        if((newLastname != null) && (!newLastname.equals(""))){
            setLastname(newLastname);
            success = true;
        }

        if((newEmail != null) && (!newEmail.equals(""))){
            setEmail(newEmail);
            success = true;
        }

        if((newPassword != null) && (!newPassword.equals(""))) {
            if (newPassword.equals(confirmNewPassword)) {
                setPassword(newPassword);
                success = true;
            }
        }
        return success;
    }
}
