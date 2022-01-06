package top.brozen.quarkus.first.dao.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Tuple;
import top.brozen.quarkus.first.dao.entities.PlanPO;
import top.brozen.quarkus.first.support.sqlclient.transform.Transformers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Brozen
 * @since 2022-01-05
 */
@ApplicationScoped
public class PanachePlanRepository implements PanacheRepositoryBase<PlanPO, String> {


    @Inject
    MySQLPool client;



    public Multi<PlanPO> listAllPlans() {
        return client.query("select * from flowjob_plan").execute()
                .onItem().transformToMulti(Transformers.forClassMulti(PlanPO.class));
    }


    public Uni<Boolean> update(String planId, Boolean enabled) {
        return client.withTransaction(conn -> conn
                .preparedQuery("update flowjob_plan set is_enabled = $1 where plan_id = $2")
                .execute(Tuple.of(planId, enabled))
        ).onItem().transform(rows -> rows.rowCount() == 1);
    }

}
