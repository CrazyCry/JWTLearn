package meng.learn.userservice.client;

import meng.learn.userservice.entity.JWT;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "uaa-service",fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {
    @PostMapping(value = "/oauth/token")
    JWT getToken(@RequestParam(value = "Authorization") String authorization,@RequestParam(value = "grant_type") String type,
                 @RequestParam(value = "username") String username,@RequestParam(value = "password") String password);
}
