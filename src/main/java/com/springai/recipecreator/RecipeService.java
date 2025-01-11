package com.springai.recipecreator;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generateRecipe(String ingredients, String cuisine, String dietaryRestrictionsString) {
        
        var template = """
                I want to make a recipe with the following ingredients: {ingredients}.
                I want the cuisine to be {cuisine}.
                Consider the following dietary restrictions: {dietaryRestrictions}.
                Provide me with a detailed recipe including title, ingredients, and instructions.
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> promptValues = Map.of(
            "ingredients", ingredients,
            "cuisine", cuisine,
            "dietaryRestrictions", dietaryRestrictionsString
        );

        Prompt prompt = promptTemplate.create(promptValues, OpenAiChatOptions.builder()
            .withModel("gpt-4o-mini")
            .withTemperature(0.4)
            .build());

        ChatResponse chatResponse = chatModel.call(prompt);
        
        return chatResponse.getResult().getOutput().getContent();
    }
    
}
