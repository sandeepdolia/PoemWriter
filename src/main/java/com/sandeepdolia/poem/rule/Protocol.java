package com.sandeepdolia.poem.rule;

import java.util.HashMap;
import java.util.Map;

public class Protocol {
	private String rootRule;
	private Map<String,Rule> rules;
	
	public Protocol(){
	}
	
	
	public String getRootRule() {
		return rootRule;
	}
	
	public void setRootRule(String rootRule) {
		this.rootRule = rootRule;
	}
	
	public Map<String,Rule> getRules() {
		if (this.rules==null){
			rules = new HashMap<String, Rule>();
		}
		return rules;
	}
	
	public void setRules(Map<String,Rule> rules) {
		this.rules = rules;
	}
}