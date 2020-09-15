package com.teco.market.member.domain;

public enum Role {
    ADMIN, USER, GUEST, NONE;

    public boolean isGuest() {
        return this.equals(GUEST);
    }

    public boolean isNone() {
        return this.equals(NONE);
    }

    public boolean hasPermission(Role that) {
        if (this.equals(USER)) {
            return !that.equals(ADMIN);
        }
        if (this.equals(GUEST)) {
            return that.equals(GUEST);
        }
        return true;
    }
}
