package com.kaltsit.sys.controller;

import com.kaltsit.annotation.SysLog;
import com.kaltsit.sys.entity.SysLogEntity;
import com.kaltsit.sys.service.impl.SysLogServiceImpl;
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

    private static final String THIS_NAME = "系统日志";

    @Resource
    private SysLogServiceImpl sysLogService;

    @GetMapping("/pageList")
    @SysLog(THIS_NAME + " - 分页查询")
    @ApiOperation(value = "列表", notes = "分页查询" + THIS_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页条数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型，0-未知 1-新增 2-删除 3-修改 4-查询 5-登录/登出", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "searchKey", value = "查询条件", paramType = "query", dataType = "string")
    })
    public JsonResult<PageUtils<SysLogEntity>> pageList(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils<SysLogEntity> pageList = sysLogService.queryPage(MapUtils.getInstance(params));
        return JsonResult.ok(pageList);
    }

}
