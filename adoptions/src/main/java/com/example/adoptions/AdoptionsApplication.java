package com.example.adoptions;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class AdoptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionsApplication.class, args);
	}

}

@Controller
@ResponseBody
class AdoptionController {

    private final ChatClient  ai;

    public AdoptionController(ChatClient.Builder ai) {
        this.ai = ai.build();
    }

    @PostMapping("/{user}/inquire")
    String inquire (
            @RequestParam String question
    ) {
        return this.ai
                .prompt()
                .user( question )
                .call()
                .content();
    }

}
