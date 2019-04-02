package ghailene.souissi.springbooatapp.client.client;

import CoreProject.Processor;
import ghailene.souissi.springbooatapp.client.client.jsonModel.User;
//import CoreProjet.Processor;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

/***
 * The client application
 */
@SpringBootApplication
public class ClientApplication {
	//Logger configuration
	final static Logger logger = Logger.getLogger(ClientApplication.class);

	//a user map , useful for executing processing
	LinkedHashMap<String,Integer> usersMap = new LinkedHashMap<>();

	/***
	 * The main function, executes the SpringbootApp
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	/**
	 * Rest tamplate Bean
	 * @param builder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/**
	 * Bean execution
	 * @param restTemplate
	 * @return
	 * @throws Exception
	 */
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			//the user list extracted from the API
			User[] users = restTemplate.getForObject(
					"http://localhost:8080/all", User[].class);
			//filling the userMap with informations from the API
			for(User user:users){
				String name = user.getName();
				Integer age = user.getAge();
				usersMap.put(name,age);
			}
			logger.debug("The users list : "+usersMap);

			//Processing the files.
			String oldest = Processor.theOldest(usersMap);
			String youngest = Processor.theYoungest(usersMap);
			String longest = Processor.theLongestName(usersMap);
			String shortest = Processor.theShortestName(usersMap);
			double mean = Processor.mean(usersMap);
			int diff = Processor.diff(usersMap);


			logger.debug("the oldest is : "+oldest+" : "+usersMap.get(oldest));
			logger.debug("the youngest is : "+youngest+" : "+usersMap.get(youngest));
			logger.debug("the longest name is : "+longest+" : "+usersMap.get(longest));
			logger.debug("the shortest name is : "+shortest+" : "+usersMap.get(shortest));
			logger.debug("the age's average is "+mean);
			logger.debug("the maximum consecutive difference between ages is : "+diff);

		};
	}

}
