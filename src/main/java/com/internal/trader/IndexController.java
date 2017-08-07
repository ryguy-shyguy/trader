package com.internal.trader;

import com.internal.helpers.RequestHelper;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	private static final String template = "Hello, %s!";
	    private final AtomicLong counter = new AtomicLong();
	
	    @RequestMapping("/index")
	    public Index index(@RequestParam(value="name", defaultValue="World") String name) {
	        try {
	        	RequestHelper.baseRequester();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	    	return new Index(counter.incrementAndGet(),
	                            String.format(template, name));
	    }
}
