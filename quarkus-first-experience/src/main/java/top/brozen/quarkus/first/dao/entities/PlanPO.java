package top.brozen.quarkus.first.dao.entities;

import io.vertx.mutiny.sqlclient.Row;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serial;

/**
 * plan
 *
 * @author Devil
 * @since 2021/7/23
 */
@Getter
@Setter
@ToString
@Entity(name = "flowjob_plan")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PlanPO extends PO {

    @Serial
    private static final long serialVersionUID = -6323915044280199312L;

    /**
     * DB自增序列ID，并不是唯一标识
     */
    private Long serialId;

    /**
     * 作业执行计划ID
     */
    @Id
    private String planId;

    /**
     * 当前版本。可能发生回滚，因此 currentVersion 可能小于 recentlyVersion 。
     */
    private Integer currentVersion;

    /**
     * 最新版本
     */
    private Integer recentlyVersion;

    /**
     * 是否启动 新建plan的时候 默认为不启动
     * 接口调用的时候会修改 leader 内存数据以及 db数据 需要保障一致性
     */
    private Boolean isEnabled;

    /**
     * ORM，将数据库查询出的列映射为持久化对象
     * @param row 数据库列
     * @return 持久化对象
     */
    public static PlanPO from(Row row) {
        PlanPO po = new PlanPO();
        po.serialId = row.getLong("serial_id");
        po.planId = row.getString("plan_id");
        po.currentVersion = row.getInteger("current_version");
        po.recentlyVersion = row.getInteger("recently_version");
        po.isEnabled = row.getBoolean("is_enabled");
        return po;
    }

}
