package com.tpl.backend.service;

import com.tpl.backend.config.Response;
import com.tpl.backend.dto.RuleDTO;
import com.tpl.backend.entity.Rule;
import com.tpl.backend.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleService {
    @Autowired
    RuleRepository ruleRepository;

    public Response<?> getRules(){
        List<Rule> rules = ruleRepository.findAll();
        return Response.builder()
                .status(true)
                .message(null)
                .data(rules)
                .build();
    }

    public Response<?> getRule(RuleDTO ruleDTO){
        Optional<Rule> rule = ruleRepository.findById(ruleDTO.getId());
        if(rule.isPresent()){
            return Response.builder()
                    .status(true)
                    .message(null)
                    .data(rule)
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .message("Rule Not Found")
                    .data(null)
                    .build();
        }
    }

    public Response<?> createRule(RuleDTO ruleDTO){
        Optional<Rule> existingRule = ruleRepository.findOneByName(ruleDTO.getName());
        if(existingRule.isEmpty()){
            Rule rule = Rule.builder().name(ruleDTO.getName()).build();
            ruleRepository.save(rule);
            return Response.builder()
                    .status(true)
                    .message("Rule Created Successfully")
                    .data(null)
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .message("Rule Already Exists")
                    .data(null)
                    .build();
        }
    }

    public Response<?> updateRule(RuleDTO ruleDTO){
        Optional<Rule> existingRule = ruleRepository.findById(ruleDTO.getId());
        if(existingRule.isPresent()){
            Optional<Rule> isRuleAlreadyExist = ruleRepository.findOneByName(ruleDTO.getName());
            if(isRuleAlreadyExist.isEmpty()){
                Rule rule = existingRule.get();
                rule.setName(ruleDTO.getName());
                ruleRepository.save(rule);
                return Response.builder()
                        .status(true)
                        .message("Rule Updated Successfully")
                        .data(null)
                        .build();
            }else{
                return Response.builder()
                        .status(false)
                        .message("Rule Already Exists")
                        .data(null)
                        .build();
            }
        }else{
            return Response.builder()
                    .status(false)
                    .message("Rule Not Exists")
                    .data(null)
                    .build();
        }
    }

    public Response<?> deleteRule(RuleDTO ruleDTO){
        Optional<Rule> existingRule = ruleRepository.findById(ruleDTO.getId());
        if(existingRule.isPresent()){
            ruleRepository.delete(existingRule.get());
            return Response.builder()
                    .status(true)
                    .message("Rule Deleted Successfully")
                    .data(null)
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .message("Rule Not Exists")
                    .data(null)
                    .build();
        }
    }

}
