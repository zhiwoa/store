package com.demos.Controller;

import com.demos.entity.District;
import com.demos.service.IDistrictService;
import com.demos.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.demos.Controller.BaseController.OK;

@RequestMapping("districts")
@RestController
public class DistrictController {
    @Autowired
    private IDistrictService districtService;

    //district 开头的请求都被拦截到getByParent方法中
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }
}
