package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        ${entity} u = ${table.serviceName?uncap_first}.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(${entity} obj){
        startPage();
        QueryWrapper<${entity}> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<${entity}> list = ${table.serviceName?uncap_first}.list(w);
        return tabledatas(list);
    }

    @PostMapping
    public AxiosResult add(@RequestBody ${entity} obj){
        boolean isok = ${table.serviceName?uncap_first}.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody ${entity} obj){
        boolean isok = ${table.serviceName?uncap_first}.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = ${table.serviceName?uncap_first}.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
</#if>
