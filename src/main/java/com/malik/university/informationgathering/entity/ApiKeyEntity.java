package com.malik.university.informationgathering.entity;

import com.malik.university.informationgathering.constants.Status;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "api_key")
public class ApiKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "PUBLIC_KEY", nullable = false, length = 200)
    private String publicKey;

    @Column(name = "SECRET_KEY", nullable = false, length = 200)
    private String secretKey;

    @Column(name = "ACTIVE")
    private Boolean isActive;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "CAN_TRADE")
    private Boolean canTrade;

    @Column(name = "CAN_WITHDRAW")
    private Boolean canWithdraw;

    @Column(name = "CAN_DEPOSIT")
    private Boolean canDeposit;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private UserEntity user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getCanTrade() {
        return canTrade;
    }

    public void setCanTrade(Boolean canTrade) {
        this.canTrade = canTrade;
    }

    public Boolean getCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(Boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public Boolean getCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(Boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiKeyEntity that = (ApiKeyEntity) o;
        return getId().equals(that.getId()) &&
                Objects.equals(getPublicKey(), that.getPublicKey()) &&
                Objects.equals(getSecretKey(), that.getSecretKey()) &&
                Objects.equals(isActive, that.isActive) &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPublicKey(), getSecretKey(), isActive, getStatus());
    }
}
