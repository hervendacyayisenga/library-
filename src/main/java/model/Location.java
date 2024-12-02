package model;
import javax.persistence.*;
@Entity
@Table(name = "location")
public class Location {
	@Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name = "location_id")
    private Long locationId;
   @Column(name = "location_code",  nullable = false)
    private String locationCode;
   @Column(name = "location_name", nullable = false)
    private String locationName;
   @Column(name = "province", nullable = false)
   private String province;
    @Column(name = "district", nullable = false)
    private String district;
   @Column(name = "sector", nullable = false)
    private String sector;
   @Column(name = "cell", nullable = false)
    private String cell;
   @Column(name = "village", nullable = false)
   private String village;
   @ManyToOne
   @JoinColumn(name = "user_id")
    private User personUser;

    // No-argument constructor
    public Location() {}
  
public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public User getPersonUser() {
		return personUser;
	}

	public void setPersonUser(User personUser) {
		this.personUser = personUser;
	}

public Long getLocationId() {
		return locationId;
	}

public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
