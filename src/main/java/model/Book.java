package model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "book_id")
    private Long bookId;
	 @Column(name = "title", nullable = false)
    private String title;
	 @Column(name = "edition", nullable = false)
    private String edition;
	 @Column(name = "isbncode", unique = true, nullable = false)
    private String ISBNcode;
	 @Column(name = "publicationyear", nullable = false)
    private String publicationYear;
	 @Column(name = "publishername", nullable = false)
    private String publisherName;
    @Column(name = "bookstatus", nullable = false)
    private BookStatus bookStatus;
    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    // No-argument constructor
    public Book() {}

    // Getters and Setters
 public String getTitle() {
        return title;
    }
 public Book(Long bookId, String title, String edition, String iSBNcode, String publicationYear,
			String publisherName, BookStatus bookStatus, Shelf shelf) {
		this.bookId = bookId;
		this.title = title;
		this.edition = edition;
		ISBNcode = iSBNcode;
		this.publicationYear = publicationYear;
		this.publisherName = publisherName;
		this.bookStatus = bookStatus;
		this.shelf = shelf;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public void setTitle(String title) {
        this.title = title;
    }


    public String getISBNcode() {
        return ISBNcode;
    }

    public void setISBNcode(String ISBNcode) {
        this.ISBNcode = ISBNcode;
    }


    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

}