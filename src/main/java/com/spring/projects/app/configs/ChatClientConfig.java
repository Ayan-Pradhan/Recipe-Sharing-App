package com.spring.projects.app.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
	
	@Bean
	ChatClient chatClient(OllamaChatModel model) {
		MessageWindowChatMemory messageWindowChatMemory = MessageWindowChatMemory
				.builder()
				.maxMessages(10)
				.build();
		
		return ChatClient.builder(model)
				.defaultAdvisors(MessageChatMemoryAdvisor
						.builder(messageWindowChatMemory)
						.build())
				.build();
	}

}
