package top.brozen.quarkus.first.api.beans.form;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.limbo.utils.time.DateTimeUtils;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import java.time.LocalDateTime;

/**
 * @author Brozen
 * @since 2022-01-05
 */
@Data
public class GetWrapForm {

    @QueryParam("str")
    private String str;

    @QueryParam("num")
    private Integer num;

    private LocalDateTime dateTime;

    @HeaderParam("user-agent")
    private String ua;


    @QueryParam("dateTime")
    public void setDateTime(String dateTime) {
        this.dateTime = StringUtils.isBlank(dateTime) ? null : DateTimeUtils.parseYMDHMS(dateTime);
    }

}
