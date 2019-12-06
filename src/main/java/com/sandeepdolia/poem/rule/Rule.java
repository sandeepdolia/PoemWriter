package com.sandeepdolia.poem.rule;

import java.util.List;


public class Rule {
	private String ruleName;
	private List<String> words;
	private List<String> referenceKeywords;
	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}
	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	/**
	 * @return the words
	 */
	public List<String> getWords() {
		return words;
	}
	/**
	 * @param words the words to set
	 */
	public void setWords(List<String> words) {
		this.words = words;
	}
	/**
	 * @return the referenceKeywords
	 */
	public List<String> getReferenceKeywords() {
		return referenceKeywords;
	}
	/**
	 * @param referenceKeywords the referenceKeywords to set
	 */
	public void setReferenceKeywords(List<String> referenceKeywords) {
		this.referenceKeywords = referenceKeywords;
	}
	

}