package com.barnabasszoke.customerapp.domain;

import java.io.Serializable;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * Persistent tokens are used by Spring Security to automatically log in users.
 *
 * @see com.mycompany.myapp.security.CustomPersistentRememberMeServices
 */
public class PersistentToken implements Serializable {


	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("d MMMM yyyy");

    private String series;

    private String tokenValue;

    private LocalDate tokenDate;

    private String ipAddress;

    private String userAgent;
    
    private User user;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public LocalDate getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(LocalDate tokenDate) {
        this.tokenDate = tokenDate;
    }

    public String getFormattedTokenDate() {
        return dateTimeFormatter.print(this.tokenDate);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        if (userAgent.length() >= 255) {
            this.userAgent = userAgent.substring(0, 254);
        } else {
            this.userAgent = userAgent;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersistentToken that = (PersistentToken) o;

        if (!series.equals(that.series)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return series.hashCode();
    }

    @Override
    public String toString() {
        return "PersistentToken{" +
                "series='" + series + '\'' +
                ", tokenValue='" + tokenValue + '\'' +
                ", tokenDate=" + tokenDate +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                "}";
    }
}
