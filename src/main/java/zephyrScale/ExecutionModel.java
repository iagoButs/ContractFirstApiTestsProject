package zephyrScale;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExecutionModel {
    private String projectKey;
    private String testCaseKey;
    private String testCycleKey;
    private String statusName;
}
