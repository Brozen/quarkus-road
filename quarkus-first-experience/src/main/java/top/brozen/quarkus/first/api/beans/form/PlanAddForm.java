package top.brozen.quarkus.first.api.beans.form;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.ws.rs.HeaderParam;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Devil
 * @since 2021/7/24
 */
@Data
@Schema(title = "新增计划参数")
public class PlanAddForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 3349688739542837391L;

    /**
     * 作业计划ID
     */
    @Schema(title = "作业计划ID")
    private String planId;

    /**
     * 计划描述
     */
    @Schema(title = "计划描述")
    private String description;

    @HeaderParam("user-agent")
    private String ua;

//    /**
//     * 作业计划调度配置参数
//     */
//    @Schema(title = "作业计划调度配置参数")
//    private ScheduleOptionDto scheduleOption;
//
//    /**
//     * 此执行计划对应的所有作业
//     */
//    @Schema(title = "此执行计划对应的所有作业")
//    private List<JobDto> jobs;

}
