/**
 * 
 */
package com.hertz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Subba Rao Ch
 *
 */
@RestController
@RequestMapping("/")
public class GatewayController {
	

	
	@Autowired
	@LoadBalanced
    private RestTemplate restTemplate;
	
	@RequestMapping("myprofile/{id}")
	public Object[] getById(@PathVariable Integer id){
		Object[] custOrsers = new Object[2];
		final String uri = "http://customer/customer/"+id;
		Object result = restTemplate.getForObject(uri, Object.class); 
		custOrsers[0] = result;
		final String orderUri = "http://orders/order/customerId/"+id;
		@SuppressWarnings("rawtypes")
		List orders = restTemplate.getForObject(orderUri, List.class); 
		custOrsers[1] = orders;
	return custOrsers;
	}
}
