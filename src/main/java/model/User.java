package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person_user")
public class User {
   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private Long personId;
    
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId; 

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "username", nullable = false,  unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;     
    @Column(name = "role", nullable = false)
    private String role;        
    
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "personUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Membership> memberships;  // One-to-Many with Membership

    @OneToMany(mappedBy = "personUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations;  // One-to-Many with Location

    @OneToMany(mappedBy = "personUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Borrower> borrower;  // One-to-Many with Borrowing


    // Default constructor
    public User() {}
    
    public User(Long personId, String userId, String firstname, String lastname, String gender, String username,
			String password, String role, String phoneNumber, List<Membership> memberships, List<Location> locations,
			List<Borrower> borrower) {
		this.personId = personId;
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.memberships = memberships;
		this.locations = locations;
		this.borrower = borrower;
	}


public String getUserId() {
		return userId;
	}
public void setUserId(String userId) {
		this.userId = userId;
	}
 public String getPhoneNumber() {
		return phoneNumber;
	}
public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
public List<Location> getLocations() {
		return locations;
	}
public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
public List<Borrower> getBorrower() {
		return borrower;
	}
public void setBorrower(List<Borrower> borrower) {
		this.borrower = borrower;
	}
public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

}
