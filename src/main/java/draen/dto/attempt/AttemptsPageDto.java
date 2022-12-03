package draen.dto.attempt;

import draen.dto.Dto;
import draen.storage.PageOfUserAttemptInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AttemptsPageDto implements Dto<PageOfUserAttemptInfo> {
    private long totalLength;
    private int pagesAmount;
    private List<UserAttemptDto> attempts;
    private long userId;
}
