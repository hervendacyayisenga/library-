package model;

import javax.persistence.*;
@Entity
@Table(name = "membership")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "membership_id")
    private Long membershipId;
    @Column(name = "membership_code", nullable = false)
    private String membershipCode;  
    @Column(name = "registration_date", nullable = false)
    private String registration_Date; 
    @Column(name = "membership_status", nullable = false)
    private String membershipStatus;   
    @Column(name = "membership_type", nullable = false)
    private String membership_Type;   
    @Column(name = "expiration_date", nullable = false)
    private String expiration_Date; 
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User personUser;  
    @ManyToOne
    @JoinColumn(name = "membership_type_id")
    private MembershipTypeBook membershipType;
    

    // Default constructor
    public Membership() {}

    public Membership(Long membershipId, String membershipCode, String registration_Date, String membershipStatus,
	String membership_Type, String expiration_Date, User personUser, MembershipTypeBook membershipType) {
		this.membershipId = membershipId;
		this.membershipCode = membershipCode;
		this.registration_Date = registration_Date;
		this.membershipStatus = membershipStatus;
		this.membership_Type = membership_Type;
		this.expiration_Date = expiration_Date;
		this.personUser = personUser;
		this.membershipType = membershipType;
	}

	// Getters and Setters
    // ...

    public Long getMembershipId() {
		return membershipId;
	}

	public String getRegistration_Date() {
		return registration_Date;
	}

	public void setRegistration_Date(String registration_Date) {
		this.registration_Date = registration_Date;
	}

	public String getMembership_Type() {
		return membership_Type;
	}

	public void setMembership_Type(String membership_Type) {
		this.membership_Type = membership_Type;
	}

	public String getExpiration_Date() {
		return expiration_Date;
	}

	public void setExpiration_Date(String expiration_Date) {
		this.expiration_Date = expiration_Date;
	}

	public User getPersonUser() {
		return personUser;
	}

	public void setPersonUser(User personUser) {
		this.personUser = personUser;
	}

	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}

	public String getMembershipCode() {
		return membershipCode;
	}

	public void setMembershipCode(String membershipCode) {
		this.membershipCode = membershipCode;
	}

	public String getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(String membershipStatus) {
		this.membershipStatus = membershipStatus;
	}

	public MembershipTypeBook getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipTypeBook membershipType) {
		this.membershipType = membershipType;
	}
}
