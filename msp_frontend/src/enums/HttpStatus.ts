/**
 * @description Enum holding http status codes.
 */
export enum HttpStatus {
  OK = 200,
  CLIENT_ERROR = 400,
  PAYMENT_REQUIRED = 402,
  NOT_FOUND = 404,
  METHOD_NOT_ALLOWED = 405,
  NOT_ACCEPTABLE = 406,
  SERVER_ERROR = 500,
}
