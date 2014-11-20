package com.example.sbunke.testretro1;

import java.util.Date;
import java.util.Objects;

/**
 * Created by sbunke on 11/10/2014.
 */

/*
public class User {
    int id;
    String name;
    String address;
}
*/
public class User {
    //@Id
    private long id;

    private String firstName;
    private String password;
    private long duration;
    //private String medicalRecordNumber;
    private Date dateOfBirth;

    public User() {
    }

    public User(String firstName, String password, long duration) {
        super();
        this.firstName = firstName;
        this.password = password;
        this.duration = duration;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	/*
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
	*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Two Videos will generate the same hashcode if they have exactly the same
     * values for their name, url, and duration.
     *
     */

}
