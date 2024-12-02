package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private Long roomId;
    
    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "room_code", unique = true, nullable = false)
    private String roomCode;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shelf> shelves;

    // No-argument constructor
    public Room() {}

    public Room(Long roomId, String roomName, String roomCode, List<Shelf> shelves) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomCode = roomCode;
        this.shelves = shelves;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }
}
