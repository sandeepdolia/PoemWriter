package com.sandeepdolia.poem.writer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.sandeepdolia.poem.rule.PoemKeywords;
import com.sandeepdolia.poem.rule.Protocol;
import com.sandeepdolia.poem.rule.Rule;

public class PoemWriter {

	public Protocol readInputFile(String inputFile){

		Protocol protocol= new Protocol();
		
		// Read the input file
		try {
			InputStream fis = this.getClass().getClassLoader().getResourceAsStream(inputFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);
	
			while (dis.available() != 0) {
				// Creates the rule object for each line
				Rule rule= new Rule();
				String line=dis.readLine();
				String [] ruleString=line.split(":");
				String ruleName = ruleString[0].trim();
				String ruleDefinition = ruleString[1].trim();
				rule.setRuleName(ruleName);
				
				// Set the rules in protocol 
				if (protocol.getRootRule()==null){
					// hydrate the root rule
					protocol.setRootRule(ruleName);
					String [] referenceKeywords=ruleDefinition.trim().split(" ");
					rule.setReferenceKeywords(Arrays.asList(referenceKeywords));			
					protocol.getRules().put(ruleName, rule);					
				}
				else{
					// hydrate child rules
					String[] ruleWordReferenceKeyword = ruleDefinition.split(" ");
					
					// Flag to decide which ruleWordReferenceKeyword is for References and Keywords
					int indexReferenceKeyword=0;
					
					// The rule has words
					if (ruleWordReferenceKeyword[0].matches("[a-z].*")){
						String [] words =ruleWordReferenceKeyword[0].trim().split("\\|");
						List<String> ruleWords= new ArrayList<String>();
					
						// Add words to the rule
						for (String word : words) {
							ruleWords.add(word);
						}
						rule.setWords(ruleWords);
						
						// Second entry of the ruleWordReferenceKeyword is for References and Keywords
						indexReferenceKeyword=1;	
					}
	
					// Get the References and Keywords and add them to the rule
					String [] referenceKeywords=ruleWordReferenceKeyword[indexReferenceKeyword].trim().split("\\|");
					rule.setReferenceKeywords(Arrays.asList(referenceKeywords));			
					// Add the rule to protocol
					protocol.getRules().put(ruleName, rule);	            
				}
			}
			
			// Done with reading, close resources
			fis.close();
			bis.close();
			dis.close();
		} catch (IOException e) {
			System.out.println("Error reading input file.");
		}		  	  
		return protocol;		  	  
	}
	
	
	
	public void generateRandomPoem(Protocol protocol){

		// Get the root rule
        Rule rootRule = protocol.getRules().get(protocol.getRootRule());
        
        // Get all the child elements of the root rule
        List<Rule> rules= new ArrayList<Rule>();
        for(String ruleName: rootRule.getReferenceKeywords()){ 	
        	rules.add(protocol.getRules().get(ruleName.substring(1, ruleName.length() - 1)));
        }
        
        for(Rule rule: rules){
        	writePoem(rule, protocol);
        }
	}
	
	 private static void writePoem(Rule rule, Protocol protocol) {
	    	
	    	// Print all the words belonging to a rule
	    	if (rule.getWords()!=null){
		        List<String> words = rule.getWords();
		        int random = getRandomNumber(0, words.size());
		        String word = words.get(random);
		        System.out.print(word+" "); 
	    	}
	    	
	    	// Now lets get random refernence and keywords
	        List<String> referencesAndKeywords=rule.getReferenceKeywords();
	        int random = getRandomNumber(0, referencesAndKeywords.size());
	        String referenceKey = referencesAndKeywords.get(random);
	        
	        if (referenceKey.matches("<[A-Z]+>")) {
	        	// Call the writePeom again for reference words
	        	Rule nextRule = protocol.getRules().get(referenceKey.substring(1, referenceKey.length() - 1));
	        	writePoem(nextRule, protocol);
	        }
	        else {
	        	// Randomly select keywords in a poem.
	        	PoemKeywords valueOf = PoemKeywords.valueOf(referenceKey);
	        	System.out.print(valueOf.getValue());
	        }
	    }
	
	    private static int getRandomNumber(int min, int max) {
	        Random r = new Random();
	        return r.nextInt(max - min) + min;
	    }
	
	public static void main(String args[]) {
		PoemWriter writer = new PoemWriter();
		Protocol protocol= writer.readInputFile("poem.txt");
		// Generate a poem
		writer.generateRandomPoem(protocol);
		
	}
}
