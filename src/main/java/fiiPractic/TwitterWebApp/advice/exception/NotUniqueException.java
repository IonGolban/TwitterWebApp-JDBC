package fiiPractic.TwitterWebApp.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class NotUniqueException extends RuntimeException {

    String message ;

}
