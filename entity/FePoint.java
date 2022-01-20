package com.gabriel.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
@Entity
@Table(name = "fe_point", schema = "oopl20211", catalog = "")
public class FePoint {
    private int id;
    private int value;
    private FeMember feMemberByMemberCitizenId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value", nullable = false)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FePoint fePoint = (FePoint) o;
        return id == fePoint.id && value == fePoint.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    @ManyToOne
    @JoinColumn(name = "member_citizenId", referencedColumnName = "citizenId", nullable = false)
    public FeMember getFeMemberByMemberCitizenId() {
        return feMemberByMemberCitizenId;
    }

    public void setFeMemberByMemberCitizenId(FeMember feMemberByMemberCitizenId) {
        this.feMemberByMemberCitizenId = feMemberByMemberCitizenId;
    }
}
