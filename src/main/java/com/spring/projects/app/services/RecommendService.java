package com.spring.projects.app.services;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.spring.projects.app.constants.ResponseStatusCode;
import com.spring.projects.app.dtos.RecommendRequest;
import com.spring.projects.app.dtos.RecommendResponse;
import com.spring.projects.app.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommendService {

	private final ChatClient chatClient;

	public ResponseDto getRecommendedRecipes(RecommendRequest details) {

		String template = """
				I want to cook something delicious today. I am feeling {mood}. I want something to eat as {category}. The dish should be {type}.
				I want to prepare the dish within {cookingTime}. Suggest me some {cuisine} recipes.
				Suggest top 5 recipes with these criteria.

				IMPORTANT: Return ONLY a JSON array of 5 recipe objects. Each recipe object should follow this exact structure:
				{format}

				Do not include any explanatory text, just return the pure JSON array starting with [ and ending with ].
				""";

		BeanOutputConverter<List<RecommendResponse>> opCon = new BeanOutputConverter<>(
				new ParameterizedTypeReference<List<RecommendResponse>>() {
				});

		PromptTemplate promptTemplate = new PromptTemplate(template);
		Prompt prompt = promptTemplate.create(Map.of(
				"mood", details.getMood(),
				"category", details.getCategory(),
				"type", details.getType(),
				"cookingTime", details.getCookingTime(),
				"format", opCon.getFormat(),
				"cuisine", details.getCuisine()));

		List<RecommendResponse> response = opCon.convert(chatClient.prompt(prompt).call().content());

		return new ResponseDto(ResponseStatusCode.SUCCESS, "Recommendations generated", response);
	}

}
