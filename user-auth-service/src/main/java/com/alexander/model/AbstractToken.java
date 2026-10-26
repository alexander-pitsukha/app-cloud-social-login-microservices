package com.alexander.model;

import com.alexander.util.GeneralUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractToken extends BaseEntity<Long> {

    private static final int EXPIRATION = 60 * 24;

    @Column(name = "token")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    protected AbstractToken(final String token) {
        super();
        this.token = token;
        this.expiryDate = GeneralUtils.calculateExpiryDate(EXPIRATION);
    }

    protected AbstractToken(final String token, final User user) {
        super();
        this.token = token;
        this.user = user;
        this.expiryDate = GeneralUtils.calculateExpiryDate(EXPIRATION);
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = GeneralUtils.calculateExpiryDate(EXPIRATION);
    }

}
