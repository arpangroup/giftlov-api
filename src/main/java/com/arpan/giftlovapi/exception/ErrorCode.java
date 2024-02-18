package com.arpan.giftlovapi.exception;

public enum ErrorCode {
    TOKEN_EXPIRED(10001, Series.AUTHENTICATION, "Given jwt token is expired !!", "Token Expired"),
    INVALID_TOKEN(10002, Series.AUTHENTICATION, "Some changed has done in token !! Invalid Token", "Invalid Token"),
    INVALID_HEADER(10003, Series.AUTHENTICATION, "Invalid Header Value !", "Invalid Header"),
    INVALID_USERNAME_PASSWORD(10004, Series.AUTHENTICATION, "Wrong username or password.", ""),
    ILLEGAL_ARGUMENT(10005, Series.AUTHENTICATION, "Illegal Argument while fetching the username !!", ""),
    UNAUTHORIZED(10001, Series.AUTHENTICATION, "Wrong username or password", "");


    private final int value;
    private final Series series;
    private final String errorMessage;
    private final String reasonPhrase;

    ErrorCode(int errorCode, Series series, String errorMessage, String reasonPhrase) {
        this.value = errorCode;
        this.series = series;
        this.errorMessage = errorMessage;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public int errorCode() {
        return this.value;
    }

    public Series series() {
        return this.series;
    }

    public String errorMessage() {
        return errorMessage;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public enum Series {
        AUTHENTICATION(1),
        VALIDATION(2);
        private final int value;

        Series(int value) {
            this.value = value;
        }
        public int value() {
            return this.value;
        }
        public static Series valueOf(int statusCode) {
            Series series = resolve(statusCode);
            if (series == null) {
                throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
            }
            return series;
        }
        public static Series resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            for (Series series : values()) {
                if (series.value == seriesCode) {
                    return series;
                }
            }
            return null;
        }
    }
}
