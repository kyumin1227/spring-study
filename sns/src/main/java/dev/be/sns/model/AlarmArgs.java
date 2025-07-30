package dev.be.sns.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// 알람의 타입에 따라 null 일수도 있는 값은 args로 json 으로 저장하는 것으로 유연하게 대처
@Data
@AllArgsConstructor
public class AlarmArgs {

    // 알람을 발생시킨 사람
    private Integer fromUserId;
    private Integer targetId;
}
