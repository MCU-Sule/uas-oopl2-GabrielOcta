package com.gabriel.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
@Entity
@Table(name = "fe_transaction", schema = "oopl20211", catalog = "")
public class FeTransaction {
    private int id;
    private Date transDate;
    private long nominal;
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
    @Column(name = "trans_date", nullable = false)
    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    @Basic
    @Column(name = "nominal", nullable = false)
    public long getNominal() {
        return nominal;
    }

    public void setNominal(long nominal) {
        this.nominal = nominal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeTransaction that = (FeTransaction) o;
        return id == that.id && nominal == that.nominal && Objects.equals(transDate, that.transDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transDate, nominal);
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
