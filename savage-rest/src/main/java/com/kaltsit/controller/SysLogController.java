package com.kaltsit.controller;

import com.kaltsit.annotation.SysLog;
import com.kaltsit.entity.SysLogEntity;
import com.kaltsit.service.impl.SysLogServiceImpl;
import com.kaltsit.utils.JsonResult;
import com.kaltsit.utils.MapUtils;
import com.kaltsit.utils.PageUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/sysLog")
public class SysLogController {

    @Resource
    private SysLogServiceImpl sysLogService;

    private static final String THIS_NAME = "系统日志";

    @SysLog("查询" + THIS_NAME)
    @GetMapping("/list")
    @ApiOperation(value = "列表", notes = "分页查询" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sidx", value = "排序字段", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "order", value = "排序方式，如：asc、desc", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "searchKey", value = "查询条件", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "审核状态", paramType = "query", dataType = "string")
    })
    public JsonResult<PageUtils<SysLogEntity>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils<SysLogEntity> page = sysLogService.queryPage(MapUtils.getInstance(params));
        return JsonResult.ok(page);
    }
}
