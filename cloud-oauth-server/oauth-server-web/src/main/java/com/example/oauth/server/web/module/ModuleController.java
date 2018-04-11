package com.example.oauth.server.web.module;

import com.example.oauth.server.common.restful.RestfulVo;
import com.example.oauth.server.common.restful.ResultUtil;
import com.example.oauth.server.domain.module.dto.SysModuleDTO;
import com.example.oauth.server.service.module.ModuleService;
import com.example.oauth.server.web.base.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 资源菜单　controller
 */
@RestController
public class ModuleController extends AbstractController {

    @Autowired
    private ModuleService moduleService;

    /**
     * 保存数据
     * @param moduleDTO
     * @return
     */
    @PostMapping("module")
    public RestfulVo save(SysModuleDTO moduleDTO){
        boolean success = this.moduleService.saveModule(moduleDTO);
        return ResultUtil.restful(success);
    }
}
