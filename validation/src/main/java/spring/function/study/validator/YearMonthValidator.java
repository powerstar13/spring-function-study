package spring.function.study.validator;

import spring.function.study.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> { // ConstraintValidator의 제네릭으로 첫 번째는 커스텀 애노테이션을 넣어주고, 두 번째는 입력 값을 전달하면 된다.

    private String pattern; // yyyyMM

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern(); // 초기화
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {
            LocalDate localDate = LocalDate.parse(value + "01", DateTimeFormatter.ofPattern(this.pattern + "dd")); // LocalDate의 구조에 맞추기 위해 임시 일자 추가
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }
}
