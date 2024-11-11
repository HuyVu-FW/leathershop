package huyvu.leathershop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
//    user create
USER_EXIST(1001, "User exists"),
    USERNAME_INVALID(1003, "USERNAME MUST BE AT LEAST 3 LETTERS"),
    USER_INVALID(1004, "USER NOT EXIST"),
    UNCATEGORIZED_EXCEPTION(9999, "Have an exception"),

    PASSWORD_TOO_WEAK(1005, "Password is too weak"),
    EMAIL_ALREADY_REGISTERED(1006, "Email is already registered"),
    USER_NOT_AUTHORIZED(1007, "User not authorized"),
    SESSION_EXPIRED(1008, "Session has expired"),
    INVALID_TOKEN(1009, "Invalid authentication token"),

    ACCOUNT_LOCKED(1010, "Account is locked"),
    INSUFFICIENT_PERMISSIONS(1011, "Insufficient permissions to perform this action"),
    DATA_NOT_FOUND(1012, "Requested data not found"),
    INVALID_INPUT(1013, "Invalid input provided"),
    OPERATION_FAILED(1014, "Operation failed, please try again"),

    EMAIL_FORMAT_INVALID(1015, "Email format is invalid"),
    PHONE_NUMBER_INVALID(1016, "Phone number format is invalid"),
    ITEM_OUT_OF_STOCK(1017, "Item is out of stock"),
    CART_EMPTY(1018, "Cart is empty"),
    MAX_ATTEMPTS_REACHED(1019, "Maximum login attempts reached, please try again later"),
    TOKEN_EXPIRED(2001, "JWT token has expired"),
    TOKEN_INVALID(2002, "JWT token is invalid"),
    TOKEN_NOT_PROVIDED(2003, "JWT token not provided"),
    TOKEN_SIGNATURE_INVALID(2004, "JWT token signature is invalid"),
    TOKEN_NOT_SUPPORTED(2005, "JWT token type is not supported"),

    ROLE_NOT_FOUND(2006, "User role not found in the token"),
    CLAIMS_MISSING(2007, "Required claims are missing from the token"),
    USER_NOT_AUTHENTICATED(2008, "User is not authenticated"),
    USER_NOT_ACTIVE(2009, "User account is not active"),
    TOKEN_REVOKED(2010, "JWT token has been revoked"),
    USER_AUTHENTICATED_SUCCESSFULLY(3003, "User authenticated successfully"),;;







    private int code;
    private String message;


}
