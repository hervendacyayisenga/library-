package model;

import javax.persistence.*;
@Entity
@Table(name = "borrower")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "borrower_id")
     private Long borrower_id;
    
     @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "user_id" )
     private User personUser;
     
    @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "book_id")
     private Book book;

     @Column(name = "due_date",unique = true,  nullable = false)
      private String due_date;
     @Column(name = "fine_amount")
      private String fine_amount;
      @Column(name = "borrow_date", nullable = false)
      private String borrow_date;
      @Column(name = "return_date", unique = true, nullable = false )
      private String return_date;

    // No-argument constructor
    public Borrower() {}

    // Getters and Setters
  
   
	public Book getBook() {
        return book;
    }

    public Borrower(Long borrower_id, User personUser, Book book, String due_date, String fine_amount,
			String borrow_date, String return_date) {
		this.borrower_id = borrower_id;
		this.personUser = personUser;
		this.book = book;
		this.due_date = due_date;
		this.fine_amount = fine_amount;
		this.borrow_date = borrow_date;
		this.return_date = return_date;
	}

	public User getPersonUser() {
		return personUser;
	}

	public void setPersonUser(User personUser) {
		this.personUser = personUser;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getFine_amount() {
		return fine_amount;
	}

	public void setFine_amount(String fine_amount) {
		this.fine_amount = fine_amount;
	}

	public String getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(String borrow_date) {
		this.borrow_date = borrow_date;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public Long getBorrower_id() {
		return borrower_id;
	}

	public void setBorrower_id(Long borrower_id) {
		this.borrower_id = borrower_id;
	}

	public void setBook(Book book) {
        this.book = book;
    }

}
