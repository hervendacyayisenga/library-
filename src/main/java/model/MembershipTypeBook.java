package  model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "membership_book")
public class MembershipTypeBook {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "membership_book_id")
      private Long  Membership_book_Id;
       @Column(name = "membership_name", nullable = false)
       private String membershipName;  
       @Column(name = "max_books", nullable = false)
       private int MaxBooks; 
       @Column(name = "price", unique = true, nullable = false)
       private int Price; 
      @OneToMany(mappedBy = "membershipType", cascade = CascadeType.ALL, orphanRemoval = true)
       private List<Membership> memberships; 

         public MembershipTypeBook() {}
     
         public MembershipTypeBook(Long membership_book_Id, String membershipName, int maxBooks, int price,
				List<Membership> memberships) {
			Membership_book_Id = membership_book_Id;
			this.membershipName = membershipName;
			MaxBooks = maxBooks;
			Price = price;
			this.memberships = memberships;
		}


	public Long getMembership_book_Id() {
			return Membership_book_Id;
		}


		public void setMembership_book_Id(Long membership_book_Id) {
			Membership_book_Id = membership_book_Id;
		}


		public int getMaxBooks() {
			return MaxBooks;
		}


		public void setMaxBooks(int maxBooks) {
			MaxBooks = maxBooks;
		}


		public int getPrice() {
			return Price;
		}


		public void setPrice(int price) {
			Price = price;
		}


		public List<Membership> getMemberships() {
			return memberships;
		}


		public void setMemberships(List<Membership> memberships) {
			this.memberships = memberships;
		}


		public void setMembershipName(String membershipName) {
			this.membershipName = membershipName;
		}


	// Getters and Setters
      public String getMembershipName() {
        return membershipName;
    }
}
