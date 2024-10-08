package com.tpl.backend.controller;

import com.tpl.backend.config.Response;
import com.tpl.backend.dto.RuleDTO;
import com.tpl.backend.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    RuleService ruleService;
    @GetMapping("/")
    public Response<?> getRules(){
        return ruleService.getRules();
    }

    @GetMapping("/{id}")
    public Response<?> getRule(@PathVariable int id){
        RuleDTO ruleDTO = RuleDTO.builder().id(id).build();
        return ruleService.createRule(ruleDTO);
    }
    @PostMapping("/")
    public Response<?> createRule(@RequestBody RuleDTO ruleDTO){
        return ruleService.createRule(ruleDTO);
    }
    @PutMapping("/{id}")
    public Response<?> updateRule(@PathVariable int id,@RequestBody RuleDTO ruleDTO){
        ruleDTO.setId(id);
        return ruleService.updateRule(ruleDTO);
    }
    @DeleteMapping("/{id}")
    public Response<?> deleteRule(@PathVariable int id){
        RuleDTO ruleDTO = RuleDTO.builder().id(id).build();
        return ruleService.deleteRule(ruleDTO);
    }
}
