package com.gabriel.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
@Entity
@Table(name = "fe_member", schema = "oopl20211", catalog = "")
public class FeMember {
    private String citizenId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String username;
    private Date birthdate;

    @Id
    @Column(name = "citizenId", nullable = false, length = 14)
    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 400)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 13)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "birthdate", nullable = false)
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeMember feMember = (FeMember) o;
        return Objects.equals(citizenId, feMember.citizenId) && Objects.equals(name, feMember.name) && Objects.equals(address, feMember.address) && Objects.equals(phone, feMember.phone) && Objects.equals(email, feMember.email) && Objects.equals(username, feMember.username) && Objects.equals(birthdate, feMember.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(citizenId, name, address, phone, email, username, birthdate);
    }

}
