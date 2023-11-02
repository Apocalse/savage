package com.kaltsit.controller.sys;

import com.kaltsit.abstracts.sys.SysDictEntity;
import com.kaltsit.abstracts.sys.SysDictItemEntity;
import com.kaltsit.service.sys.impl.SysDictItemServiceImpl;
import com.kaltsit.service.sys.impl.SysDictServiceImpl;
import io.swagger.annotations.Api;
import com.kaltsit.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "字典API")
@RestController
@RequestMapping("/sysDict")
public class SysDictController {

    @Autowired
    private SysDictServiceImpl sysDictService;

    @Autowired
    private SysDictItemServiceImpl sysDictItemService;

    @PostMapping("/addOrUpdate")
    public JsonResult<Void> addOrUpdate(@RequestBody SysDictEntity sysDictEntity){
        sysDictService.saveOrUpdate(sysDictEntity);
        return JsonResult.ok();
    }

    @PostMapping("/addOrUpdateItem")
    public JsonResult<Void> addOrUpdateItem(@RequestBody SysDictItemEntity sysDictItemEntity){
        sysDictItemService.saveOrUpdate(sysDictItemEntity);
        SysDictEntity byId = sysDictService.getById(sysDictItemEntity.getDictId());

        return JsonResult.ok();
    }

}
