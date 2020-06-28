package com.malik.university.informationgathering.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TELEGRAM_USER_NAME", length = 32)
    private String telegramUserName;

    @Column(name = "FIRST_NAME", length = 64)
    private String firstName;

    @Column(name = "LAST_NAME", length = 64)
    private String lastName;

    @Column(name = "PHONE_NUMBER", length = 32)
    private String phoneNumber;

    @Column(name = "UNIQUE_TELEGRAM_USER_ID", nullable = false, unique = true)
    private Integer uniqueTelegramUserId;

    @OneToMany(mappedBy="user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<ApiKeyEntity> apiKeys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelegramUserName() {
        return telegramUserName;
    }

    public void setTelegramUserName(String telegramUserName) {
        this.telegramUserName = telegramUserName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getUniqueTelegramUserId() {
        return uniqueTelegramUserId;
    }

    public void setUniqueTelegramUserId(Integer uniqueTelegramUserId) {
        this.uniqueTelegramUserId = uniqueTelegramUserId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<ApiKeyEntity> getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(Set<ApiKeyEntity> apiKeys) {
        this.apiKeys.clear();
        if (nonNull(apiKeys)) {
            this.apiKeys = apiKeys;
        }
    }

    public void addApiKey(ApiKeyEntity apiKeyEntity) {
        this.apiKeys.add(apiKeyEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return getId().equals(that.getId()) &&
                Objects.equals(getTelegramUserName(), that.getTelegramUserName()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equals(getUniqueTelegramUserId(), that.getUniqueTelegramUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTelegramUserName(), getFirstName(), getLastName(), getPhoneNumber(), getUniqueTelegramUserId());
    }
}
