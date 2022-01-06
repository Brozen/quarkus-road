package top.brozen.quarkus.first.controllers;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.limbo.utils.strings.Symbol;
import top.brozen.quarkus.first.api.beans.form.GetWrapForm;
import top.brozen.quarkus.first.api.beans.form.PlanAddForm;
import top.brozen.quarkus.first.dao.entities.PlanPO;
import top.brozen.quarkus.first.dao.repositories.PanachePlanRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * OpenAPI3 注解说明 https://github.com/eclipse/microprofile-open-api/wiki/Annotation-Samples
 * 与swagger-annotation包提供的注解名称相同，但是要用microprofile-openapi-api包里的注解
 *
 *
 * @author Brozen
 * @since 2022-01-05
 */
@Slf4j
@Path("/api/plan")
@Produces(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(info = @Info(title = "Plan测试接口", version = "v1"))
public class PlanController {

    @Inject
    PanachePlanRepository planRepo;


    @GET
    public Multi<PlanPO> getAllPlans(@BeanParam GetWrapForm form) {
        log.info("请求参数 {}", form);
        return planRepo.listAllPlans();
//        return planRepo.listAll().onItem().transformToMulti(plans -> Multi.createFrom().iterable(plans));
    }


    /**
     * 接收POST参数的body，content-type=application/json的，直接用bean参数接受即可，无需额外注解。
     * 如果加了@BeanParam注解，反而接收不到。
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "新增plan")
    public Uni<Symbol> addPlan(@RequestBody PlanAddForm form, @BeanParam PlanAddForm formParam) {
        return Uni.createFrom().item(Symbol.symbol());
    }


}
