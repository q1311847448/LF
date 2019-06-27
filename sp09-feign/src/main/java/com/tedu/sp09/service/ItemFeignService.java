package com.tedu.sp09.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;


//通知指定服务id，可以知道，向那一台主机来转发调用
@FeignClient(name="item-service", fallback=ItemFeignServiceFB.class)
public interface ItemFeignService {
	/*
	 * feign利用我们熟悉的String MVC注解，
	 * 来反向生成访问路径
	 * 
	 * 根据Mapping中指定的路径在逐句地址后面拼接路径
	 * 
	 * 根据@PathVariable把参数数据拼接到路径当中
	 */
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);

	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}